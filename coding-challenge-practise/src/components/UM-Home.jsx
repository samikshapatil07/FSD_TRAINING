// src/components/Home.jsx
import React from "react";
import { useNavigate } from "react-router-dom";


function Home() {
    const navigate = useNavigate();

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <h1 className="mb-4">User Management Home</h1>
                    <button className="btn btn-primary mx-2 btn-lg" onClick={() => navigate("/addalbum")}>Post  </button>
                    <button className="btn btn-primary mx-2 btn-lg" onClick={() => navigate("/getintable")}>Get in table</button>
                    <button className="btn btn-primary mx-2 btn-lg" onClick={() => navigate("/getincard")}>Get in Card</button>
                    <button className="btn btn-primary mx-2 btn-lg" onClick={() => navigate("/getbyid")}>Get by ID</button>


                </div>
            </div>
        </div>
    );
}

export default Home

