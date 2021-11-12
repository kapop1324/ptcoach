export default {
  login: function({commit}, res){
    commit('LOGIN', res)
  },
  logout: function({commit}){
    commit('LOGOUT')
  },
  getCourseList: function({commit}, res){
    commit('GETCOURSELIST', res)
  },
  fetch_exercise_list: function ({ commit }, res) {
    commit('FETCH_EXERCISE_LIST',res)
  },
}
