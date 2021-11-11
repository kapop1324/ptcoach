export default {
    getLoginEmail(state) {
        return state.userEmail
    },
    getCourseList(state) {
        return state.courseList
    },
    getExerciseList(state) {
        return state.exerciseList
    },
    getRecord(state) {
        return state.record
    },
    getIsLogin(state) {
        return state.isLogin
    },
}