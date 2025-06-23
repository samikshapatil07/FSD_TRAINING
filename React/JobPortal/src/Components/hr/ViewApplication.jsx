import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";


function ViewApplication() {
    const { id } = useParams();
    const [application, setApplication] = useState(null);
     const navigate = useNavigate();

    useEffect(() => {
        const fetchApplication = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/applications/${id}`, {
                    headers: { Authorization: "Bearer " + localStorage.getItem("token") },
                });
                setApplication(response.data);
            } catch (err) {
                console.error("Error fetching application", err);
            }
        };

        fetchApplication();
    }, [id]);

    return (
        <div className="container mt-4">
            <div className="card mb-4 shadow-sm p-3">
                <h4 className="fw-bold">Application ID: {application?.applicationId} </h4>
                <form>
                    <div className="row">
                        <div className="col-md-6 mb-3">
                            <label className="form-label">Status</label>
                            <input type="text" className="form-control" value={application?.status} disabled />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Job Title</label>
                            <input type="text" className="form-control" value={application?.jobTitle} disabled />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Job ID</label>
                            <input type="text" className="form-control" value={application?.jobId } disabled />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Job Seeker Name</label>
                            <input type="text" className="form-control" value={application?.jobSeeker?.name } disabled />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Education</label>
                            <input type="text" className="form-control" value={application?.jobSeeker?.education} disabled />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Experience</label>
                            <input type="text" className="form-control" value={application?.jobSeeker?.experience } disabled />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Skills</label>
                            <input type="text" className="form-control" value={application?.jobSeeker?.skills } disabled />
                        </div>

                    </div>
                    <button type="button" className="btn btn-outline-primary" onClick={() => navigate("/hr/applications")}>View Resume </button> <br />
                    <button type="button" className="btn btn-secondary mt-3" onClick={() => navigate("/hr/applications")}>Close </button>
                    <div className="d-flex justify-content-center mt-4">
                    <button type="button" className="btn btn-success mt-3" onClick={() => navigate(`/hr/schedule-interview/${id}`)}> Schedule Interview </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default ViewApplication;