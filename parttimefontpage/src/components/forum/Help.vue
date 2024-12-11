<script setup>
import { ref, onMounted } from 'vue';
import myaxios from "@/axios/myaxios.js";
import { ElMessage } from 'element-plus';
import router from "@/router/index.js";

// 用于存储获取的帖子数据
const posts = ref([]);

// 获取所有帖子数据
onMounted(() => {
  myaxios.get('posts/all')
      .then((response) => {
        posts.value = response.data;  // 保存数据
      })
      .catch((error) => {
        ElMessage.error('加载帖子失败: ' + error.message);
      });
});
const viewPost = (Pid) => {
  // 跳转到帖子详情页面
  router.push({ name: 'PostDetail', params: { id: Pid } });
};
</script>

<template>
  <div class="forum-container">
    <!-- 论坛标题 -->
    <h1>论坛帖子</h1>

    <!-- 帖子列表 -->
    <el-row :gutter="20">
      <el-col v-for="post in posts" :key="post.Pid" :span="8">
        <el-card class="post-card">
          <div class="post-header">
            <h3>{{ post.pname }}</h3>
            <p>类型：{{ post.ptype }}</p>
            <p>作者：{{ post.username }}</p>
            <p>更新时间：{{ post.pupdatetime }}</p>
          </div>

          <div class="post-content">
            <p>{{ post.pcontent }}</p>
          </div>

          <div class="post-footer">
            <el-button type="text" size="small" @click="viewPost(post.pid)">查看详情</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.forum-container {
  padding: 20px;
}

.post-card {
  margin-bottom: 20px;
}

.post-header h3 {
  font-size: 1.5rem;
  margin-bottom: 10px;
}

.post-footer {
  margin-top: 10px;
}
</style>
