<script setup>
import {onMounted, ref} from 'vue'
import SIdentify from '@/components/login/Sidentify.vue'
import { ElMessage } from 'element-plus'
import {createRouter as $router, useRouter} from 'vue-router'
import axios from "axios";
import myaxios from "@/axios/myaxios.js";

const email = ref('')
const username = ref('')
const password = ref('')
const confirmPassword = ref('')

// 注册
const register = async () => {
  // 验证验证码不为空
  if (!email.value) {
    ElMessage({ type: 'error', message: '邮箱不能为空！' })
    return
  }
  if (!username.value) {
    ElMessage({ type: 'error', message: '用户名不能为空！' })
    return
  }
  if (!password.value) {
    ElMessage({ type: 'error', message: '密码不能为空！' })
    return
  }
  if (password.value !== confirmPassword.value) {
    ElMessage({ type: 'error', message: '两次密码不一致！' })
    return
  }

  try {
    const res = await myaxios.post('/register', {
      useremail: email.value,
      username: username.value,
      userpassword: password.value,
    })
    if(res.data.code===1){
      ElMessage({type: 'success', message: '注册成功'})
    //   注册成功后跳转到登录页面

    }else{
      ElMessage({type: 'error', message: res.data.msg})
      await $router.push('/home')
    }
  } catch (e) {
    ElMessage({type: 'error', message: '注册失败'})
    console.log(e)
  }
}




</script>

<template>
  <div class="loginPart">
    <h2>用户注册</h2>
    <el-form
      :model="{ username, password, email }"
      ref="registerFormRef"
      label-width="100px"
      style="transform: translate(-30px)"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="username" placeholder="请输入用户名" clearable></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="email" placeholder="请输入邮箱" clearable></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="password" placeholder="请输入密码" show-password clearable></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="confirmPassword" placeholder="请确认密码" show-password clearable></el-input>
      </el-form-item>
      <el-button class="btn" type="primary" @click="register" auto-insert-space @keyup.enter="register">注册</el-button>
      <div style="text-align: right; transform: translate(0, 30px)">
        <el-link type="warning" @click="$emit('changeView', 'login')">已有账号？去登录</el-link>
      </div>
    </el-form>
  </div>
</template>

<style lang="scss" scoped>
.loginPart {
  position: absolute;
  top: 50%;
  left: 80%;
  transform: translate(-50%, -50%);
  width: 450px;
  padding: 50px;
  background: rgba(255, 204, 255, 0.3);
  box-sizing: border-box;
  box-shadow: 0px 15px 25px rgba(0, 0, 0, 0.5);
  border-radius: 15px;
}
.btn {
  transform: translate(170px);
  width: 80px;
  height: 40px;
  font-size: 15px;
}
.code-btn {
  transform: translate(20px);
  width: 90px;
  height: 40px;
  font-size: 10px;
}
</style>