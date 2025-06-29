import React from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { setUserDetails } from "../../store/actions/UserAction";

function JsSidebar() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  //logout
  const logout = () => {
    let u = {
      username: "",
      role: ""
    };
    setUserDetails(dispatch)(u);
    localStorage.clear();
    navigate("/");
  };

  return (
    <div className="offcanvas offcanvas-end" tabIndex="-1" id="profileSidebar" aria-labelledby="offcanvasLabel">
      <div className="offcanvas-header">
        <h5 className="offcanvas-title" id="offcanvasLabel">Profile</h5>
        <button type="button" className="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div className="offcanvas-body d-flex flex-column gap-3">
        <button className="btn btn-outline-primary" onClick={() => navigate("/jobseeker/updateprofile")}>Update Profile</button>
        <button className="btn btn-danger" onClick={logout}>Logout</button>
      </div>
    </div>
  );
}

export default JsSidebar;
