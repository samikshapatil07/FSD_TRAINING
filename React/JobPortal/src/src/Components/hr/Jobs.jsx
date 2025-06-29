import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";


function Jobs() {

    const [jobs, setJobs] = useState([]);
      const navigate = useNavigate();


// api to get all jobs
    useEffect(() => {
        const getAllJobs = async () => {
            try {
                let response = await axios.get('http://localhost:8080/api/jobs/by-hr', {
                    headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
                });
                setJobs(response.data);
            } 
            catch (err)
             {
                console.error("Failed to fetch jobs:", err);
            }
        };
        getAllJobs();
    }, []);

    return (
        <div>
        <div className="container mt-4">
            <h1 className="mb-4">Job Listings</h1>
            {
                jobs.map((job, index) => (
                    <div className="card mb-4 shadow-sm p-3" key={index}>
                        <div className="d-flex justify-content-between align-items-start mb-2">
                            <h4 className="fw-bold">{job.jobTitle} - {job.jobId} </h4>
                            <small className="text-muted"> Posted on: {new Date(job.createdAt).toLocaleDateString()} </small>
                                {/* ask sir about displaying date/time for post posted */}
                        </div>
                        <p className="text-muted">{job.company} · {job.location}</p>

                           <strong>Description</strong>
                           <p>{job.description}</p>

                        <div className="row">
                            <div className="col-md-4">
                                <strong>Experience</strong>
                                <p>{job.experience}</p>
                            </div>

                            <div className="col-md-4">
                                <strong>Salary</strong>
                                <p>{job.salary}</p>
                            </div>

                            <div className="col-md-4">
                                <strong>Department</strong>
                                <p>{job.department}</p>
                            </div>
                            
                            <div className="mb-3">
                                <strong>Skills</strong>
                                <p>{job.skills}</p>
                            </div>
                        </div>

                        <div className="d-flex justify-content-start gap-2">
                            <button onClick={() => navigate(`/hr/editjob/${job.jobId}`)} className="btn btn-outline-dark">Edit</button>
                            <button className="btn btn-outline-danger" onClick={() => navigate(`/hr/deletejob/${job.jobId}`)}>
                                <i className="bi bi-trash"></i>
                            </button>
                        </div>
                    </div>
                ))
            }
        </div>
        </div>
    );
}

export default Jobs;
