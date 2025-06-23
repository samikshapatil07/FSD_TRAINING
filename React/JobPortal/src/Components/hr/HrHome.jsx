import "../../css/hr.css";

import { useNavigate } from "react-router-dom";

function HrHome() {

      const navigate = useNavigate();


  return (

      <div className="hero-content">
        <h1 className="fw-bold display-4">Empower Your Hiring</h1>
        <p className="fs-5 text-muted mb-4">Recrute best talent for your job in seconds.</p>
        <button className="btn btn-primary btn-lg" onClick={() => navigate("/hr/postjob")}>Post Job</button>
      </div>
    )
  
      }

export default HrHome;
