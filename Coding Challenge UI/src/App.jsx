import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState } from 'react'
import Home from "./components/Home";
import UserList from "./components/UserList";

function App() {
  //implementing router
   return (
    
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} ></Route>
        <Route path="userlist" element={<UserList />} ></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App;
