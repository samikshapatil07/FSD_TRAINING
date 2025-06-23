import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState } from 'react'

import Login from './Components/Login'
import HrDashboard from "./Components/Hr/HrDashboard";
import JobseekerDashboard from "./Components/jobseeker/JobSeekerDashboard";
import Signup from "./Components/Signup";
import HomePage from "./Components/homepage/HomePage";
import PostJob from "./Components/hr/PostJob";
import HrNavbar from "./Components/Hr/HrNavbar";
import PopUp from "./Components/hr/PopUp";
import HrSidebar from "./Components/hr/HrSidebar";
import UpdateProfile from "./Components/hr/UpdateProfile";
import Interviews from "./Components/hr/Interviews";
import Jobs from "./Components/hr/Jobs";
import HrHome from "./Components/hr/HrHome";
import DeleteJob from "./Components/hr/DeleteJob";
import EditJob from "./Components/hr/EditJob";
import ApplicationList from "./Components/hr/ApplicationList";
import ViewApplication from "./Components/hr/ViewApplication";
import ScheduleInterview from "./Components/hr/ScheduleInterview";

import JsNavbar from "./Components/jobseeker/JsNavbar";
import JsSidebar from "./Components/jobseeker/JsSidebar";
import JobsList from "./Components/jobseeker/JobsList";
import JsHome from "./Components/jobseeker/JsHome";
import ApplyJob from "./Components/jobseeker/ApplyJob";
import MyApplications from "./Components/jobseeker/MyApplications";
import MyInterviews from "./Components/jobseeker/MyInterviews";

function App() {
  //implementing router
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<Login />} ></Route>
          <Route path="/signup" element={<Signup />} ></Route>

          <Route path="/hr" element={<HrDashboard />}>
            <Route path="postjob" element={<PostJob />} />
            <Route path="hrnavbar" element={<HrNavbar />} />
            <Route index element={<HrHome />} /> 
            <Route path="jobs" element={<Jobs/>} />
            <Route path="hrsidebar" element={<HrSidebar />} />
            <Route path="popup" element={<PopUp />} />
            <Route path="updateprofile" element={<UpdateProfile />} />
            <Route path="deletejob/:jobId" element={<DeleteJob />} />
            <Route path="editjob/:jobId" element={<EditJob />} />
            <Route path="applications" element={<ApplicationList />} />
            <Route path="applications/:id" element={<ViewApplication />} />
            <Route path="schedule-interview/:id" element={<ScheduleInterview />} />
            <Route path="interviews" element={<Interviews />} /> 
          </Route>

          <Route path="/jobseeker" element={<JobseekerDashboard />}>
          <Route index element={<JsHome />} />
            <Route path="jsnavbar" element={<JsNavbar />} />
            <Route path="jssidebar" element={<JsSidebar />} />
            <Route path="jobslist" element={<JobsList />} />
            <Route path="applyjob/:jobId" element={<ApplyJob />} />
            <Route path="myapplications" element={<MyApplications />} />
            <Route path="interviews" element={<MyInterviews />} />

            

            

            
          </Route>
          
        </Routes>
      </BrowserRouter>

    </div>
  )
}

export default App;
