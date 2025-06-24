import { useState, useEffect } from "react"


function EditUser() {

    let [name, setName] = useState("");
    let [email, setEmail] = useState("");
    let [gender, setGender] = useState("");
    let [status, setStatus] = useState("");
    let [msg, setMsg] = useState("");

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
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>

                    <div className="mb-4">
                        <label>Staus:</label>
                        <input type="text" required onChange={$e => setStatus(e.target.value)} className="form-control mb-2" ></input>
                    </div>

                    <div >
                        <button className="btn btn-primary" onClick={() => addUser()} >Edit</button>
                    </div>
                </div>

            </div>
        </div>
    )
}
export default EditUser