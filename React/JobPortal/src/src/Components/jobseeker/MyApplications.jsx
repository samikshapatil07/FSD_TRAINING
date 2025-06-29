import React, { useEffect, useState } from "react";
import axios from "axios";
import { Stepper } from "primereact/stepper";
import { StepperPanel } from "primereact/stepperpanel";
import { Card } from "primereact/card";
import { useNavigate } from "react-router-dom";

function MyApplications() {
  const [applications, setApplications] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchApplications();
  }, []);

  const fetchApplications = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/applications/for-js", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });
      setApplications(response.data);
    } catch (error) {
      console.error("Error fetching applications:", error);
    }
  };

  const handleWithdraw = async (applicationId) => {
    try {
      await axios.delete(`http://localhost:8080/api/applications/delete/${applicationId}`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });
      alert(`Application ${applicationId} withdrawn successfully`);
      fetchApplications(); 
    }
     catch (error) 
     {
      console.error("Failed to withdraw application", error);
      alert("Failed to withdraw application");
    }
  };

  const getStepFlow = (status) => {
    switch (status) {
      case "APPLIED":
        return { steps: ["Applied"], index: 0 };
      case "APPLICATION_REJECTED":
        return { steps: ["Applied", "Application Rejected"], index: 1 };
      case "INTERVIEW_SCHEDULED":
        return { steps: ["Applied", "Interview Scheduled"], index: 1 };
      case "OFFERED":
        return { steps: ["Applied", "Interview Scheduled", "Offered"], index: 2 };
      case "REJECTED":
        return { steps: ["Applied", "Interview Scheduled", "Rejected"], index: 2 };
      default:
        return { steps: ["Applied"], index: 0 };
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="fw-bold mb-2 text-center">My Applications</h2>

      {applications.map((app) => {
        const flow = getStepFlow(app.status);
        return (
          <Card className="mb-5 shadow position-relative" key={app.applicationId}>
            {
            app.status === "APPLIED" && (
              <div className="d-flex justify-content-end gap-2 position-absolute top-0 end-0 m-3">
                <button className="btn btn-outline-primary" onClick={() => navigate(`/jobseeker/updateapplication/${app.applicationId}`)} >
                  Update Application </button>
      
                 <button className="btn btn-outline-danger" onClick={() => handleWithdraw(app.applicationId)} >
                  Withdraw Application </button>
              </div>
            )}

            <h5 className="fw-bold mb-3">{app.jobPosting.jobTitle}</h5>
            <p><strong>Application ID:</strong> {app.applicationId}</p>
            <p><strong>Job ID:</strong> {app.jobPosting.jobId}</p>
            <p><strong>Company:</strong> {app.jobPosting.company}</p>
            <p><strong>Applied On:</strong> {new Date(app.appliedOn).toLocaleDateString()}</p>
            <p><strong>Status:</strong> {app.status}</p>

            <Stepper activeStep={flow.index} readOnly className="mt-4" orientation="horizontal">
              {
              flow.steps.map((step, index) => (
                <StepperPanel key={index} header={step}>
                  <p>
                    {step === "Applied" && "Your application has been submitted."}
                    {step === "Application Rejected" && "Your application was rejected by HR."}
                    {step === "Interview Scheduled" && "Interview has been scheduled. Prepare well!"}
                    {step === "Offered" && "Congratulations! You've been offered the job."}
                    {step === "Rejected" && "Sorry, your interview did not proceed successfully."}
                  </p>
                </StepperPanel>
              ))}
            </Stepper>
          </Card>
        )
      })}
    </div>
  );
}

export default MyApplications;
