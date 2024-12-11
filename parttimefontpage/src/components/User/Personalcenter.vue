<template>
  <div class="container">
    <el-avatar
      class="avatar"
      src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
    />
    <span class="username">{{ username }}</span>
    <el-button type="primary" @click="logout">退出</el-button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import {useCounterStore} from "@/stores/counter.js"
import { useRouter } from 'vue-router';

const router = useRouter();

const coun=useCounterStore()

const username = coun.email.username;

const logout = () => {
  // 处理退出逻辑
  coun.logout()
  router.push('/login');
  console.log('用户已退出');
};
</script>

<style scoped >
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 85vh;
  border-radius: 10px;

  background: linear-gradient(to bottom, rgba(0, 217, 255, 0.5), white),
  url('@/assets/img/home1.jpg') no-repeat center center;
  background-size: cover;
  background-blend-mode: overlay;

}

.avatar {
  width: 100px;
  height: 100px;
  margin-bottom: 20px;
}

.username {
  font-size: 24px;
  margin-bottom: 20px;
}

.el-button {
  width: 100px;
}
</style>