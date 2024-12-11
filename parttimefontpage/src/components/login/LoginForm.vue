<script setup>
import {onMounted, ref} from 'vue'
import SIdentify from '@/components/login/Sidentify.vue'
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";
import myaxios from "@/axios/myaxios.js";
import {useCounterStore} from '@/stores/counter.js'




let $router = useRouter()
let sidentifyMode = ref('') //输入框验证码
let identifyCode = ref('') //图形验证码
let identifyCodes = ref('1234567890abcdefjhijklinopqrsduvwxyz') //验证码出现的数字和字母
let loginForm = ref({
  email: '',
  password: '',
})


//组件挂载
onMounted(() => {
  identifyCode.value = ''
  makeCode(identifyCodes.value, 4)
})

// 生成随机数
const randomNum = (min, max) => {
  max = max + 1
  return Math.floor(Math.random() * (max - min) + min)
}
// 随机生成验证码字符串
const makeCode = (o, l) => {
  for (let i = 0; i < l; i++) {
    identifyCode.value += o[randomNum(0, o.length)]
  }
}
// 更新验证码
const refreshCode = () => {
  identifyCode.value = ''
  makeCode(identifyCodes.value, 4)

}
const validateEmail = (email)=>{
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return re.test(email)
}

//登录
const login = async () => {
  //邮箱格式不对
  if (!validateEmail(loginForm.value.email)) {
    ElMessage({type: 'error', message: '邮箱格式不对！'})
    return
  }
  //邮箱和密码不能为空
  if (!loginForm.value.email || !loginForm.value.password) {
    ElMessage({type: 'error', message: '邮箱和密码不能为空！'})
    return
  }
  //验证验证码不为空
  if (!sidentifyMode.value) {
    ElMessage({type: 'error', message: '验证码不能为空！'})
    return
  }
  //验证验证码是否正确
  if (sidentifyMode.value !== identifyCode.value) {
    ElMessage({type: 'error', message: '验证码错误'})
    refreshCode()
    return
  }
  try {
    const res = await myaxios.post('/login',{
      useremail: loginForm.value.email,
      userpassword: loginForm.value.password,
     })
    if(res.data.code===1){
      const u= useCounterStore()
      u.setUser(res.data.data)
      ElMessage({type: 'success', message: '登录成功'})
      await $router.push('/home')
    }else{
      ElMessage({type: 'error', message: res.data.msg})
    }
  } catch (e) {
    ElMessage({type: 'error', message: '登录失败'})
    console.log(e)
  }
}




</script>

<template>
  <div class="loginPart">
    <h2>用户登录</h2>
    <el-form
        :model="loginForm"
        ref="loginFormRef"
        :rules="login"
        label-width="100px"
        style="transform: translate(-30px)"
    >
      <el-form-item label="邮箱" prop="email">
        <el-input
            v-model="loginForm.email"
            placeholder="请输入邮箱"
            clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
            type="password"
            v-model="loginForm.password"
            placeholder="请输入密码"
            show-password
            clearable
        ></el-input>
      </el-form-item>
      <el-form label-width="65px">
        <el-form-item label="验证码">
          <el-input placeholder="请输入验证码" v-model="sidentifyMode" clearable></el-input>
        </el-form-item>
        <!-- 使用验证码组件 -->
        <div class="code" @click="refreshCode">
          <SIdentify :identifyCode="identifyCode"></SIdentify>
        </div>
      </el-form>
      <el-button
          class="btn"
          type="primary"
          @click="login"
          auto-insert-space
          @keyup.enter="login"
      >登录</el-button
      >
      <div style="text-align: right; transform: translate(0, 30px)">
        <el-link
            type="danger"
            @click="$emit('changeView', 'forgotPassword')"
            style="margin-right: 140px"
        >
          忘记密码？
        </el-link>

        <el-link type="warning" @click="$emit('changeView', 'register')"
        >没有账号？去注册</el-link
        >
      </div>
    </el-form>
  </div>
</template>

<style lang="scss" scoped>
.loginPart {
  position: absolute;
  /*定位方式绝对定位absolute*/
  top: 50%;
  left: 80%;
  /*顶和高同时设置50%实现的是同时水平垂直居中效果*/
  transform: translate(-50%, -50%);
  /*实现块元素百分比下居中*/
  width: 450px;
  padding: 50px;
  background: rgba(255, 204, 255, 0.3);
  /*背景颜色为黑色，透明度为0.8*/
  box-sizing: border-box;
  /*box-sizing设置盒子模型的解析模式为怪异盒模型，
    将border和padding划归到width范围内*/
  box-shadow: 0px 15px 25px rgba(0, 0, 0, 0.5);
  /*边框阴影  水平阴影0 垂直阴影15px 模糊25px 颜色黑色透明度0.5*/
  border-radius: 15px;
  /*边框圆角，四个角均为15px*/
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