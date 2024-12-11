<script setup>
import { ref, onMounted } from 'vue';
import { ElMessage, ElDialog } from 'element-plus';
import myaxios from '@/axios/myaxios.js';
import { useCounterStore } from '@/stores/counter.js';
import router from "@/router/index.js";

const Userid = useCounterStore().email.userid;

const tableData = ref([]);
const editDialogVisible = ref( false);
const currentPost = ref({});

onMounted(() => {
  myaxios.get(`/posts/${Userid}`)
    .then(response => {
      tableData.value = response.data;
    })
    .catch(error => {
      ElMessage.error('获取数据失败: ' + error.message);
    });
});

const handleDelete = (row) => {
  myaxios.delete(`/posts/${row.pid}`)
    .then(() => {
      ElMessage.success('删除成功');
      tableData.value = tableData.value.filter(item => item.pid !== row.pid);
    })
    .catch(error => {
      ElMessage.error('删除失败: ' + error.message);
    });
};

// const handleEdit = (row) => {
//   console.log('Editing post:', row);  // 检查 row 数据
//   currentPost.value = { ...row };
//   console.log('Dialog visibility:', editDialogVisible.value);
//   editDialogVisible.value = true;
//
// };
//
// const handleSaveEdit = () => {
//   const updatedData = {
//     Pid: currentPost.value.pid,
//     Pname: currentPost.value.pname,
//     Ptype: currentPost.value.ptype,
//     Pupdatetime: new Date().toISOString(),
//   };
//
//   myaxios.put(`/posts/${currentPost.value.pid}`, updatedData)
//     .then(() => {
//       ElMessage.success('更新成功');
//       const index = tableData.value.findIndex(item => item.pid === currentPost.value.pid);
//       if (index !== -1) {
//         tableData.value[index] = updatedData;
//       }
//       editDialogVisible.value = false;
//     })
//     .catch(error => {
//       ElMessage.error('更新失败: ' + error.message);
//     });
// };
const viewPost = (row) => {
  // 跳转到帖子详情页面
  router.push({ name: 'Edit-Post', params: { userid: row.pid } });
};
</script>

<template>
  <div class="back" style="background-color: white; width: 100%; height: 90%; border-radius: 10px;">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column fixed prop="pid" label="序号" width="150" />
      <el-table-column prop="ptype" label="类型" width="120" />
      <el-table-column prop="puid" label="标题" width="120" />
      <el-table-column prop="pname" label="标题" width="120" />
      <el-table-column prop="puid" label="标题" width="120" />
      <el-table-column prop="pcontent" label="内容" width="120" />
      <el-table-column prop="pupdatetime" label="更新时间" width="120" />
      <el-table-column fixed="right" label="Operations" min-width="120">
        <template #default="{ row }">
          <el-button link type="primary" size="small" @click="handleDelete(row)">
            删除
          </el-button>
          <el-button link type="primary" size="small" @click="viewPost(row)">
            修改
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
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSaveEdit">保存</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<style scoped lang="scss">
.back {
  padding: 20px;
}

.el-table {
  margin-top: 20px;
}

.el-button {
  margin-right: 10px;
}
</style>