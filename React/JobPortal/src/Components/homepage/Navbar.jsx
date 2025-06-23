import React from "react";
import "../../css/login.css";

function Navbar({ onLoginClick, onSignupClick }) {
  return (
    <div className="d-flex justify-content-between align-items-center px-4 py-3 shadow bg-white">
      <h3 className="fw-bold text-primary">CareerCrafter</h3>

      <div>
        <div className="d-flex align-items-center gap-2 mt-2">
          <button className="btn btn-dark px-4 rounded-pill" onClick={onLoginClick}>Login </button>
          <button className="btn btn-dark px-4 rounded-pill" onClick={onSignupClick}>Sign Up</button> 
        </div>
      </div>
      
    </div>
  );
}

export default Navbar;
