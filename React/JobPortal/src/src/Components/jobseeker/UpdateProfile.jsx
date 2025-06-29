import { useState, useRef } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { Toast } from "primereact/toast";
import { useEffect} from "react";


import "../../css/login.css";

function UpdateProfileJS({ onClose }) {

  const [name, setName] = useState("");
  const [education, setEducation] = useState("");
  const [skills, setSkills] = useState("");
  const [experience, setExperience] = useState("");
  const toast = useRef(null);
  const navigate = useNavigate();


  
  // fetch the  detailes first
 useEffect(() => {
    const fetchProfile = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/jobseeker/me`, {
          headers: { Authorization: "Bearer " + localStorage.getItem("token") },
        });
        const js = response.data;
        setName(js.name);
        setEducation(js.education);
        setSkills(js.skills);
        setExperience(js.experience);
      } catch (err) {
        console.error("Error fetching profile", err);
        toast.current.show({
          severity: "error",
          detail: "Failed to load profile details.",
          life: 3000,
        });
      }
    };
    fetchProfile();
  }, []);
    

  const updateProfile = async () => {

    try {
      await axios.put(
        "http://localhost:8080/api/jobseeker/update/me",
        { name, education, skills, experience },
        { headers: { Authorization: "Bearer " + localStorage.getItem("token") } }
      );

      toast.current.show({
        severity: "success",
        detail: "Profile updated successfully!",
        life: 2000,
      });

      setTimeout(() => {
        if (onClose) onClose();
        navigate("/jobseeker");
      }, 1500);
    } 
    catch (error)
     {
      console.error("Profile Update Error:");
      toast.current.show({
        severity: "error",
        detail: "Failed to update profile",
        life: 3000,
      });
    }
  };


  return (
    <div>
      <Toast ref={toast} />

      <div className="modal-overlay">
        <div className="modal-card">
          <button className="close-btn" onClick={() => {
            if (onClose) onClose()
            navigate("/jobseeker") }}> × </button>

          <h3>Update Profile</h3>
          <p className="text-muted mb-3">Fill below to update your profile</p>

          <form onSubmit={updateProfile}>
            <div className="mb-3 text-start">
              <label>Name</label>
              <input  type="text" className="form-control" value={name}
                onChange={(e) => setName(e.target.value)} required />
            </div>

            <div className="mb-3 text-start">
              <label>Education</label>
               <input type="text" className="form-control" value={education}
                onChange={(e) => setEducation(e.target.value)} required/>
            </div>

            <div className="mb-3 text-start">
              <label>Skills</label>
              <input  type="text" className="form-control" value={skills}                
              onChange={(e) => setSkills(e.target.value)}  required /> 
            </div>

            <div className="mb-3 text-start">
              <label>Experience</label>
               <input type="text" className="form-control" value={experience}
                onChange={(e) => setExperience(e.target.value)} required />
            </div>

            <button type="submit" className="btn btn-dark w-100"> Update Profile </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default UpdateProfileJS;
