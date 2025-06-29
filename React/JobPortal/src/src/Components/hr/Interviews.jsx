import { useEffect, useRef, useState } from "react";
import axios from "axios";
import { Toast } from "primereact/toast";

// PrimeReact CSS imports (required for toast styling)
// import "primereact/resources/themes/lara-light-blue/theme.css";
// import "primereact/resources/primereact.min.css";

function Interviews() {
  const [interviews, setInterviews] = useState([]); // Store all fetched interviews
  const toast = useRef(null); 

  // fetch interviews once when component mounts
  useEffect(() => {
    fetchInterviews();
  }, []);

  // schedule interview api
  const fetchInterviews = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/interviews/by-hr", {
        headers: { Authorization: "Bearer " + localStorage.getItem("token") },
      });
      setInterviews(res.data);
    } catch (err) {
      console.error("Error fetching interviews", err);
      toast.current.show({
        severity: "error",
        detail: "Failed to fetch interviews",
        life: 3000,
      });
    }
  };

  // Update interview status (outcome) when HR changes selection
  const updateStatus = async (interviewId, newStatus) => {
    try {
      await axios.post(
        `http://localhost:8080/api/interviews/update/outcome/interviewId/${interviewId}`,
        { outcome: newStatus },
        {
          headers: { Authorization: "Bearer " + localStorage.getItem("token") },
        }
      );

      // show toast on successful update
      toast.current.show({
        severity: "success",
        detail: "Interview status updated!",
        life: 2000,
      });

      fetchInterviews(); 
    }
     catch (err) 
     {
      console.log( err);
      toast.current.show({
        severity: "error",
        detail: "Could not update interview status.",
        life: 3000,
      });
    }
  };

  return (
    <div className="container mt-4">
      <Toast ref={toast} />

      <h4 className="mb-3 fw-bold">All Scheduled Interviews</h4>

      <div className="table-responsive">
        <table className="table table-bordered">
          <thead className="table-dark">
            <tr>
              <th>Interview ID</th>
              <th>Application ID</th>
              <th>Date</th>
              <th>Location</th>
              <th>Mode</th>
              <th>Status</th>
              <th>Update</th>
            </tr>
          </thead>
          <tbody>
            {
            interviews.map((interview) => (
              <tr key={interview.interviewId}>
                <td>{interview.interviewId}</td>
                <td>{interview.applicationId}</td>
                <td>{interview.interviewDate}</td>
                <td>{interview.interviewLocation}</td>
                <td>{interview.interviewMode}</td>
                <td>{interview.outcome}</td>
                <td>
                  {/* Dropdown to update interview outcome */}
                  <select
                    className="form-select"
                    value={interview.outcome}
                    onChange={(e) => updateStatus(interview.interviewId, e.target.value)} >
                  
                    <option value="PENDING">PENDING</option>
                    <option value="PASSED">PASSED</option>
                    <option value="FAILED">FAILED</option>
                  </select>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Interviews;
