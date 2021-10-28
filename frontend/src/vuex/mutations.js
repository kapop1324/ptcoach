export default {
    LOGIN: function (state, res) {

        state.userEmail = res
        
    },
    LOGOUT: function(state){
    
        state.userEmail = ''
    },

}