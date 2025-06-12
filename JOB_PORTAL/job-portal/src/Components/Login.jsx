import axios from "axios";
import { useState } from "react";

function Login() {
    let [username, setUsername] = useState("");
    let [password, setPassword] = useState("");
    let [msg, setMsg] = useState("");

    const processLogin = async () => {
        // Encode username and password using btoa 
        let encodedString = window.btoa(username + ':' + password);
        //console.log(encodedString)
        //console.log(window.atob(encodedString))
        try {
            const response = await axios.get('http://localhost:8080/api/user/token', {
                headers: { "Authorization": "Basic " + encodedString }
            })
            //console.log(response.data.token)
            let token = response.data; //<-- this is our access token, save it for later usage. (redux,localstorage)
            localStorage.setItem('token', token); //<-- saving token for future use in browsers local storage mem
            // Step 2: Get User Details 
            let details = await axios.get('http://localhost:8080/api/user/details', {
                headers: { "Authorization": "Bearer " + token }
            }
            )
            //console.log(details)

            let role = details.data.user.role;
            switch (role) {
                case "HR":
                    console.log("go to hr dashboard... ");
                    break;
                case "JOB_SEEKER":
                    console.log("go to job seeker dashboard... ");
                    break;
                case "EXECUTIVE":
                    console.log("go to executive dashboard.. ");
                    break;
                default:
                    setMsg("Login Disabled, contact Admin at admin@example.com")
            }
            setMsg("Login Success!!!")
        }
        catch (err) {
            setMsg('Invalid Credentials')
        }


    }
    return (
        <div className="container">
            <div className="row">
                <div className="col-lg-12">
                    <br /><br /><br /><br />
                </div>
            </div>
            <div className="row">
                <div className="col-md-3"> </div>
                <div className="col-md-5">
                    <div className="card">
                        <div className="card-header"> Login</div>
                        <div className="card-body">
                            {msg !== "" ? <div>
                                <div className="alert alert-info" >
                                    {msg}
                                </div>
                            </div> : ""}

                            <div className="mb-2">
                                <label>Enter username:</label>
                                <input type="text" className="form-control"
                                    onChange={($e) => setUsername($e.target.value)} />
                            </div>
                            <div className="mb-2">
                                <label>Enter password:</label>
                                <input type="text" className="form-control"
                                    onChange={($e) => setPassword($e.target.value)} />
                            </div>
                            <div className="mb-2">
                                <button className="btn btn-primary" onClick={() => processLogin()}>Login</button>
                            </div>
                        </div>
                        <div className="card-footer">
                            Don't have an Account? Sign Up here.
                        </div>
                    </div>
                </div>
                <div className="col-md-3"> </div>
            </div>
        </div>
    )
}

export default Login; 
