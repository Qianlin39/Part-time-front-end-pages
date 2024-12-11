// PostDetail.vue
<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router'; // 使用 Vue Router 获取路由参数

// 获取当前帖子的ID
const route = useRoute();
const postId = route.params.id;

// 用于存储帖子详情数据
const postDetail = ref({});

// 获取帖子详情
onMounted(() => {
  axios.get(`http://localhost:8080/posts/post/${postId}`)
      .then((response) => {
        postDetail.value = response.data.data;
      })
      .catch((error) => {
        ElMessage.error('获取帖子详情失败: ' + error.message);
      });
});
</script>

<template>
  <div class="post-detail-container">
    <h2>{{ postDetail.pname }}</h2>
    <p>作者：{{ postDetail.username }}</p>
    <p>类型：{{ postDetail.ptype }}</p>
    <p>工资：{{ postDetail.psalary}}</p>
    <p>更新时间：{{ postDetail.pupdatetime }}</p>
    {{ postDetail.pcontent }}
  </div>
</template>


<style scoped>

</style>