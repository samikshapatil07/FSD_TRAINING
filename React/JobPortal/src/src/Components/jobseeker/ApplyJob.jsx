import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { FileUpload } from "primereact/fileupload";
import { Toast } from "primereact/toast";
import axios from "axios";
import { useRef } from "react"; // To create a reference to Toast popup
import "../../css/postjob.css"; 

export default function ApplyJob() {
  const { jobId } = useParams();
  const navigate = useNavigate();
  const toast = useRef(null); //to show toast msg

  const onUpload = async ({ files }) => {
    const resume = files[0];
    const formData = new FormData();
    formData.append("resume", resume);

    try {
      await axios.post(
        `http://localhost:8080/api/applications/apply/upload/resume/${jobId}`,
        formData,{
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          }
        }
      )
      toast.current.show({
        severity: "success",
        detail: "Resume uploaded & applied!",
        life: 3000,
      })
      setTimeout(() => navigate("/jobseeker/jobslist"), 1500);

    } 
    catch (err)
     {
      console.log(err)
      toast.current.show(
        {
        severity: "error",
        detail: "Upload failed",
        life: 3000,
      })
    }
  }

  return (
    <div>
      <Toast ref={toast} />
      <div className="modal-overlay">
        <div className="modal-card modal-large">
          <button className="close-btn" onClick={() => navigate("/jobseeker/jobslist")}>×</button>
          <h4 className="fw-bold">Apply for Job</h4>
          <p className="text-muted">Upload your resume (.pdf only) to apply</p>

          <div className="p-fluid">
            <FileUpload
              name="resume" //name of the imnput
              customUpload uploadHandler={onUpload} accept=".pdf"
              maxFileSize={3 * 1024 * 1024} // 3 MB
              chooseLabel="Choose Resume" uploadLabel="Apply" cancelLabel="Cancel"
              emptyTemplate={
                <p className="m-0 text-center">Drag & drop your PDF resume here</p>
              }
              className="w-100" />
          </div>
        </div>
      </div>
    </div>
  )
}
