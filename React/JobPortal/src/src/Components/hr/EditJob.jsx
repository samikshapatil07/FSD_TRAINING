import axios from "axios";
import { useEffect, useState, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "../../css/postjob.css";
import { Toast } from "primereact/toast";
import "primereact/resources/themes/lara-light-blue/theme.css";
import "primereact/resources/primereact.min.css";

function EditJob() {
  const navigate = useNavigate();
  const { jobId } = useParams();
  const toast = useRef(null); // for showing toast messages

  const [title, setTitle] = useState("");
  const [company, setCompany] = useState("");
  const [location, setLocation] = useState("");
  const [salaryRange, setSalaryRange] = useState("");
  const [experience, setExperience] = useState("");
  const [skills, setSkills] = useState("");
  const [department, setDepartment] = useState("");
  const [description, setDescription] = useState("");

  // fetch the job detailes first
  useEffect(() => {
    const fetchJob = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/jobs/jobId/${jobId}`, {
          headers: { Authorization: "Bearer " + localStorage.getItem("token") },
        })
        const job = response.data;
        setTitle(job.jobTitle);
        setCompany(job.company);
        setLocation(job.location);
        setSalaryRange(job.salary);
        setExperience(job.experience);
        setSkills(job.skills);
        setDepartment(job.department);
        setDescription(job.description);

      }
       catch (err) 
       {
        console.log(err);
        toast.current.show({
          severity: "error",
          detail: "Failed to load job details.",
          life: 3000,
        });
      }
    };

    fetchJob();
  }, [jobId]);

  // then update the job
  const updateJob = async () => {

    try {
      await axios.put(
        `http://localhost:8080/api/jobs/update/${jobId}`,
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
          headers: { Authorization: "Bearer " + localStorage.getItem("token") },
        }
      )

      toast.current.show({
        severity: "success",
        detail: "Job updated successfully!",
        life: 2000,
      })

      setTimeout(() => {
        navigate("/hr/jobs", { state: { showToast: true } })
      }, 1500)
    } 
    catch (err)
     {
      console.log(err);
      toast.current.show({
        severity: "error",
        detail: "Failed to update job.",
        life: 3000,
      })
    }
  }

  return (
    <div>
      <Toast ref={toast} />
      <div className="modal-overlay">
        <div className="modal-card modal-large">
          <button className="close-btn" onClick={() => navigate("/hr/jobs")}>×</button>
          <h4 className="fw-bold">Edit Job</h4>
          <div>
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
                <button type="submit" className="btn btn-dark mt-3 w-100" onClick={updateJob}>Update Job</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EditJob;
