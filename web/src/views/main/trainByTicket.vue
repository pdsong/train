<template>
  <p>
    <a-space>
      <a-date-picker v-model:value="dateParam"  @change ="handleQuery()"/>
      <a-button type="primary" @click="handleQuery()">刷新</a-button>
    </a-space>
  </p>
  <a-table :dataSource="trains"
           :columns="columns"
           :pagination="pagination"
           @change="handleTableChange"
           :loading="loading">
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'operation'">
        <a-space>
<!--          <a-popconfirm-->
<!--              title="删除后不可恢复，确认删除?"-->
<!--              @confirm="onDelete(record)"-->
<!--              ok-text="确认" cancel-text="取消">-->
<!--            <a style="color: red">删除</a>-->
<!--          </a-popconfirm>-->
          <a @click="onOrder(record)">订票</a>
        </a-space>
      </template>
      <template v-else-if="column.dataIndex === 'type'">
        <span v-for="item in TRAIN_TYPE_ARRAY" :key="item.code">
          <span v-if="item.code === record.type">
            {{item.desc}}
          </span>
        </span>
      </template>
    </template>
  </a-table>
</template>

<script>
import { defineComponent, ref, onMounted, watch } from 'vue';
import {notification} from "ant-design-vue";
import axios from "axios";
import {pinyin} from "pinyin-pro";

export default defineComponent({
  name: "train-by-ticket",
  components: {},
  setup() {
    const dateParam=ref();
    // const searchByDate = () => {
    //   axios.post("/xx/xx/search", dateParam.value).then((response) => {
    //     let data = response.data;
    //     if (data.success) {
    //       notification.success({description: "搜索成功！"});
    //       handleQuery({
    //         page: pagination.value.current,
    //         size: pagination.value.pageSize
    //       });
    //     } else {
    //       notification.error({description: data.message});
    //     }
    //   });
    // };



    const TRAIN_TYPE_ARRAY = window.TRAIN_TYPE_ARRAY;
    const visible = ref(false);
    let train = ref({
      id: undefined,
      code: undefined,
      type: undefined,
      start: undefined,
      startPinyin: undefined,
      startTime: undefined,
      end: undefined,
      endPinyin: undefined,
      endTime: undefined,
      createTime: undefined,
      updateTime: undefined,
    });
    const trains = ref([]);
    // 分页的三个属性名是固定的
    const pagination = ref({
      total: 0,
      current: 1,
      pageSize: 10,
    });
    let loading = ref(false);
    const columns = [
      {
        title: '车次编号',
        dataIndex: 'code',
        key: 'code',
      },
      {
        title: '车次类型',
        dataIndex: 'type',
        key: 'type',
      },
      {
        title: '始发站',
        dataIndex: 'start',
        key: 'start',
      },
      {
        title: '出发时间',
        dataIndex: 'startTime',
        key: 'startTime',
      },
      {
        title: '终点站',
        dataIndex: 'end',
        key: 'end',
      },
      {
        title: '到站时间',
        dataIndex: 'endTime',
        key: 'endTime',
      },      {
        title: '剩余车票',
        dataIndex: 'ticketRestNum',
        key: 'endTime',
      },
      {
        title: '操作',
        dataIndex: 'operation'
      }
    ];

    watch(() => train.value.start, ()=>{
      if (train.value.start) {
        train.value.startPinyin = pinyin(train.value.start, { toneType: 'none'}).replaceAll(" ", "");
      } else {
        train.value.startPinyin = "";
      }
    }, {immediate: true});
    watch(() => train.value.end, ()=>{
      if (train.value.end) {
        train.value.endPinyin = pinyin(train.value.end, { toneType: 'none'}).replaceAll(" ", "");
      } else {
        train.value.endPinyin = "";
      }
    }, {immediate: true});

    const onAdd = () => {
      train.value = {};
      visible.value = true;
    };

    const onOrder = (record) => {
      train.value = JSON.parse(JSON.stringify(record));
      console.log("--------train---->",train.value)
      axios.post("/business/web/trainTicket/orderTic", train.value).then((response) => {
        let data = response.data;
        if (data.success) {
          notification.success({description: "保存成功！"});
          visible.value = false;
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          });
        } else {
          notification.error({description: data.message});
        }
      });
    };

    const onDelete = (record) => {
      axios.delete("/business/admin/train/delete/" + record.id).then((response) => {
        const data = response.data;
        if (data.success) {
          notification.success({description: "删除成功！"});
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {
          notification.error({description: data.message});
        }
      });
    };

    const handleOk = () => {
      axios.post("/business/admin/train/save", train.value).then((response) => {
        let data = response.data;
        if (data.success) {
          notification.success({description: "保存成功！"});
          visible.value = false;
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          });
        } else {
          notification.error({description: data.message});
        }
      });
    };

    const handleQuery = (param) => {
      if (!param) {
        param = {
          page: 1,
          size: pagination.value.pageSize
        };
      }
      loading.value = true;
      axios.get("/business/web/trainTicket/queryByDate", {
        params: {
          page: param.page,
          size: param.size,
          dateParam:dateParam.value
        }
      }).then((response) => {
        loading.value = false;
        let data = response.data;
        if (data.success) {
          trains.value = data.content.list;
          // 设置分页控件的值
          pagination.value.current = param.page;
          pagination.value.total = data.content.total;
        } else {
          notification.error({description: data.message, duration: 1});
        }
      });
    };

    const handleTableChange = (pagination) => {
      // console.log("看看自带的分页参数都有啥：" + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };

    const genSeat = (record) => {
      loading.value = true;
      axios.get("/business/admin/train/gen-seat/" + record.code).then((response) => {
        loading.value = false;
        const data = response.data;
        if (data.success) {
          notification.success({description: "生成成功！"});
        } else {
          notification.error({description: data.message});
        }
      });
    };

    onMounted(() => {
      handleQuery({
        page: 1,
        size: pagination.value.pageSize
      });
    });

    return {
      TRAIN_TYPE_ARRAY,
      train,
      visible,
      trains,
      pagination,
      columns,
      handleTableChange,
      handleQuery,
      loading,
      onAdd,
      handleOk,
      onOrder,
      onDelete,
      genSeat
    };
  },
});
</script>
