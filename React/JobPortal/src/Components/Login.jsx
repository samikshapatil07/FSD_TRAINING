import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import axios from "axios";
import "../css/login.css";
import { setUserDetails } from "../store/actions/UserAction";
import { useDispatch } from "react-redux";

function Login({ onClose }) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [msg, setMsg] = useState("");
    const navigate = useNavigate();
    const dispatch = useDispatch();

    //login api
    const processLogin = async (e) => {
        e.preventDefault();
        let encodedString = window.btoa(username + ':' + password);
        try {
            const response = await axios.get('http://localhost:8080/api/user/token', {
                headers: { "Authorization": "Basic " + encodedString }
            })
            let token = response.data.token;
            localStorage.setItem('token', token);

            let details = await axios.get('http://localhost:8080/api/user/details', {
                headers: { "Authorization": "Bearer " + token }
            })

            let user = {
                'username': username,
                'role': details.data.user.role
            }
            setUserDetails(dispatch)(user); //<-- this is where i save this user details in store

            //console.log(details)
            let name = details.data.name;
            localStorage.setItem('name', name);
            let role = details.data.user.role;

            switch (role) {
                case "HR":
                    navigate("/hr");
                    break;
                case "JOB_SEEKER":
                    navigate("/jobseeker");
                    break;
                case "EXECUTIVE":
                    navigate("/executive");
                    break;
                default:
                    setMsg("Login Disabled, Contact Admin at carres@example.com");
            }
            setMsg("Login Success!!!");
        }
        catch (err) {
            setMsg('Invalid Credentials');
        }
    }
    return (
        <div className="modal-overlay">
            <div className="modal-card">
                <button className="close-btn" onClick={onClose}>×</button>
                <h3>Job Portal</h3>
                <p className="text-muted mb-3">Login to access the job portal</p>

                <form onSubmit={processLogin}>
                    {msg && <div className="alert alert-info">{msg}</div>}
                    {/* username */}
                    <div className="mb-3 text-start">
                        <label>Username</label>
                        <input type="text" className="form-control" value={username}
                             onChange={(e) => setUsername(e.target.value)}  required/>  
                    </div>
                    {/* password */}
                    <div className="mb-3 text-start">
                        <label>Password</label>
                        <input
                            type="password" className="form-control" value={password}
                            onChange={(e) => setPassword(e.target.value)} required />
                    </div>
                    <button type="submit" className="btn btn-dark w-100">Login</button>
                </form>

                <div className="mt-3">
                    <p>   
                        Don't have an account?
                        <Link to="/signup">Sign Up here.</Link>
                    </p>

                </div>
            </div>
        </div>
    );
}

export default Login;
