import React, { useEffect, useState } from "react";
import axios from "axios";
import { Paginator } from 'primereact/paginator';
import ApplyJob from './ApplyJob';
import { useNavigate } from 'react-router-dom';


function GetAllJobs() {
  const [jobs, setJobs] = useState([]);
  const [first, setFirst] = useState(0);
  const [rows, setRows] = useState(6);
  const [selectedJobId, setSelectedJobId] = useState(null);
  const navigate = useNavigate();


  useEffect(() => {
    const getAllJobs = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/jobs/all");
        setJobs(response.data);
      } catch (err) {
        console.error("Error fetching jobs", err);
      }
    };
    getAllJobs();
  }, []);

  const onPageChange = (event) => {
    setFirst(event.first);
    setRows(event.rows);
  };

  const paginatedJobs = jobs.slice(first, first + rows);

  const handleApplyClick = (jobId) => {
    setSelectedJobId(jobId);
  };

  const closeModal = () => {
    setSelectedJobId(null);
  };

  return (
    <>
        <h2 className="fw-bold my-4 text-center">All Job Listings</h2>

      <div className="container">
        <div className="row">
          {paginatedJobs.map((job, index) => (
            <div className="col-md-4 mb-4" key={index}>
              <div className="card shadow-sm h-100">
                <div className="position-absolute top-0 end-0 m-2 badge bg-secondary">
                  Job ID: {job.jobId}
                </div>
                <div className="card-body">
                  <h5 className="card-title fw-bold">{job.jobTitle}</h5>
                  <h6 className="card-subtitle mb-2 text-muted">{job.company}</h6>
                  <p className="card-text">
                    <i className="pi pi-map-marker me-2 text-secondary" />
                    {job.location}
                  </p>
                  <p className="card-text"><strong>Experience:</strong> {job.experience}</p>
                  <p className="card-text"><strong>Skills:</strong> {job.skills}</p>
                  <p className="card-text"><strong>Department:</strong> {job.department}</p>
                  <p className="card-text"><strong>Salary:</strong> ₹{job.salary}</p>
                  <p className="card-text text-muted"><small>Posted on: {new Date(job.createdAt).toLocaleDateString()}</small></p>
                  <button className="btn btn-primary m-2" onClick={() => navigate(`/jobseeker/applyjob/${job.jobId}`)}> Apply </button> 
                  <button className="btn btn-outline-danger m-2" > Withdraw </button> 

                                           
                </div>
              </div>
            </div>
          ))}
        </div>

        <div className="d-flex justify-content-center mt-4">
          <Paginator
            first={first}
            rows={rows}
            totalRecords={jobs.length}
            onPageChange={onPageChange}
            template="FirstPageLink PrevPageLink CurrentPageReport NextPageLink LastPageLink"
          />
        </div>
      </div>

      {/* Show modal if a job is selected */}
      {selectedJobId && <ApplyJob jobId={selectedJobId} onClose={closeModal} />}
    </>
  );
}

export default GetAllJobs;
