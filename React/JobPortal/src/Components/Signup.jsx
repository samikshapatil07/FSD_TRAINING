import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "../css/signup.css";


function Signup({onClose}) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("");
    const [msg, setMsg] = useState("");

    const processSignup = async (e) => {
        e.preventDefault();

        //signup API to be called
        
        console.log({ email, password, role });
        setMsg("Signup successful!");
        
    };

    return (
        <div className="modal-overlay">
            <div className="modal-card">
                <button className="close-btn" onClick={onClose}>×</button>
                <h3>Job Portal</h3>
                <p className="text-muted mb-3">Sign up to create your account</p>

                <form onSubmit={processSignup} >
                    {msg && <div className="alert alert-success">{msg}</div>}
                    {/* email */}
                    <div className="mb-3 text-start">
                        <label>Email</label>
                        <input type="email" className="form-control"value={email}
                               onChange={(e) => setEmail(e.target.value)}required/>  
                    </div>
                    {/* Password */}
                     <div className="mb-3 text-start">
                        <label>Password</label>
                        <input type="password" className="form-control"value={email}
                               onChange={(e) => setPassword(e.target.value)}required/>  
                    </div>
                    {/* Role */}
                    <div className="mb-4 text-start">
                        <label>Role</label>
                        <select className="form-control" value={role}
                                 onChange={(e) => setRole(e.target.value)} required >
                            <option value="">Select your role</option>
                            <option value="jobseeker">Job Seeker</option>
                            <option value="hr">HR</option>
                        </select>
                    </div>  

                    <button type="submit" className="btn btn-dark w-100" >Sign Up</button>
                </form>

                <div className="mt-3">
                        <p>  Already have an account?
                          <Link to="/login">Login Up here.</Link>
                        </p>                
                </div>
            </div>
        </div>
    );
}

export default Signup;
