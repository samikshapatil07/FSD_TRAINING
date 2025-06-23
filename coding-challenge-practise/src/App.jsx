import { BrowserRouter, Routes, Route } from "react-router-dom";
import React from "react";
import AddUser from "./components/UM-AddUser";
import Home from "./components/UM-Home";
import Add from "./components/AddAlbum";
import AlbumListCard from "./components/AlbumListCard";
import AlbumListById from "./components/AlbumListbyId";
import AlbumListTable from "./components/AlbumListTable";



function App() {

  return (
    
    <BrowserRouter>
      <Routes>
        {/* for sample coding challenge */}
        <Route path="/" element={<Home />}> </Route>
        {/* <Route path="adduser" element={<AddUser />}> </Route>
        <Route path="userlist" element={<UserList />}> </Route> */}
        <Route path="addalbum" element={<Add />}> </Route>
        <Route path="getintable" element={<AlbumListTable />}> </Route>
        <Route path="getincard" element={<AlbumListCard />}> </Route>
        <Route path="getbyid" element={<AlbumListById />}> </Route>

      </Routes>
    </BrowserRouter>
  )
}

export default App;
