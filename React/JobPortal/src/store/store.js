// i will configure store here..
// src/store/store.js

import {configureStore} from "@reduxjs/toolkit"
import UserReducer from "../../../react-ui/src/store/reducers/UserReducer";

//register all reducers
const store = configureStore({
    reducer : {
        user :UserReducer //we will come back later
    }

})

export default store;