import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter',{
  state: () => ({
    email:null,
  }),
  actions:{
    setUser(userData){
        this.email = userData;
    },
    logout(){
      this.email = null;
    }
  }
})