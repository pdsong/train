import { createStore } from 'vuex'

const MEMBER="MEMBER";
export default createStore({
  state: {
    //去缓存里读 有就取出来 没有就是{}
    member:window.SessionStorage.get(MEMBER) || {}
  },
  getters: {
  },
  mutations: {
    setMember(state,member){
      state.member=member
      window.SessionStorage.set(MEMBER,member)
    }
  },
  actions: {
  },
  modules: {
  }
})
