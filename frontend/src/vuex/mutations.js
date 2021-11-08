export default {
    LOGIN: function (state, res) {
        state.userEmail = res
    },
    LOGOUT: function(state){
        state.userEmail = ''
    },
    GETCOURSELIST: function(state, res){
        state.courseList = res
    },
    FETCH_EXERCISE_LIST: function (state, res) {
        state.exerciseList = res
    },
}