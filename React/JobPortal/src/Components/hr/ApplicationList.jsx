import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function ApplicationList() {
  const [applications, setApplications] = useState([]);
  const navigate = useNavigate();

  //api to get all applications
  const getAllApplications = async () => {
    try {
      let response = await axios.get("http://localhost:8080/api/applications/get-all", {
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


  return (
    <div className="container mt-4">
      <h1 className="mb-4">Applications</h1>

      {applications.map((app, index) => (
        <div className="card mb-4 shadow-sm p-3 position-relative" key={index}>
          <h5 className="fw-bold">Application ID: {app.applicationId}</h5>
          <p><strong>Name:</strong> {app.jobSeeker.name}</p>
          <p><strong>Status:</strong> {app.status}</p>

          <button className="btn btn-outline-primary" onClick={() => navigate(`/hr/applications/${app.applicationId}`)}>View Details</button>
<button
  className="btn btn-danger position-absolute top-0 end-0 m-2"
  onClick={() => updateStatus(app.applicationId)}
  disabled={app.status === "APPLICATION_REJECTED"}
>
  Reject
</button>
        </div>
      ))}
    </div>
  );
}

export default ApplicationList;
