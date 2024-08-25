<template>
  <a-layout id="components-layout-demo-top-side-2">
    <the-header-view></the-header-view>
    <a-layout>
    <the-sider-view></the-sider-view>
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
<!--          所有会员总数:{{ count }}-->
          <router-view></router-view>
        </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
// ref
import {defineComponent,} from 'vue';
import TheHeaderView from "@/components/the-header.vue";
import TheSiderView from "@/components/the-sider.vue";
// import axios from "axios";
// import {notification} from "ant-design-vue";

export default defineComponent({
  components: {
    TheSiderView,
    TheHeaderView
  },
  setup() {
    // ref用来生命基本的数据类型  reactive用来生命对象或者对象数组
    // const  count=ref();

    // axios.post("/member/member/count").then((response) => {
    //   let data = response.data;
    //   if (data.success) {
    //     count.value=data.content;
    //     notification.success({ description: '请求成功!' });
    //
    //   } else {
    //     notification.error({ description: data.message });
    //   }
    // })

    return {
      // count
    }
  }
})

</script>
<style scoped>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>