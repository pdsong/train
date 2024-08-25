<template>
  <a-layout-header class="header">
    <div class="logo">
      <router-link to="/welcome">
        控制台
      </router-link>
    </div>
    <div style="float:right;color:white">
      控制台管理
    </div>
    <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <coffee-outlined/>欢迎
        </router-link>
      </a-menu-item>
      <a-menu-item key="/about">
        <router-link to="/about">
          <coffee-outlined/>关于
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
import {defineComponent, ref, watch} from 'vue';
// import store from "@/store";
import router from "@/router";

export default defineComponent({
  name: 'the-header-view',
  setup() {
    // let member = store.state.member;
    const selectedKeys=ref([])
    //监视当前路由变化  例如 最新路由变化/welcome放入selectedKeys 与 <a-menu-item key一致 即可实现选中效果
    watch(()=>router.currentRoute.value.path,(newValue)=>{
      console.log('watch',newValue);
      selectedKeys.value=[];
      selectedKeys.value.push(newValue);
    },{immediate:true})

    return {
      selectedKeys,
    };
  }
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.logo {
  float: left;
  height: 31px;
  width: 150px;
  color: white;
  font-size: 20px;
}
</style>
