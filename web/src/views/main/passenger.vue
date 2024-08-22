<template>
  <h1>乘车人管理</h1>
  <a-button type="primary" @click="showModal">新增</a-button>
  <!--  api实现 头部 底部设置 点击空白处不消失-->
  <a-modal v-model:visible="visible" title="乘车人" @ok="handleOk"
           ok-text="确认" cancel-text="取消">
    <a-form :model="passenger" :label-col="{span:4}" :wrapper-col="{span:20}">
      <a-form-item label="姓名">
        <a-input v-model:value="passenger.name"></a-input>
      </a-form-item>
      <a-form-item label="身份证">
        <a-input v-model:value="passenger.idCard"></a-input>
      </a-form-item>
      <a-form-item label="类型">
        <a-select v-model:value="passenger.type">
          <a-select-option value="1">成人</a-select-option>
          <a-select-option value="2">儿童</a-select-option>
          <a-select-option value="3">学生</a-select-option>
<!--          <a-select-option v-for="item in PASSENGER_TYPE_ARRAY" :key="item.code" :value="item.code">-->
<!--            {{ item.desc }}-->
<!--          </a-select-option>-->
        </a-select>
      </a-form-item>
    </a-form>

  </a-modal>
</template>

<script>

import {defineComponent, reactive, ref} from "vue";
import axios from "axios";
import {notification} from "ant-design-vue";

export default defineComponent({
  setup() {
    const passenger = reactive({
      id: undefined,
      memberId: undefined,
      name: undefined,
      idCard: undefined,
      type: undefined,
      createTime: undefined,
      updateTime: undefined,
    });
    // 分页的三个属性名是固定的
    // const pagination = ref({
    //   total: 0,
    //   current: 1,
    //   pageSize: 10,
    // });
    const PASSENGER_TYPE_ARRAY = window.PASSENGER_TYPE_ARRAY;
    const visible = ref(false)

    const showModal = () => {
      visible.value = true;
    };

    const handleOk = e => {
      console.log(e)

      axios.post("/member/passenger/save",passenger).then((response)=>{
        let data=response.data
        if(data.success){
          notification.success({description:"保存成功"});
          visible.value = false;
          // handleQuery({
          //   page:pagination.value.current,
          //   size:pagination.value.pageSize
          // });
        }
        else{
          notification.error({description:data.message})
        }
      })
    };

    return {
      passenger,
      PASSENGER_TYPE_ARRAY,
      showModal,
      handleOk,
      visible
    }
  }
})


</script>

<style scoped>

</style>