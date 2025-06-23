import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "../../css/postjob.css";
import PopUp from "./PopUp";

function EditJob() {
  const navigate = useNavigate();
  const { jobId } = useParams();
  const [msg, setMsg] = useState("");
  const [showToast, setShowToast] = useState(false);

  const [title, setTitle] = useState("");
  const [company, setCompany] = useState("");
  const [location, setLocation] = useState("");
  const [salaryRange, setSalaryRange] = useState("");
  const [experience, setExperience] = useState("");
  const [skills, setSkills] = useState("");
  const [department, setDepartment] = useState("");
  const [description, setDescription] = useState("");

  // api to get existing job data (get job by is api)
  useEffect(() => {
    const fetchJob = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/jobs/jobId/${jobId}`, {
          headers: { Authorization: "Bearer " + localStorage.getItem("token") },
        });
        const job = response.data;
        setTitle(job.jobTitle);
        setCompany(job.company);
        setLocation(job.location);
        setSalaryRange(job.salary);
        setExperience(job.experience);
        setSkills(job.skills);
        setDepartment(job.department);
        setDescription(job.description);
      } catch (error) {
        console.error("Failed to load job:", error);
        setMsg("Failed to load job details.");
        setShowToast(true);
      }
    };

    fetchJob();
  }, [jobId]);

  // update job api
  const updateJob = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:8080/api/jobs/update/${jobId}`, {
        jobTitle: title,
        company,
        location,
        salary: salaryRange,
        experience,
        skills,
        department,
        description,
      }, {
        headers: { Authorization: "Bearer " + localStorage.getItem("token") },
      });

      setMsg("Job updated successfully!");
      setShowToast(true);
      setTimeout(() => {
        navigate("/hr/jobs", { state: { showToast: true } });
      }, 1500);
    } catch (error) {
      console.error("Update Job Error:", error);
      setMsg("Failed to update job.");
      setShowToast(true);
    }
  };

  return (
    <div>
      {showToast && <PopUp />}
      <div className="modal-overlay">
        <div className="modal-card modal-large">
          <button className="close-btn" onClick={() => navigate("/hr/jobs")}>×</button>
          <h4 className="fw-bold">Edit Job</h4>
          <form onSubmit={updateJob}>
            {/* in form the values are pre field as job details as we are even calling git job by id aip */}
            <div className="row">
              <div className="col-md-6 mb-3">
                <label>Job Title</label>
                <input type="text" value={title} className="form-control" onChange={(e) => setTitle(e.target.value)} required />
              </div>

              <div className="col-md-6 mb-3">
                <label>Company</label>
                <input type="text" value={company} className="form-control" onChange={(e) => setCompany(e.target.value)} required />
              </div>

              <div className="col-md-6 mb-3">
                <label>Location</label>
                <input type="text" value={location} className="form-control" onChange={(e) => setLocation(e.target.value)} required />
              </div>

              <div className="col-md-6 mb-3">
                <label>Salary Range</label>
                <input type="text" value={salaryRange} className="form-control" onChange={(e) => setSalaryRange(e.target.value)} required />
              </div>

              <div className="col-md-6 mb-3">
                <label>Experience Required</label>
                <input type="text" value={experience} className="form-control" onChange={(e) => setExperience(e.target.value)} required />
              </div>

              <div className="col-md-6 mb-3">
                <label>Required Skills</label>
                <input type="text" value={skills} className="form-control" onChange={(e) => setSkills(e.target.value)} required />
              </div>

              <div className="col-md-12 mb-3">
                <label>Department</label>
                <input type="text" value={department} className="form-control" onChange={(e) => setDepartment(e.target.value)} required />
              </div>

              <div className="col-md-12 mb-3">
                <label>Job Description</label>
                <textarea value={description} className="form-control" onChange={(e) => setDescription(e.target.value)} required></textarea>
              </div>

              <div className="col-md-12">
                <button type="submit" className="btn btn-dark mt-3 w-100">Update Job</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default EditJob;
