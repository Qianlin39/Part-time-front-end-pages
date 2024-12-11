<!-- UserLogin.vue -->
<script setup lang="ts" >
import {ref} from "vue";
import {
  Document, Search,
} from '@element-plus/icons-vue';
import {useCounterStore} from '../stores/counter.js'
import {useRouter} from "vue-router";
import Post from "../components/post/Post.vue";
import EditPost from "../components/post/EditPost.vue";
import Message from "../components/Message/Message.vue";
import Personalcenter from "../components/User/Personalcenter.vue";
import Share from "../components/forum/Share.vue";
import Help from "../components/forum/Help.vue";

let $router = useRouter()

//
const particlesLoaded = async container => {
  console.log("Particles container loaded", container);
};

const isCollapse = ref(true)
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
}
const coun = useCounterStore()

const selectedMenuItem = ref('')

const navigateTo = (routeName: string) => {
  $router.push({ name: routeName })
};

// export const logout =async () => {
//   coun.logout()
//   await $router.push('/login')
// };

</script>

<template>
  <div class="app">
<!--    背景设计-->
    <vue-particles
        id="tsparticles"
        class="login"
        @particles-loaded="particlesLoaded"
        :options="{
                    // background:{
                    //     color: {
                    //         value: '#000'
                    //     }
                    // },
                    fpsLimit: 120,
                    interactivity: {
                        events: {
                            onClick: {
                                enable: true,
                                mode: 'push'
                            },
                            onHover: {
                                enable: true,
                                mode: 'grab'
                            },
                        },
                        modes: {
                            bubble: {
                                distance: 400,
                                duration: 2,
                                opacity: 0.8,
                                size: 40
                            },
                            push: {
                                quantity: 4
                            },
                            repulse: {
                                distance: 200,
                                duration: 0.4
                            }
                        }
                    },
                    particles: {
                        color: {
                            value: '#eaa4f6'
                        },
                        links: {
                            color: '#bea6c4',
                            distance: 150,
                            enable: true,
                            opacity: 0.5,
                            width: 1
                        },
                        move: {
                            direction: 'none',
                            enable: true,
                            outModes: 'bounce',
                            random: true,
                            speed: 6,
                            straight: false
                        },
                        number: {
                            density: {
                                enable: true,

                            },
                            value: 80
                        },
                        opacity: {
                            value: 0.5
                        },
                        shape: {
                            type: 'circle'
                        },
                        size: {
                            value: { min: 1, max: 5 }
                        }
                    },
                    detectRetina: true
                }"
    />
    <div class="common-layout">
      <el-container>
        <el-aside width="200px">
<!--          <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">-->
<!--            <el-radio-button :value="false">expand</el-radio-button>-->
<!--            <el-radio-button :value="true">collapse</el-radio-button>-->
<!--          </el-radio-group>-->
          <el-button @click="toggleCollapse" style="margin: 20px 0 20px 70px;  border: none; width: 16px; height: 30px;">
  <el-avatar
      src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
  />
<!--换行-->
</el-button>
          <br/>
          <span style="margin-left: 60px">{{coun.email.username}}</span>

          <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              :collapse="isCollapse"
              @open="handleOpen"
              @close="handleClose"
              style=" background: white;border-radius: 10px"
          >
            <el-sub-menu index="1">
              <template #title >
                <el-icon><MostlyCloudy /></el-icon>
                <span>论坛</span>
              </template>
              <el-menu-item index="1-1" @click="navigateTo('Share')">交流分享</el-menu-item>
              <el-menu-item index="1-2" @click="navigateTo('Help')">困难求助</el-menu-item>
<!--              <el-menu-item-group>-->
<!--                <template #title><span>Group One</span></template>-->
<!--                <el-menu-item index="1-1">item one</el-menu-item>-->
<!--                <el-menu-item index="1-2">item two</el-menu-item>-->
<!--              </el-menu-item-group>-->
<!--              <el-menu-item-group title="Group Two">-->
<!--                <el-menu-item index="1-3">item three</el-menu-item>-->
<!--              </el-menu-item-group>-->
<!--              <el-sub-menu index="1-4">-->
<!--                <template #title><span>item four</span></template>-->
<!--                <el-menu-item index="1-4-1">item one</el-menu-item>-->
<!--              </el-sub-menu>-->
            </el-sub-menu>
            <el-sub-menu index="2">
              <template #title>
                <el-icon><UploadFilled /></el-icon>
                <span>发布</span>
              </template>
              <el-menu-item index="2-1" @click="navigateTo('Post')">发布帖子</el-menu-item>
              <el-menu-item index="2-2" @click="navigateTo('EditPost')">修改帖子</el-menu-item>
            </el-sub-menu>
            <el-menu-item index="3" @click="navigateTo('Message')">
              <el-icon><document /></el-icon>
              <template #title>消息</template>
            </el-menu-item>
            <el-menu-item index="4"@click="navigateTo('user')">
              <el-icon><UserFilled /></el-icon>
              <template #title>我的</template>
            </el-menu-item>
<!--            <el-menu-item index="5" @click="logout()">-->
<!--              <el-icon><Key /></el-icon>-->
<!--              <template #title>退出</template>-->
<!--            </el-menu-item>-->
          </el-menu>
        </el-aside>
        <el-container>
          <el-header style="background-color: rgba(0, 255, 255, 0.1);border-radius: 10px">
            <div style="display: flex; align-items: center;margin-top: 10px">
            <h1 style="font-family: 'KaiTi', cursive; font-size: 36px; margin-right: 20px;color: #00d9ff;min-width: 200px;white-space: nowrap;">听凭风引，与风同行</h1>
            <el-button type="primary" style="margin-left: 700px">
           <el-icon style="vertical-align: middle">
            <Search />
           </el-icon>
          <span style="vertical-align: middle"> Search </span>
          </el-button>
         </div>
          </el-header>
          <el-main style="height: 100vh">
            <router-view />
          </el-main>
        </el-container>
      </el-container>
    </div>


  </div>
</template>

<style scoped>
.app{
  background-image: url("@/assets/img/home2.png");
  height: 100%;
  width: 100%;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  position: fixed;
  top: 0;
  left: 0;

}
.layout-container-demo .el-header {
  position: relative;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
  border-radius: 10px;
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
  border-radius: 10px;
}
.layout-container-demo .el-menu {
  border-right: none;
  border-radius: 10px;

}
.layout-container-demo .el-main {
  padding: 0;
  border-radius: 10px;
}
.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
  border-radius: 10px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
  border-radius: 10px;
}

</style>