// src/components/Home.jsx
import React from "react";
import { useNavigate } from "react-router-dom";


function Home() {
    const navigate = useNavigate();

    return (
        <div className="container">
            <div className="col-lg-6"></div>
            <h4 className="mb-4">User Management Home</h4>
            <button className="btn btn-primary mx-2 btn-lg" onClick={() => navigate("/userlist")}>UserList  </button>

        </div>
    );
}

export default Home

