import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";

function DeleteJob() {
// for pop up ..,mmsg
    const [msg, setMsg] = useState("");
  const [showToast, setShowToast] = useState(false);

  const navigate = useNavigate();
  const { jobId } = useParams();
  const [confirmId, setConfirmId] = useState("");
  const [showModal, setShowModal] = useState(true);

  //cancel
    const cancelbutton = () => {
    setShowModal(false);
    navigate("/hr/jobs");
  };

  // delte job api
  const deleteJob = async () => {
    if (confirmId !== jobId) {
      alert("Entered Job ID does not match.");
      return;
    }
    try {
      await axios.delete(`http://localhost:8080/api/jobs/delete/${jobId}`, {
        headers: { Authorization: "Bearer " + localStorage.getItem("token") },
      });
      setShowModal(false);

      setMsg("Job deleted successfully!");
      setShowToast(true);

      setTimeout(() => {
        navigate("/hr/jobs", { state: { showToast: true } });
      }, 1500);
    } 
    catch (err)
    {
      console.error("Error deleting job:", err);
      setMsg("Failed to post job");
      setShowToast(true);    }
  };


  return (
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
  );
}

export default DeleteJob;
