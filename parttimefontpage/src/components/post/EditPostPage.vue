<script setup>
import { ref, reactive, onMounted, nextTick } from 'vue';
import { ElMessage, ElDialog } from 'element-plus';
import myaxios from '@/axios/myaxios.js';
import { useRouter, useRoute } from 'vue-router';

const router = useRouter();
const route = useRoute();
const Userid = route.params.userid; // 假设 userid 从路由参数中获取

const tableData = ref([]);
const editDialogVisible = ref(false);
const currentPost = reactive({});

onMounted(() => {
  myaxios.get(`/posts/${Userid}`)
      .then(response => {
        tableData.value = response.data;
      })
      .catch(error => {
        ElMessage.error('获取数据失败: ' + error.message);
      });
});

const handleEdit = (row) => {
  console.log('Before edit:', editDialogVisible.value, currentPost);
  Object.assign(currentPost, row);  // 使用 Object.assign 来合并对象
  nextTick(() => {
    console.log('After nextTick:', editDialogVisible.value, currentPost);
    editDialogVisible.value = true;
  });
};

const handleSaveEdit = () => {
  const updatedData = {
    Pid: currentPost.pid,
    Pname: currentPost.pname,
    Ptype: currentPost.ptype,
    Pupdatetime: new Date().toISOString(),
  };

  myaxios.put(`/posts/${currentPost.pid}`, updatedData)
      .then(() => {
        ElMessage.success('更新成功');
        const index = tableData.value.findIndex(item => item.pid === currentPost.pid);
        if (index !== -1) {
          tableData.value[index] = updatedData;
        }
        editDialogVisible.value = false;
      })
      .catch(error => {
        ElMessage.error('更新失败: ' + error.message);
      });
};

const handleCancel = () => {
  router.push('/previous-page'); // 跳转到之前的页面或列表页
};
</script>

<template>
  <div class="edit-post-page" style="background-color: white; width: 100%; height: 100%; border-radius: 10px;">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column fixed prop="pid" label="序号" width="150" />
      <el-table-column prop="ptype" label="类型" width="120" />
      <el-table-column prop="puid" label="标题" width="120" />
      <el-table-column prop="pname" label="标题" width="120" />
      <el-table-column prop="pcontent" label="内容" width="120" />
      <el-table-column prop="pupdatetime" label="更新时间" width="120" />
      <el-table-column fixed="right" label="操作" min-width="120">
        <template #default="{ row }">
          <el-button link type="primary" size="small" @click="handleEdit(row)">
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
        title="编辑帖子"
        :visible.sync="editDialogVisible"
    >
      <el-form :model="currentPost">
        <el-form-item label="标题" prop="pname">
          <el-input v-model="currentPost.pname" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="类型" prop="ptype">
          <el-input v-model="currentPost.ptype" placeholder="请输入类型" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="handleSaveEdit">保存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">
.edit-post-page {
  padding: 20px;
}

.el-table {
  margin-top: 20px;
}

.el-button {
  margin-right: 10px;
}

.el-dialog {
  z-index: 9999;
}
</style>
