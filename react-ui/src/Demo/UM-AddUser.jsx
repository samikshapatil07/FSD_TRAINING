import axios from "axios";
import { useEffect, useState } from "react";

function AddUser() {

    let[fname, setFname] = useState("");
    let[lname, setLname] = useState("");
    let[age, setAge] = useState("");
    let[msg, setMsg] = useState("");
    let[postArray ,setPostArray] = useState([]);

    useEffect(()=>{
        const getPosts = async ()=>{
            try {
                const response = await axios.get('https://dummyjson.com/c/4e67-0b66-4c8f-833a');
                //console.log(response)  <-- i get response structure 
                setPostArray(response.data)
            } catch (error) {
                console.log(error)
            }
        }
        getPosts();  //<-- calling function
    },[]);

    const addUser = async()=>{
        try {
            await axios.post('https://dummyjson.com/c/490a-b0c3-484d-ba0d',
                {
                    'fname': fname,
                    'lname': lname,
                    'age': age,
                }
            )
            setMsg("Post created successfully!!!!")
        }
        catch (err) {
            setMsg("Operation Failed, Try again")
        }
    }

    return(
        <div className="container-fluid">
            <h2 className="text-center mt-4">Example 2</h2>
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
                    <div  className="card p-3 shadow-sm">
                        <label>First Name:</label>
                        <input type="text" onChange={$e => setFname($e.target.value)} className="form-control mb-2" ></input>
                    </div>
                    <div>
                        <label>Last Name:</label>
                        <input type="text" onChange={$e => setLname($e.target.value)} className="form-control mb-2" ></input>
                    </div>
                    <div className="mb-4">
                        <label>Age:</label>
                        <input type="text" onChange={$e => setAge($e.target.value)} className="form-control mb-2" ></input>
                    </div>
                    <div >
                        <button className="btn btn-primary" onClick={() => addUser()}>Add User</button>
                    </div>
                </div>


                <div className="col-md-6">
                    <table className="table">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">First Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Age</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                postArray.map((post) => (
                                    <tr key={post.id}>
                                        <td>{post.id}</td>
                                        <td>{post.fname}</td>
                                        <td>{post.lname}</td>
                                        <td>{post.age}</td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    )
}

export default AddUser;