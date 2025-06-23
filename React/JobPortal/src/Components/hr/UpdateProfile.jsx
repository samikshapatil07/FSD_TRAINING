import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import PopUp from "./PopUp";
import "../../css/login.css";


function UpdateProfile(){

  const [msg, setMsg] = useState("");
  const [showToast, setShowToast] = useState(false);
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [companyName, setCompanyName] = useState("");


    //update profile api
  const updateProfile = async (e) => {
    e.preventDefault();
    try {
           await axios.post('http://localhost:8080/api/hr/update/me', {
          'name': name,
          'companyName': companyName,
        },
        {
         headers: { "Authorization": "Bearer " + localStorage.getItem('token') }
            })
                  
      setMsg("Profile Updated successfully!");
      setShowToast(true);

      setTimeout(() => {
        navigate("/hr", { state: { showToast: true } });
      }, 1500);
    } 
    catch (error) 
    {
      console.error("Post Job Error:", error.response?.data || error.message);
      setMsg("Failed to update profile");
      setShowToast(true);
    }
  };

    return (
       <div>
        {showToast && <PopUp message={msg} />}
        <div className="modal-overlay">
            <div className="modal-card">
                <button className="close-btn" onClick={() => navigate("/hr")}>×</button>
                <h3>Update Profile</h3>
                <p className="text-muted mb-3">Fill below to update your profile</p>

                <form onSubmit={updateProfile}>
                    {/* name */}
                    <div className="mb-3 text-start">
                        <label>Name</label>
                        <input type="text" className="form-control" value={name}
                             onChange={(e) => setName(e.target.value)}  required/>  
                    </div>
                    {/* company name */}
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
