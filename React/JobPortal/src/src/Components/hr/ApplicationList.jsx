import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function ApplicationList() {
  const [applications, setApplications] = useState([]);
  const navigate = useNavigate();

  //api to get all applications
  const getAllApplications = async () => {
    try {
      let response = await axios.get("http://localhost:8080/api/applications/for-hr", {
        headers: { Authorization: "Bearer " + localStorage.getItem("token") },
      });
      console.log("Fetched applications:", response.data);
      setApplications(response.data);
    } catch (err) {
      console.error("Error fetching applications", err);
    }
  };

  useEffect(() => {
    getAllApplications();
  }, []);

  const updateStatus = async (applicationId) => {
  try {
    await axios.post(`http://localhost:8080/api/applications/update/status/${applicationId}`,{ status: "APPLICATION_REJECTED" }, {
      headers: { Authorization: "Bearer " + localStorage.getItem("token") }
    });

    
    getAllApplications();
  } 
  catch (err) {
    console.error("Failed to update application status", err);
  }
};



  return (
    <div className="container mt-4">
      <h1 className="mb-4">Applications</h1>

      {applications.map((app, index) => (
        <div className="card mb-4 shadow-sm p-3 position-relative" key={index}>
          <h5 className="fw-bold">Application ID: {app.applicationId}</h5>
          <p><strong>Name:</strong> {app.jobSeeker.name}</p>
          <p><strong>Status:</strong> {app.status}</p>

<div className="d-flex gap-2">
  
   <button className="btn btn-outline-primary" onClick={() => navigate(`/hr/applications/${app.applicationId}`)}> View Details</button>
    <button className="btn btn-outline-secondary" onClick={() => navigate(`/hr/application-updates/${app.applicationId}`)}>View Resume Updates</button>
</div>
  <button className="btn btn-danger position-absolute top-0 end-0 m-2" onClick={() => updateStatus(app.applicationId)} disabled={app.status === "APPLICATION_REJECTED"}>Reject</button>
 </div>
      ))}
    </div>
  );
}

export default ApplicationList;
