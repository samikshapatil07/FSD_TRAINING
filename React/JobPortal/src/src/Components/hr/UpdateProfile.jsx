import { useState, useRef } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { Toast } from "primereact/toast";
import "../../css/login.css";
import { useEffect} from "react";



function UpdateProfile() {
  const [name, setName] = useState("");
  const [companyName, setCompanyName] = useState("");
  const navigate = useNavigate();
  const toast = useRef(null); // Toast reference

// fetch the  detailes first
 useEffect(() => {
    const fetchProfile = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/hr/me`, {
          headers: { Authorization: "Bearer " + localStorage.getItem("token") },
        });
        const hr = response.data;
        setName(hr.name);
        setCompanyName(hr.companyName);
        
      } 
      catch (err)
       {
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
      await axios.post(
        "http://localhost:8080/api/hr/update/me",
        {
          name,
          companyName,
        },
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );

      toast.current.show({
        severity: "success",
        detail: "Profile updated successfully!",
        life: 2000,
      });

      setTimeout(() => {
        navigate("/hr", { state: { showToast: true } });
      }, 1500)

    }
     catch (error)
      {
      console.error("Update Profile Error");
      toast.current.show({
        severity: "error",
        detail: "Failed to update profile",
        life: 3000,
      });
    }
  }

    return (
       <div>
        <Toast ref={toast} />
        <div className="modal-overlay">
            <div className="modal-card">
                <button className="close-btn" onClick={() => navigate("/hr")}>×</button>
                <h3>Update Profile</h3>
                <p className="text-muted mb-3">Fill below to update your profile</p>

                <form onSubmit={updateProfile}>
                    <div className="mb-3 text-start">
                        <label>Name</label>
                        <input type="text" className="form-control" value={name}
                             onChange={(e) => setName(e.target.value)}  required/>  
                    </div>
                    <div className="mb-3 text-start">
                        <label>Company Name</label>
                        <input
                            type="text" className="form-control" value={companyName}
                            onChange={(e) => setCompanyName(e.target.value)} required />
                    </div>
                    <button type="submit" className="btn btn-dark w-100" >Update Profile</button>
                </form>
            </div>
        </div>
    </div>  
    );
}
export default UpdateProfile;
