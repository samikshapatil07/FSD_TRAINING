import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";


function AddUser() {

    let [name, setName] = useState("");
    let [email, setEmail] = useState("");
    let [gender, setGender] = useState("");
    let [status, setStatus] = useState("");
    let [msg, setMsg] = useState("");

    const navigate = useNavigate();

//api for add user
    const addUser = async () => {
        try {
            await axios.post(
                'https://gorest.co.in/public/v2/users',{
                    'name': name,
                    'email': email,
                    'gender': gender,
                    'status': status
                },{
                    headers: {
                        Authorization: `Bearer a7e8a77501b2dfd9deced04fd01adac1de039af7918884ec035d783ce2758051`,
                    }
                }
            )
            setMsg("Post created successfully!!!!")
                        
                // navigate('/');
        

        }
        catch (err) 
        {
            console.log(err)
        }
    }

    return (
        <div className="container-fluid">
            <div className="row">

                <div className="col-md-6 mb-4">
                    <div>
                        <h3>Enter the Deatils</h3>
                        {
                            msg != "" ? <div className="mb-4">
                                <div className="alert alert-primary">
                                    {msg}
                                </div>
                            </div> : ""
                        }
                    </div>

                    <div>
                        <label>Name:</label>
                        <input type="text" required onChange={e => setName(e.target.value)} className="form-control mb-2" ></input>
                    </div>

                    <div>
                        <label>Email:</label>
                        <input type="text" required onChange={e => setEmail(e.target.value)} className="form-control mb-2" ></input>
                    </div>

                    <div className="mb-4">
                        <label>Gender:</label>
<select className="form-control mb-2" onChange={e => setGender(e.target.value)} required>
    <option value="">Select Gender</option>
    <option value="male">Male</option>
    <option value="female">Female</option>
  </select>                
      </div>

                    <div className="mb-4">
                        <label>Status:</label>
                        <input type="text" required onChange={e => setStatus(e.target.value)} className="form-control mb-2" ></input>
                    </div>

                    <div >
                        <button className="btn btn-primary" onClick={() => addUser()} >Add User</button>
                    </div>
                </div>

            </div>
        </div>
    )
}

export default AddUser;