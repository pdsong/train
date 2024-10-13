<template>
  <div>
    <a-row gutter="16">
      <a-col :span="6" v-for="(bookmark,index) in bookmarks" :key="bookmark.enTitle">
        <a-card hoverable :style="{ cursor: 'pointer' }">
          <a :href="bookmark.link" target="_blank" style="display: block; text-decoration: none; color: inherit;">
            <div>
              <h3>{{ index + 1 }}.  {{ bookmark.cnTitle }}</h3>
              <a-card-meta title="">
                <template #description>{{ bookmark.enTitle }}</template>
              </a-card-meta>
            </div>
          </a>
        </a-card>

        <!--        <a-card hoverable>-->
<!--          <a :href="bookmark.link" target="_blank">-->
<!--            {{ bookmark.cnTitle  }}-->
<!--          </a>-->
<!--          <a-card-meta title="">-->
<!--            <template #description>{{ bookmark.enTitle}}</template>-->
<!--          </a-card-meta>-->
<!--        </a-card>-->
      </a-col>
    </a-row>
  </div>
</template>

<script>

import {defineComponent, onMounted, reactive} from "vue";
import axios from "axios";
import {notification} from "ant-design-vue";

export default defineComponent({
  setup(){
      const bookmarks = reactive([
        { id: 1, title: 'Google', url: 'https://www.google.com' },
        { id: 2, title: 'Facebook', url: 'https://www.facebook.com' },
        // 添加更多书签数据
      ]);

    onMounted(() => {
      // console.log('组件已挂载，当前时间:', new Date().toLocaleTimeString());
      getNews()
    });

    const getNews = () => {
      axios.post("/news/news/hackNews", {}).then(response => {
        let data = response.data;
        if (data.success) {
          bookmarks.splice(0, bookmarks.length, ...data.content.list);
        } else {
          notification.error({ description: data.message });
        }
      });
    };

      return{
        bookmarks,
        getNews
      }
  }
})


</script>

<style scoped>

</style>