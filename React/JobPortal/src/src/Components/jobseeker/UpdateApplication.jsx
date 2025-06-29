import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { FileUpload } from "primereact/fileupload";
import { Toast } from "primereact/toast";
import axios from "axios";
import { useRef } from "react";
import "../../css/postjob.css"; 

 function UpdateApplication() {
  const { appId } = useParams();
  const navigate = useNavigate();
  const toast = useRef(null);
  const [msg, setMsg] = useState("");

  const onUpload = async ({ files }) => {
    const resume = files[0];
    const formData = new FormData();
    formData.append("resume", resume);

    try {
      await axios.put(
        `http://localhost:8080/api/applications/update/${appId}`,
        formData,
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );

      setMsg("Application submitted successfully!");
      toast.current.show({
        severity: "success",
        detail: "Resume uploaded & applied!",
        life: 3000,
      });

      setTimeout(() => navigate("/jobseeker/myapplications"), 1500);
    } 
    catch (err) 
    {
      console.error("Upload failed", err);
      toast.current.show({
        severity: "error",
        detail: "Upload failed",
        life: 3000,
      });
    }
  };

  return (
    <div>
      <Toast ref={toast} />
      <div className="modal-overlay">
        <div className="modal-card modal-large">
          <button className="close-btn" onClick={() => navigate("/jobseeker/myapplications")}>×</button>
          <h4 className="fw-bold">Update Your Application</h4>
          <p className="text-muted">Upload your resume (.pdf only) to update</p>

          <div className="p-fluid">
            <FileUpload
              name="resume"
              customUpload
              uploadHandler={onUpload}
              accept=".pdf"
              maxFileSize={3 * 1024 * 1024} // 3 MB
              chooseLabel="Choose Resume"
              uploadLabel="Update"
              cancelLabel="Cancel"
              emptyTemplate={
                <p className="m-0 text-center">Drag & drop your PDF resume here</p>
              }
              className="w-100"
            />
          </div>
          
        </div>
      </div>
    </div>
  );
}
export default  UpdateApplication
