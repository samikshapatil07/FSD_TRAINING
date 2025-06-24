import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState } from 'react'
import Home from "./components/Home";
import UserList from "./components/UserList";
import AddUser from "./components/AddUser";
import EditUser from "./components/EditUser";

function App() {
  //implementing router
   return (
    
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} ></Route>
        <Route path="userlist" element={<UserList />} ></Route>
        <Route path="adduser" element={<AddUser />} ></Route>
        <Route path="edituser" element={<EditUser />} ></Route>
        
      </Routes>
    </BrowserRouter>
  )
}

export default App;
