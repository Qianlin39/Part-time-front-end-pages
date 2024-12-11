import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIcons from '@element-plus/icons-vue'

import zhCn from 'element-plus/es/locale/lang/zh-cn'

import Particles from "@tsparticles/vue3"
import { loadSlim } from "@tsparticles/slim"
import {loadFull} from "tsparticles";

const app = createApp(App)

for (const [key,component] of Object.entries(ElementPlusIcons)) {
    app.component(key, component)
}

app.use(ElementPlus, { locale: zhCn })

app.use(createPinia())
app.use(router)
app.use(Particles, {
    init: async engine => {
        await loadFull(engine); // you can load the full tsParticles library from "tsparticles" if you need it
        await loadSlim(engine); // or you can load the slim version from "@tsparticles/slim" if don't need Shapes or Animations
    },
});

app.mount('#app')
