import axios from "axios";
import { useState } from "react";

function AddPost() {
    let [title, setTitle] = useState("");
    let [postText, setPostText] = useState("");
    let [userId, setUserId] = useState("");
    let[msg, setMsg] = useState("");

    //------------------------------add post api ------------------------------------------------
    const addPost = async () => {
        try{
        await axios.post('https://jsonplaceholder.typicode.com/posts',
        {
            'title' : title,
            'body' : postText ,
            'userId' : userId ,
        }
        )
        setMsg("Post Created Successfully!!")
    }
    catch(err){
        setMsg("Operation Failed, Try again..")
    }
}

//--------------------- page styling -----------------------------------------
    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="col-md-6">
                <div className="card shadow">
                    <div className="card-header text-center fs-4">Enter Post Details</div>
                    <div className="card-body">

                        {
                                msg != "" ? <div className="mb-3">
                                    <div className="alert alert-primary">
                                        {msg}
                                    </div>
                                </div> : ""
                            }


                        <div className="mb-3">
                            <label className="form-label">Enter Post Title</label>
                            <input
                                type="text"
                                onChange={(e) => setTitle(e.target.value)}
                                className="form-control"
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Enter Post Text</label>
                            <textarea
                                rows="3"
                                onChange={(e) => setPostText(e.target.value)}
                                className="form-control"
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Enter Post UserId</label>
                            <input
                                type="number"
                                onChange={(e) => setUserId(e.target.value)}
                                className="form-control"
                            />
                        </div>

                        <div className="text-center">
                            <button className="btn btn-primary"onClick={() => addPost()}>Add Post</button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default AddPost;
