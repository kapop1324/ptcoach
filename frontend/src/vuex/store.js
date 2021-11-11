import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

let state = {
    userEmail: '',
    courseList: [],
    exerciseList: [],
    record: [{
        exercise_idx:0,
        time: 0,
        accuracy: 0,
    }],
}

export default new Vuex.Store({
    plugins:[
        createPersistedState()
    ],
    state,
    mutations,
    getters,
    actions
})