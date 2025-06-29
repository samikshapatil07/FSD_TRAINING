import React, { useState, useRef } from "react";
import { useNavigate, Link } from "react-router-dom";
import "../css/signup.css";
import axios from "axios";
import { Toast } from "primereact/toast";


function Signup({ onClose }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [name, setName] = useState("");
  const [companyName, setCompanyName] = useState("");
  const [education, setEducation] = useState("");
  const [skills, setSkills] = useState("");
  const [experience, setExperience] = useState("");
  const toast = useRef(null);
  const navigate = useNavigate();

  const processSignup = async (e) => {
    e.preventDefault();
    try {
      let endpoint = "";
      let payload = {};

      if (role === "hr") {
        endpoint = "http://localhost:8080/api/hr/register";
        payload = {
          name,
          companyName,
          user: { username, password },
        };
      } else if (role === "jobseeker") {
        endpoint = "http://localhost:8080/api/jobseeker/register";
        payload = {
          name,
          education,
          skills,
          experience,
          user: { username, password },
        };
      } else {
        throw new Error("Please select a valid role.");
      }

      await axios.post(endpoint, payload);

      toast.current.show({
        severity: "success",
        summary: "Signup Successful",
        detail: "Your account has been created",
        life: 2000,
      });

      setTimeout(() => {
        if (onClose) onClose();
        navigate("/");
      }, 1500);
    } catch (err) {
      console.error(err);
      toast.current.show({
        severity: "error",
        summary: "Signup Failed",
        detail: "Please try again",
        life: 3000,
      });
    }
  };

  return (
    <div className="modal-overlay">
      <Toast ref={toast} />

      <div className="modal-card">
        <button className="close-btn" onClick={onClose}>×</button>
        <h3>Job Portal</h3>
        <p className="text-muted mb-3">Sign up to create your account</p>

        <form onSubmit={processSignup}>
          <div className="mb-3 text-start">
            <label>Role</label>
            <select
              className="form-control"
              value={role}
              onChange={(e) => setRole(e.target.value)}
              required
            >
              <option value="">Select your role</option>
              <option value="jobseeker">Job Seeker</option>
              <option value="hr">HR</option>
            </select>
          </div>

          <div className="mb-3 text-start">
            <label>Email</label>
            <input
              type="email"
              className="form-control"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>

          <div className="mb-3 text-start">
            <label>Password</label>
            <input
              type="password"
              className="form-control"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          {/* HR Fields */}
          {role === "hr" && (
            <>
              <div className="mb-3 text-start">
                <label>Full Name</label>
                <input
                  className="form-control"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  required
                />
              </div>
              <div className="mb-3 text-start">
                <label>Company Name</label>
                <input
                  className="form-control"
                  value={companyName}
                  onChange={(e) => setCompanyName(e.target.value)}
                  required
                />
              </div>
            </>
          )}

          {/* Job Seeker Fields */}
          {role === "jobseeker" && (
            <>
              <div className="mb-3 text-start">
                <label>Full Name</label>
                <input
                  className="form-control"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  required
                />
              </div>
              <div className="mb-3 text-start">
                <label>Education</label>
                <input
                  className="form-control"
                  value={education}
                  onChange={(e) => setEducation(e.target.value)}
                  required
                />
              </div>
              <div className="mb-3 text-start">
                <label>Skills (comma separated)</label>
                <input
                  className="form-control"
                  value={skills}
                  onChange={(e) => setSkills(e.target.value)}
                  required
                />
              </div>
              <div className="mb-3 text-start">
                <label>Experience</label>
                <input
                  className="form-control"
                  value={experience}
                  onChange={(e) => setExperience(e.target.value)}
                  required
                />
              </div>
            </>
          )}

          <button type="submit" className="btn btn-dark w-100">Sign Up</button>
        </form>

        <div className="mt-3">
          <p>
            Already have an account? <Link to="/login">Login here.</Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Signup;
