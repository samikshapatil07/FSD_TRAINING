import React, { useEffect, useState } from "react";
import axios from "axios";
import { Stepper } from "primereact/stepper";
import { StepperPanel } from "primereact/stepperpanel";
import { Card } from "primereact/card";

function MyApplications() {
  const [applications, setApplications] = useState([]);

  useEffect(() => {
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

    fetchApplications();
  }, []);

  const getStepFlow = (status) => {
    // Return step list and active index based on status
    switch (status) {
      case "APPLIED":
        return {
          steps: ["Applied"],
          index: 0
        };
      case "APPLICATION_REJECTED":
        return {
          steps: ["Applied", "Application Rejected"],
          index: 1
        };
      case "INTERVIEW_SCHEDULED":
        return {
          steps: ["Applied", "Interview Scheduled"],
          index: 1
        };
      case "OFFERED":
        return {
          steps: ["Applied", "Interview Scheduled", "Offered"],
          index: 2
        };
      case "REJECTED":
        return {
          steps: ["Applied", "Interview Scheduled", "Rejected"],
          index: 2
        };
      default:
        return {
          steps: ["Applied"],
          index: 0
        };
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="fw-bold mb-2 text-center">My Applications</h2>

      {applications.map((app) => {
        const flow = getStepFlow(app.status);
        return (
          <Card title={app.jobPosting.jobTitle} className="mb-5 shadow" key={app.applicationId}>
            <p><strong>Application ID:</strong> {app.applicationId}</p>
            <p><strong>Job ID:</strong> {app.jobPosting.job_id}</p>
            <p><strong>Company:</strong> {app.jobPosting.company}</p>
            <p><strong>Applied On:</strong> {new Date(app.appliedOn).toLocaleDateString()}</p>
            <p><strong>Status:</strong> {app.status}</p>

            <Stepper activeStep={flow.index} readOnly className="mt-4" orientation="horizontal">
              {flow.steps.map((step, idx) => (
                <StepperPanel key={idx} header={step}>
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
        );
      })}
    </div>
  );
}

export default MyApplications;
