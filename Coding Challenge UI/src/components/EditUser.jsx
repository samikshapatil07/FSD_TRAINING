import axios from "axios";
import { useState, useEffect } from "react"


function EditUser() {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [gender, setGender] = useState("");
    const [status, setStatus] = useState("");
    const [user, setUser] = useState("");

    const [msg, setMsg] = useState("");



    
        //to edit the data
        const editUser = async () => {
            try {
                await axios.put( `https://gorest.co.in/public/v2/users/${user.id}`,{
                        'name': name,
                        'email': email,
                        'gender': gender,
                        'status':status
                    },
                    {
                        headers: {
                            Authorization: `Bearer a7e8a77501b2dfd9deced04fd01adac1de039af7918884ec035d783ce2758051`,
                        }
                    }
                )
                setMsg("Post edited successfully!!!!")
            }
            catch (err)
             {
                console.log(err)
            }
        }
        editUser()

    return (
        <div className="container-fluid">
            <div className="row">

                <div className="col-md-6 mb-4">
                    <div>
                        <h3>Enter the Deatils to edit</h3>
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
                        <input type="text" required onChange={$e => setName(e.target.value)} className="form-control mb-2" ></input>
                    </div>

                    <div>
                        <label>Email:</label>
                        <input type="text" required onChange={$e => setEmail(e.target.value)} className="form-control mb-2" ></input>
                    </div>

                    <div className="mb-4">
                        <label>Gender:</label>
                        <select className="form-control mb-2" onChange={e => setGender(e.target.value)} required>
                            <option value="">Select Gender</option>
                            <option value="male">male</option>
                            <option value="female">female</option>
                        </select>
                    </div>

                    <div className="mb-4">
                        <label>Staus:</label>
                        <input type="text" required onChange={e => setStatus(e.target.value)} className="form-control mb-2" ></input>
                    </div>

                    <div >
                        <button className="btn btn-primary" onClick={() => editUser()} >Edit</button>
                    </div>
                </div>

            </div>
        </div>
    )
}
export default EditUser