import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import PopUp from "./PopUp";

function Interviews() {
    const [interviews, setInterviews] = useState([]);
    const [msg, setMsg] = useState("");
    const [showToast, setShowToast] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        fetchInterviews();
    }, []);

    const fetchInterviews = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/interviews/by-hr", {
                headers: { Authorization: "Bearer " + localStorage.getItem("token") },
            });
            setInterviews(res.data);
        } catch (err) {
            console.error("Error fetching interviews", err);
        }
    };

    const updateStatus = async (interviewId, newStatus) => {
        try {
            await axios.post(`http://localhost:8080/api/interviews/update/outcome/interviewId/${interviewId}`,
                { outcome: newStatus },
                 {
                headers: { Authorization: "Bearer " + localStorage.getItem("token") }
            });
            setMsg("Interview status updated!");
            setShowToast(true);
            fetchInterviews(); // Refresh
        } catch (err) {
            console.error("Error updating status", err);
        }
    };

    return (
        <div className="container mt-4">
            {showToast && <PopUp message={msg} />}
            <h4 className="mb-3 fw-bold">All Scheduled Interviews </h4>
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
                        {interviews.map((interview) => (
                            <tr key={interview.interviewId}>
                                <td>{interview.interviewId}</td>
                                <td>{interview.applicationId}</td>
                                <td>{interview.interviewDate}</td>
                                <td>{interview.interviewLocation}</td>
                                <td>{interview.interviewMode}</td>
                                <td>{interview.outcome}</td>
                                <td>
                                    <select
                                        className="form-select"
                                        value={interview.outcome}
                                        onChange={(e) =>
                                            updateStatus(interview.interviewId, e.target.value)
                                        }
                                    >
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
