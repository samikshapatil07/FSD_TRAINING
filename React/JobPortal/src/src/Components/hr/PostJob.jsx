import { useState, useRef } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Toast } from "primereact/toast";
import "primereact/resources/themes/lara-light-blue/theme.css";
import "primereact/resources/primereact.min.css";
import "../../css/postjob.css";

function PostJob() {
  const toast = useRef(null); // Toast 
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [company, setCompany] = useState("");
  const [location, setLocation] = useState("");
  const [salaryRange, setSalaryRange] = useState("");
  const [experience, setExperience] = useState("");
  const [skills, setSkills] = useState("");
  const [department, setDepartment] = useState("");
  const [description, setDescription] = useState("");

  // post job api
  const postJob = async (e) => {
    e.preventDefault();

    try {
      await axios.post(
        "http://localhost:8080/api/jobs/add",
        {
          jobTitle: title,
          company,
          location,
          salary: salaryRange,
          experience,
          skills,
          department,
          description,
        },
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );

      toast.current.show({
        severity: "success",
        detail: "Job posted successfully!",
        life: 2000,
      });

      setTimeout(() => {
        navigate("/hr", { state: { showToast: true } });
      }, 1500)
    } 
    catch (err) 
    {
      console.log(err)
      toast.current.show({
        severity: "error",
        detail: "Failed to post job",
        life: 3000,
      });
    }
  };

  return (
    <div>
      
 <Toast ref={toast} />
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
