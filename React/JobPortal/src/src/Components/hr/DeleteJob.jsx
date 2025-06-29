import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { useState, useRef } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { Toast } from "primereact/toast";
import "primereact/resources/themes/lara-light-blue/theme.css";
import "primereact/resources/primereact.min.css";

function DeleteJob() {
  const [confirmId, setConfirmId] = useState("");
  const [showModal, setShowModal] = useState(true);
  const toast = useRef(null);
  const navigate = useNavigate();
  const { jobId } = useParams();

  // Cancel button click
  const cancelbutton = () => {
    setShowModal(false);
    navigate("/hr/jobs");
  };

  // Delete Job API call
  const deleteJob = async () => {
    if (confirmId !== jobId) {
      toast.current.show({
        severity: "warn",
        detail: "Entered Job ID does not match.",
        life: 3000,
      });
      return;
    }

    try {
      await axios.delete(`http://localhost:8080/api/jobs/delete/${jobId}`, {
        headers: { Authorization: "Bearer " + localStorage.getItem("token") },
      });

      toast.current.show({
        severity: "success",
        detail: `Job deleted successfully!`,
        life: 2000,
      });

      setTimeout(() => {
        navigate("/hr/jobs", { state: { showToast: true } });
      }, 1500)

    } 
    catch (err) {
      console.log(err)

      toast.current.show({
        severity: "error",
        summary: "Deletion Failed",
        detail:"Cannot delete this job. Applications exist for this job",
        life: 4000,
      });
    }
  };


  return (
<div>
    <Toast ref={toast} />
    <Modal show={showModal} onHide={cancelbutton} centered>

      <Modal.Header closeButton>
        <Modal.Title>Confirm Job Deletion</Modal.Title>
      </Modal.Header>

      <Modal.Body>
        <p>Are you sure you want to delete job with ID: <strong>{jobId}</strong>?</p>
        <p>Please enter the Job ID below to confirm delete:</p>
        <Form.Control type="text" placeholder="Enter Job ID" value={confirmId} onChange={(e) => setConfirmId(e.target.value)} />
      </Modal.Body>
      
      <Modal.Footer>
        <Button variant="secondary" onClick={cancelbutton}>Cancel</Button>
        <Button variant="danger" onClick={deleteJob}>Delete</Button>
      </Modal.Footer>

    </Modal>
    </div>
  )
}

export default DeleteJob;
