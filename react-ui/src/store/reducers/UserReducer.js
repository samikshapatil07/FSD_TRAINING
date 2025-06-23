//src/store/reducers/UserReducer

const initialState = {
    username: "",
    role: ""
}
const UserReducer =(state = initialState, action) =>{
//login
    if(action.type === "SET_USER_DETAILS"){
        let user = action.payload;
        return{
            ...state,
            username : user.username,
            role : user.role
        }
    }
//logout
    if(action.type === "DELETE_USER_DETAILS"){
        return{
            ...state,
            username :"",
            role : ""
        }

    }
    return state;

}
export default UserReducer;

/*-------------- before calling api -----------------
*reducer fn has 2 arguments :  state and action
1. STATE : to begin with state has username and role as ""
state.username = ""
state.role = ""
this stste will go to redux store...so store will have the object like this:
 {
username: "",
    role: """
}

2. ACTION: when the api eill be called from action file, it will dispatch a obj that
comes and gets saved in this action variable.
action.type =
{
username: "",
    role: """
}

 */