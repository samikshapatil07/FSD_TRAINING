import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function ApplicationUpdates() {
  const { appId } = useParams(); // application id
  const navigate = useNavigate();
  const [updates, setUpdates] = useState([]);

  useEffect(() => {
    const fetchUpdates = async () => {
      try {
        const res = await axios.get(`http://localhost:8080/api/updates/application/${appId}`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        });
        setUpdates(res.data);
      } catch (err) {
        console.error("Error fetching updates", err);
      }
    };

    fetchUpdates();
  }, [appId]);

  return (
    <div className="container mt-4">
      <h4 className="fw-bold mb-3">Resume Update History</h4>
      <button className="btn btn-secondary mb-3" onClick={() => navigate(-1)}>Back</button>

      <table className="table table-bordered table-hover">
        <thead className="table-dark">
          <tr>
            <th>Update ID</th>
            <th>Resume File</th>
            <th>Updated On</th>
            <th>Application ID</th>
            <th>Job Seeker ID</th>
          </tr>
        </thead>
        <tbody>
          {
            updates.map((update, index) => (
              <tr key={update.updateId}>
                <td>{index + 1}</td>
                <td>{update.updatedResumePath}</td>
                <td>{new Date(update.updatedOn).toLocaleString()}</td>
                <td>{update.applicationId}</td>
                <td>{update.jobSeekerId}</td>
              </tr>
            ))
          }
        </tbody>

      </table>
    </div>
  );
}

export default ApplicationUpdates;
