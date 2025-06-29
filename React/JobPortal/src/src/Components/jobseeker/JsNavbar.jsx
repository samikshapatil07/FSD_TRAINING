import React, { useState } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

function JsNavbar() {

  const navigate = useNavigate();
  const location = useLocation();


    const getActiveTab = (path) => {
    return location.pathname === path ? "active" : "";
  };


  return (
    <nav className="navbar navbar-expand-lg bg-white shadow-sm px-4">
      <div className="container-fluid d-flex justify-content-between align-items-center">
        <a className="navbar-brand fw-bold fs-4 text-dark" href="#">
          CareerCrafter
        </a>

        <div className="d-flex justify-content-center flex-grow-1">
          <ul className="nav nav-underline gap-4">
            <li className="nav-item">
              <a className={`nav-link fs-5 ${getActiveTab("/jobseeker")}`} href="#" onClick={() => navigate("/jobseeker")}>Home</a>
            </li>
            <li className="nav-item">
              <a className={`nav-link fs-5 ${getActiveTab("/jobseeker/jobslist")}`} href="#" onClick={() => navigate("/jobseeker/jobslist")}>Jobs</a>
            </li>
            <li className="nav-item">
              <a className={`nav-link fs-5 ${getActiveTab("/jobseeker/myapplications")}`} href="#" onClick={() => navigate("/jobseeker/myapplications")}>My Applications</a>
            </li>
            <li className="nav-item">
              <a className={`nav-link fs-5 ${getActiveTab("/jobseeker/interviews")}`} href="#" onClick={() => navigate("/jobseeker/interviews")}>My Interviews</a>
            </li>
          </ul>
        </div>

        {/* rpofile icon side bar */}
        <div>
          <button className="btn p-0 border-0" data-bs-toggle="offcanvas" data-bs-target="#profileSidebar">
            <img src="images/profile.jpg" alt="Profile" className="rounded-circle" style={{ width: "40px", height: "40px" }} />
          </button>
        </div>
      </div>
    </nav>
  );
}

export default JsNavbar;
