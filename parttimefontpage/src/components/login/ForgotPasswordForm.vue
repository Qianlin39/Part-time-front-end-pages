<script setup>
import {onMounted, ref} from 'vue'
import SIdentify from '@/components/login/Sidentify.vue'
import { ElMessage } from 'element-plus'
import {createRouter as $router, useRouter} from 'vue-router'
import myaxios from "@/axios/myaxios.js";

const email = ref('')
const password = ref('')
const confirmPassword = ref('')
const code= ref('')
const Ycode = ref('')
const validateEmail = (email)=>{
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return re.test(email)
}

const Verify = async() =>{
  //邮箱格式不对
  if (!validateEmail(email.value)) {
    ElMessage({type: 'error', message: '邮箱格式不对！'})
    return
  }
  //邮箱和密码不能为空
  if (!email.value || !password.value) {
    ElMessage({type: 'error', message: '邮箱和密码不能为空！'})
    return
  }
  //两次密码不一致
  if (password.value !== confirmPassword.value) {
    ElMessage({type: 'error', message: '两次密码不一致！'})
    return
  }
  try{
    const res = await myaxios.post('/email',{
      useremail: email.value,
    })
    if(res.data.code===1) {
      Ycode.value = res.data.data
      ElMessage({type: 'success', message: '发送验证码成功'})
    }else{
      ElMessage({type: 'error', message: res.data.msg})
    }
  }catch(e){
    ElMessage({type: 'error', message: '发送验证码失败'})
    console.log(e)
  }
}
// 修改密码
const forgotPassword = async () => {
  // 验证邮箱格式
  if (!validateEmail(email.value)) {
    ElMessage({ type: 'error', message: '邮箱格式不对！' })
    return
  }
  // 验证邮箱和密码不为空
  if (!email.value || !password.value) {
    ElMessage({ type: 'error', message: '邮箱和密码不能为空！' })
    return
  }
  // 验证两次密码不一致
  if (password.value !== confirmPassword.value) {
    ElMessage({ type: 'error', message: '两次密码不一致！' })
    return
  }
  // 验证验证码不为空
  if (!code.value) {
    ElMessage({ type: 'error', message: '验证码不能为空！' })
    return
  }
  // 验证验证码是否正确
  if (code.value !== Ycode.value) {
    ElMessage({ type: 'error', message: '验证码错误！' })
    return
  }
  try {
    const res = await myaxios.post('/forgotPassword', {
      useremail: email.value,
      userpassword: password.value,
    })
    if(res.data.code===1){
      ElMessage({type: 'success', message: '修改密码成功'})
    //   修改密码成功后跳转到登录页面
   await $router.push('/login')
    }else{
      ElMessage({type: 'error', message: res.data.msg})
    }
  } catch (e) {
    ElMessage({type: 'error', message: '修改密码失败'})
    console.log(e)
  }
}

</script>

<template>
  <div class="loginPart">
    <h2>修改密码</h2>
    <el-form
      :model="{ email }"
      ref="forgotPasswordFormRef"
      label-width="100px"
      style="transform: translate(-30px)"
    >
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="email" placeholder="请输入邮箱" clearable></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="password" placeholder="请输入新的密码" clearable></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input v-model="confirmPassword" placeholder="请再次输入密码" clearable></el-input>
      </el-form-item>
      <el-form label-width="65px">
        <el-form-item label="验证码">
          <el-input placeholder="请输入验证码" v-model="code" clearable style="width: 50%; display: inline-block;"></el-input>
          <el-button type="primary" @click="Verify" style="width: 40%; display: inline-block; margin-left: 2%;">发送验证码</el-button>
        </el-form-item>
<!--   发送验证码 按钮    -->


      </el-form>
      <el-button class="btn" type="primary" @click="forgotPassword" auto-insert-space @keyup.enter="forgotPassword">修改密码</el-button>
      <div style="text-align: right; transform: translate(0, 30px)">
        <el-link type="danger" @click="$emit('changeView', 'login')" style="margin-right: 140px">登录</el-link>
        <el-link type="warning" @click="$emit('changeView', 'register')">没有账号？去注册</el-link>
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