<template>
  <a-select v-model:value="trainCode" show-search allowClear
            :filterOption="filterTrainCondeOption"
            @change="onChange" placeholder="请选择车次"
            :style="'width: ' + localWidth">
    <a-select-option v-for="item in trains" :key="item.code" :value="item.code"
                     :label="item.code + item.start + item.end">
      {{ item.code }} {{ item.start }} ~ {{ item.end }}
    </a-select-option>
  </a-select>
</template>
<script>
import {defineComponent, onMounted, ref, watch} from "vue";
import {notification} from "ant-design-vue";
import axios from "axios";

export default defineComponent({
  name: "train-select-view",
  props: ["modelValue", "width"],
  emits: ['update:modelValue', 'change'],

  setup(props, {emit}) {
    const trainCode = ref()
    const trains = ref([])
    const localWidth = ref(props.width)
    if (!props.width) {
      localWidth.value = '100%'
    }

    // 利用watch，动态获取父组件的值，如果放在onMounted或其它方法里，则只有第一次有效
    watch(() => props.modelValue, () => {
          trainCode.value = props.modelValue;
        },
        {immediate: true}
    );

    //查询所有车次
    const queryAllTrain = () => {
      let list = SessionStorage.get(SESSION_ALL_TRAIN)
      if(list) {
        console.log("queryAllTrain 读取缓存");
        trains.value = list;
      } else {
        axios.get("/business/admin/train/query-all").then((response) => {
          let data = response.data;
          if (data.success) {
            trains.value = data.content;
            console.log("queryAllTrain 保存缓存");
            SessionStorage.set(SESSION_ALL_TRAIN, trains.value);
          } else {
            notification.error({description: data.message})
          }
        })
      }

    };

    /**
     * 车次下拉框筛选
     * 根据输入筛选
     */
    const filterTrainCondeOption = (input, option) => {
      console.log(input, option);
      return option.label.toLowerCase().indexOf(input.toLowerCase()) >= 0;
    };


    /**
     * 将当前组件的值响应给父组件
     * @param value
     */
    const onChange = (value) => {
      emit('update:modelValue', value);
      let train = trains.value.filter(item => item.code === value)[0]
      if (!train) {
        train = {}
      }
      emit('change', train)
    };
    onMounted(() => {
      queryAllTrain();
    });
    return {
      trainCode,
      trains,
      filterTrainCondeOption,
      onChange,
      localWidth
    }
  }

})
</script>
<style scoped>

</style>