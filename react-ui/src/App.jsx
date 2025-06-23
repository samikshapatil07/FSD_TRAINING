import { BrowserRouter, Routes, Route } from "react-router-dom";
import React from "react";
import Concepts from "./Demo/Concepts";
import Task from "./Demo/Task";
import TodoList from "./Demo/Todo";
import UserDetails from "./Demo/UserDetails";
import Post from "./Demo/Post";
import Albums from "./Demo/Albums";
import AddPost from "./Demo/AddPost";
import Example1 from "./Demo/Example1";
import Example2 from "./Demo/Example2";
import CourseList from "./components/CourseList";
import Login from "./components/Login";
import AuthorDashboard from "./components/author/AuthorDashboard";
import LearnerDashboard from "./components/learner/LearnerDashBoard";
import Stats from "./components/author/Stats";
import Enrollment from "./components/author/Enrollment";
import Profile from "./components/author/Profile";
import Courses from "./components/author/Courses";
import CourseDetails from "./components/author/CourseDetails";
import UploadProfilePic from "./components/author/UploadProfilePic";
import Home from "./Demo/UM-Home";
import AddUser from "./Demo/UM-AddUser";
import UserList from "./Demo/UM-UserList";

function App() {

  return (
    
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} ></Route>
        <Route path="/author" element={<AuthorDashboard />}>
          <Route index element={<Stats />} />
          <Route path="courses" element={<Courses />} />
          <Route path="enrollments" element={<Enrollment />} />
          <Route path="profile" element={<Profile />} />
          <Route path="course-details/:cid" element={<CourseDetails />} />
          <Route path="upload-profile-pic" element={<UploadProfilePic />} />
        </Route>
        <Route path="/learner" element={<LearnerDashboard />}> </Route>


        {/* for sample coding challenge */}
        {/* <Route path="/" element={<UM-Home />}> </Route>
        <Route path="adduser" element={<UM-AddUser />}> </Route>
        <Route path="userlist" element={<UM-UserList />}> </Route> */}
      </Routes>
    </BrowserRouter>
  )
}

export default App;

/**
 * private int count; //0
 * getter and setter 
 * 
 * let [count,setCount]
 * 
 * ()=>{}
 * if u change the value of state variable, I will re-render it. 
 */
