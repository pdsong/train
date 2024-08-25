<template>
  <a-layout-sider width="200" style="background: #fff">
    <a-menu
        v-model:selectedKeys="selectedKeys"
        v-model:openKeys="openKeys"
        mode="inline"
        :style="{ height: '100%', borderRight: 0 }"
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
      <a-menu-item key="/station">
        <router-link to="/station">
          <coffee-outlined/>车站管理
        </router-link>
      </a-menu-item>
<!--      原来是子menu里a-menu-item形式 现在改成了直接a-menu-item-->
<!--      <a-sub-menu key="sub1">-->
<!--        <template #title>-->
<!--              <span>-->
<!--                <user-outlined/>-->
<!--                subnav 11-->
<!--              </span>-->
<!--        </template>-->
<!--        <a-menu-item key="1">option1</a-menu-item>-->
<!--        <a-menu-item key="2">option2</a-menu-item>-->
<!--        <a-menu-item key="3">option3</a-menu-item>-->
<!--        <a-menu-item key="4">option4</a-menu-item>-->
<!--      </a-sub-menu>-->
<!--      <a-sub-menu key="sub2">-->
<!--        <template #title>-->
<!--              <span>-->
<!--                <laptop-outlined/>-->
<!--                subnav 2-->
<!--              </span>-->
<!--        </template>-->
<!--        <a-menu-item key="5">option5</a-menu-item>-->
<!--        <a-menu-item key="6">option6</a-menu-item>-->
<!--        <a-menu-item key="7">option7</a-menu-item>-->
<!--        <a-menu-item key="8">option8</a-menu-item>-->
<!--      </a-sub-menu>-->
<!--      <a-sub-menu key="sub3">-->
<!--        <template #title>-->
<!--              <span>-->
<!--                <notification-outlined/>-->
<!--                subnav 3-->
<!--              </span>-->
<!--        </template>-->
<!--        <a-menu-item key="9">option9</a-menu-item>-->
<!--        <a-menu-item key="10">option10</a-menu-item>-->
<!--        <a-menu-item key="11">option11</a-menu-item>-->
<!--        <a-menu-item key="12">option12</a-menu-item>-->
<!--      </a-sub-menu>-->
    </a-menu>
  </a-layout-sider>
</template>

<script>
import {defineComponent, ref, watch} from 'vue';
import router from "@/router";

export default defineComponent({
  name: 'the-sider-view',
  setup() {
    const selectedKeys=ref([])
    //监视当前路由变化  例如 最新路由变化/welcome放入selectedKeys 与 <a-menu-item key一致 即可实现选中效果
    watch(()=>router.currentRoute.value.path,(newValue)=>{
      console.log('watch',newValue);
      selectedKeys.value=[];
      selectedKeys.value.push(newValue);
    },{immediate:true})

    return {
      selectedKeys ,
      openKeys: ref(['sub1'])
    };
  }
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
