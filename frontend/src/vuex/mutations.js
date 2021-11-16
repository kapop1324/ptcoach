export default {
    LOGIN: function (state, res) {
        state.userEmail = res
        state.isLogin = true
    },
    LOGOUT: function(state){
        state.userEmail = ''
        state.isLogin = false
    },
    GETCOURSELIST: function(state, res){
        state.courseList = res
    },
    FETCH_EXERCISE_LIST: function (state, res) {
        state.exerciseList = res
    },
    ADD_EXERCISE_RECORD: function (state, res) {
        state.record.push(res)
    },
}