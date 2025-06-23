import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { useState } from "react";

import Companies from "./Companies";
import JobCategories from "./JobCategories";
import Login from "../Login";
import "../../css/login.css";
import Signup from "../Signup";
import Navbar from "./Navbar";

function HomePage() {

    const [login, setLogin] = useState(false);
    const [signup, setSignup] = useState(false);

    return (
        <div style={{ backgroundColor: "#F9FAFB", minHeight: "100vh" }}>
            
            {(login || signup) && <div className="blurred-content"></div>}

            {/* Navbar */}
            <Navbar onLoginClick={() => setLogin(true)} onSignupClick={() => setSignup(true)} />

            <div className="container mt-4">
                <div className="row align-items-center">

                    <div className="col-md-6 text-center text-md-start">
                        <h1 className="fw-bold">
                            <span className="text-primary">Unlock</span> Ambition
                        </h1>
                        <p className="text-muted fs-5">
                            Apply to a plethora of hiring opportunities & work with your dream companies!
                        </p>
                        <div className="container mt-3">
                            <div className="row justify-content-start">
                                <div className="col-auto mb-2">
                                    <button className="btn btn-primary w-100" onClick={() => setLogin(true)}>
                                        Find Job
                                    </button>
                                </div>
                                <div className="col-auto mb-2">
                                    <button className="btn btn-primary w-100" onClick={() => setLogin(true)}>
                                        Post Job
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="col-md-6 text-center mt-4 mt-md-0">
                        <img src="images/homepage.jpg" className="img-fluid" style={{ maxHeight: "300px" }} />
                    </div>
                    
                </div>
            </div>

            <div className="container">
                <JobCategories />
                <Companies />
            </div>

            {/* for pop up form close */}
            {login && (
                <div className="login-popup-container">
                    <button className="close-login-btn" onClick={() => setLogin(false)}>&times;</button>
                    <Login
                        onClose={() => setLogin(false)}
                        switchToSignup={() => {
                            setLogin(false);
                            setSignup(true);
                        }}
                    />
                </div>
            )}
            {signup && (
                <div className="sign-popup-container">
                    <button className="close-login-btn" onClick={() => setSignup(false)}>
                        &times;
                    </button>
                    <Signup onClose={() => setSignup(false)} />
                </div>
            )}
        </div>

    );

}

export default HomePage;
