import React, { useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import PopUp from "./PopUp";
import { Calendar } from 'primereact/calendar';


function ScheduleInterview() {
    const { id } = useParams(); // application id
    const navigate = useNavigate();
      const [date, setDate] = useState(null);


    const [form, setForm] = useState({
        interviewDate: "",
        interviewLocation: "",
        interviewMode: "ONLINE",
        outcome: "PENDING"
    });

        const [msg, setMsg] = useState("");
    const [showToast, setShowToast] = useState(false);


    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        
        try {
            form.interviewDate = date.toISOString().split("T")[0];

            await axios.post(
                `http://localhost:8080/api/interviews/schedule/application/${id}`,
                form,
                {
                    headers: { Authorization: "Bearer " + localStorage.getItem("token") }
                }
            );
                        setMsg("Interview Scheduled Successfully");
            setShowToast(true);

                        setTimeout(() => {
                navigate("/hr/applications", { state: { showToast: true } });
            }, 1500);
        } catch (err) {
            console.error("Error scheduling interview", err);
            alert("Error scheduling interview");
        }
    };

    return (
<div>
        {showToast && <PopUp message={msg} />}
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
                            type="text"
                            name="interviewLocation"
                            className="form-control"
                            value={form.interviewLocation}
                            onChange={handleChange}
                            required
                        />
                    </div>

                        <div className="mb-3">
                        <label className="form-label">Outcome</label>
                        <input
                            type="text"
                            className="form-control"
                            value={form.outcome}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Interview Mode</label>
                        <select
                            name="interviewMode"  className="form-select" value={form.interviewMode} onChange={handleChange}>
                            <option value="ONLINE">ONLINE</option>
                            <option value="OFFLINE">OFFLINE</option>
                        </select>
                    </div>

                    <button type="submit" className="btn btn-primary" onClick={handleSubmit}>Schedule Interview</button>
                    <button type="button"className="btn btn-secondary ms-2" onClick={() => navigate(`/hr/applications`)}>Cancel</button>
   
                </form>
            </div>
        </div>
        </div>
    );
}

export default ScheduleInterview;
