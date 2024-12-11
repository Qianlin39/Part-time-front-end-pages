<script setup>
import {reactive, ref, onMounted, onBeforeUnmount, shallowRef} from 'vue';
import Quill from 'quill';
import 'quill/dist/quill.snow.css';
import {useCounterStore} from '@/stores/counter.js'
import {ElMessage} from "element-plus";
import myaxios from "@/axios/myaxios.js";
// 引入Quill样式文件

// 表单数据模型
let form = ref({
  Pid:0,
  Pname: '',
  Ptype: '',
  Psalary:0,
  Pcontent: '' ,// 这里用来存储编辑器的内容
  Puid: 0,
});

// Quill编辑器相关配置
const quill = ref(null);  // 用于绑定Quill编辑器
const editor = shallowRef(null);  // 编辑器引用

onMounted(() => {
  editor.value = new Quill(quill.value, {
    theme: 'snow',
    modules: {
      toolbar: [
        [{ 'header': '1' }, { 'header': '2' }, { 'font': [] }],
        [{ 'list': 'ordered' }, { 'list': 'bullet' }],
        [{ 'align': [] }],
        ['bold', 'italic', 'underline'],
        ['link'],
        [{ 'color': [] }, { 'background': [] }],
        ['blockquote', 'code-block']
      ]
    }
  });

  // 监听编辑器内容变化
  editor.value.on('text-change', () => {
    form.value.Pcontent = editor.value.root.innerHTML; // 获取富文本内容并存储
  });
});

onBeforeUnmount(() => {
  // 清理Quill编辑器
  if (editor.value) {
    editor.value=null;
  }
});


//获取登录用户消息
const coun = useCounterStore()
 form.value.Puid = coun.email.userid
// 提交表单
const onSubmit = async () => {
  if (!form.value.Pname || !form.value.Ptype || !form.value.Pcontent) {
    ElMessage({ type: 'error', message: '请填写完整信息！' });
    return;
  }

  // 打印from
  console.log('提交的内容：', {
    pname: form.value.Pname,
    pcontent: form.value.Pcontent,
    ptype: form.value.Ptype,
    puid: form.value.Puid,
    psalary: form.value.Psalary,
  });
  try{
    const res=await myaxios.post('/addPost',{
      pname: form.value.Pname,
      pcontent: form.value.Pcontent,
      ptype: form.value.Ptype,
      puid: form.value.Puid,
      psalary: form.value.Psalary,
    })
    if(res.data.code===1){
      ElMessage({type: 'success', message: '提交成功！'});
      ElMessage({type: 'success', message: '请等待审核！'});
    }else{
      ElMessage({type: 'error', message: res.data.msg});
    }
  }catch (e) {
    ElMessage({type: 'error', message: '提交失败！'});
    console.log(e)
  }
};
</script>

<template>
  <div class="back centered-content" style="background-color: white; width: 100%; height: 90%; border-radius: 10px;">
    <!-- 上传论坛内容的表单，里面有帖子名字，帖子的内容，帖子的类型 -->
    <el-form :model="form" label-width="auto" style="max-width: 600px">
      <el-form-item label="标题">
        <el-input v-model="form.Pname" />
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="form.Ptype" placeholder="请选择帖子类型">
          <el-option label="分享" value="share" />
          <el-option label="帮助" value="help" />
        </el-select>
      </el-form-item>
<!--      薪资-->
      <el-form-item label="薪资">
        <el-input v-model="form.Psalary" />
      </el-form-item>
      <el-form-item label="内容">
        <div ref="quill" style="height: 260px;width: 560px"></div> <!-- 用于渲染Quill编辑器 -->
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button>Cancel</el-button>
      </el-form-item>
     </el-form>
  </div>
</template>

<style scoped>
.centered-content {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
