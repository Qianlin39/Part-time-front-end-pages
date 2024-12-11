<!-- UserLogin.vue -->
<script setup  >
import LoginForm from "@/components/login/LoginForm.vue";
import RegisterForm from "@/components/login/RegisterForm.vue";
import ForgotPasswordForm from "@/components/login/ForgotPasswordForm.vue";
import {computed, ref} from "vue";

const currentView = ref('login')

const particlesLoaded = async container => {
  console.log("Particles container loaded", container);
};

const changeView = (view) => {
  currentView.value = view
}

const currentViewComponent = computed(() => {
  switch (currentView.value) {
    case 'register':
      return RegisterForm
    case 'forgotPassword':
      return ForgotPasswordForm
    default:
      return LoginForm
  }
})

</script>

<template>
  <div class="app">

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
                            value: '#fff300'
                        },
                        links: {
                            color: '#f8f691',
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
    <component :is="currentViewComponent" @changeView="changeView" />
  </div>
</template>

<style scoped>
.app{
  background-image: url("@/assets/img/login.png");
  height: 100%;
  width: 100%;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  position: fixed;
  top: 0;
  left: 0;

}

</style>