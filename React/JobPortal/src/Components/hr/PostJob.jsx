import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../../css/postjob.css";
import PopUp from "./PopUp";

function PostJob() {
  const [msg, setMsg] = useState("");
  const [showToast, setShowToast] = useState(false);
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [company, setCompany] = useState("");
  const [location, setLocation] = useState("");
  const [salaryRange, setSalaryRange] = useState("");
  const [experience, setExperience] = useState("");
  const [skills, setSkills] = useState("");
  const [department, setDepartment] = useState("");
  const [description, setDescription] = useState("");

  //post job api
  const postJob = async () => {
  
    try {
           await axios.post('http://localhost:8080/api/jobs/add', {
          'jobTitle': title,
          'company': company,
          'location': location,
          'salary': salaryRange,
          'experience': experience,
          'skills': skills,
          'department': department,
          'description': description,
        },
        {
         headers: { "Authorization": "Bearer " + localStorage.getItem('token') }
            })
            
             
      setMsg("Job posted successfully!");
      setShowToast(true);

      setTimeout(() => {
        navigate("/hr", { state: { showToast: true } });
      }, 1500);
    } 
    catch (err) 
    {
      console.error("Post Job Error:", err);
      setMsg("Failed to post job");
      setShowToast(true);
    }
  };

  return (
    <div>
      {showToast && <PopUp message={msg} />}
      <div className="modal-overlay">
        <div className="modal-card modal-large">
          <button className="close-btn" onClick={() => navigate("/hr")}>×</button>
          <h4 className="fw-bold">Post a New Job</h4>
          <p className="text-muted">Fill out the form below to create a new job listing.</p>
          {msg && <div className="alert alert-info">{msg}</div>}

          <form onSubmit={postJob}>
            <div className="row">
              <div className="col-md-6 mb-3">
                <label>Job Title</label>
                 <input type="text" className="form-control" onChange={($e) => setTitle($e.target.value)} required />
              </div>

              <div className="col-md-6 mb-3">
                <label>Company</label>
                <input type="text" className="form-control" onChange={($e) => setCompany($e.target.value)} required />
              </div>

              <div className="col-md-6 mb-3">
                <label>Location</label>
                <input type="text" className="form-control" onChange={($e) => setLocation($e.target.value)} required />
              </div>

              <div className="col-md-6 mb-3">
                <label>Salary Range</label>
                <input type="text" className="form-control" onChange={($e) => setSalaryRange($e.target.value)} required />
              </div>

              <div className="col-md-6 mb-3">
                <label>Experience Required</label>
                <input type="text" className="form-control" onChange={($e) => setExperience($e.target.value)} required />
              </div>

              <div className="col-md-6 mb-3">
                <label>Required Skills</label>
                <input type="text" className="form-control" onChange={($e) => setSkills($e.target.value)} required />
              </div>

              <div className="col-md-12 mb-3">
                <label>Department</label>
                <input type="text" className="form-control" onChange={($e) => setDepartment($e.target.value)}  required />
              </div>

              <div className="col-md-12 mb-3">
                <label>Job Description</label>
                <textarea className="form-control"onChange={($e) => setDescription($e.target.value)} required></textarea>
              </div>

              <div className="col-md-12">
                <button type="submit" className="btn btn-dark mt-3 w-100" >Post Job</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default PostJob;
