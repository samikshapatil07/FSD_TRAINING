//src/store/action/UserAction.js
//this is not an component..its a normal function..so no return function
 export const setUserDetails = (dispatch) => (user) => {
    dispatch({
        'payload' : user,
        'type' : 'SET_USER_DETAILS' 
    })

 }
 export const deleteUserDetails =(dispatch) => {
     dispatch({
        'payload' : user,
        'type' : 'DELETE_USER_DETAILS' 
    })

 }

 /**
  * user =
  * {
     username : "severus@gmail.com"
     role : "AUTHOR"
    }
     //creating hook in ccomponent..saving in dispatch vatiable
     const dispatch = useDispatch();
     setUserDetails(dispatch,user)

     this object gets attached to action variable
  */