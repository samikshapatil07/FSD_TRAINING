// i will configure store here..
// src/store/store.js

import {configureStore} from "@reduxjs/toolkit"
import UserReducer from "./reducers/UserReducer";
import CourseReducer from "./reducers/CourseReducer";

//register all reducers
const store = configureStore({
    reducer : {
        user :UserReducer ,
       courses :CourseReducer
    }

})

export default store;