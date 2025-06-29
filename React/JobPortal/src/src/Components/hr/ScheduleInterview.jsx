import React, { useState, useRef } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { Calendar } from "primereact/calendar";
import { Toast } from "primereact/toast";
import { format } from "date-fns"; // import format for date
import "primereact/resources/themes/lara-light-blue/theme.css";
import "primereact/resources/primereact.min.css";

function ScheduleInterview() {
    const { id } = useParams(); // application ID
    const navigate = useNavigate();
    const toast = useRef(null);
    const [date, setDate] = useState(null);

    const [form, setForm] = useState({
        interviewDate: "",
        interviewLocation: "",
        interviewMode: "ONLINE",
        outcome: "PENDING",
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async () => {
        // format date into YYYY-MM-DD
        form.interviewDate = format(date, "yyyy-MM-dd");

        try {
            await axios.post(
                `http://localhost:8080/api/interviews/schedule/application/${id}`,
                form,
                {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                }
            );

            toast.current.show({
                severity: "success",
                detail: "Interview Scheduled Successfully",
                life: 2000,
            });

            setTimeout(() => {
                navigate("/hr/applications", { state: { showToast: true } });
            }, 1500);
        }
        catch (err)
         {
            console.error("Error scheduling interview", err);
            toast.current.show({
                severity: "error",
                detail: "Failed to schedule interview",
                life: 3000,
            });
        }
    };

    return (
        <div>
            <Toast ref={toast} />
            <div className="container mt-4">
                <div className="card shadow p-4">
                    <h4 className="fw-bold mb-4">Schedule Interview</h4>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label">Interview Date</label>
                            {/* <input
                            type="date"
                            name="interviewDate"
                            className="form-control"
                            value={form.interviewDate}
                            onChange={handleChange}
                            required
                        /> */}
                            <Calendar value={date} onChange={(e) => setDate(e.value)} />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Interview Location</label>
                            <input
                                type="text" name="interviewLocation" className="form-control"
                                value={form.interviewLocation} onChange={handleChange} required />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Outcome</label>
                            <input
                                type="text" className="form-control"
                                value={form.outcome} onChange={handleChange}  required />  
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Interview Mode</label>
                            <select
                                name="interviewMode" className="form-select" value={form.interviewMode} onChange={handleChange}>
                                <option value="ONLINE">ONLINE</option>
                                <option value="OFFLINE">OFFLINE</option>
                            </select>
                        </div>

                        <button type="submit" className="btn btn-primary" onClick={handleSubmit}>Schedule Interview</button>
                        <button type="button" className="btn btn-secondary ms-2" onClick={() => navigate(`/hr/applications`)}>Cancel</button>

                    </form>
                </div>
            </div>
        </div>
    );
}

export default ScheduleInterview;
