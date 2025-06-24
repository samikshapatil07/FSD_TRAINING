import React from "react";
import { useNavigate } from "react-router-dom";


function Home() {
    const navigate = useNavigate();

 return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <h1 className="mb-4">User Management Home</h1>
                          <button className="btn btn-primary  mx-2" onClick={() => navigate("/userlist")}>UserList  </button>
                    <button className="btn btn-primary mx-2" onClick={() => navigate("/adduser")}>Add User</button>
                    {/* <button className="btn btn-primary mx-2" onClick={() => navigate("/edituser")}>EditUser</button> */}


                </div>
            </div>
        </div>
    );
}


export default Home