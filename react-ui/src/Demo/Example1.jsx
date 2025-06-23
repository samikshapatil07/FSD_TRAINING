import { useState } from "react";
import axios from "axios";

function Example1() {
    let [userId, setUserId] = useState("");
    let [postArry, setPostArry] = useState([]);

    const getPosts = async () => {
        try {
            const response = await axios.get(`https://jsonplaceholder.typicode.com/users/${userId}/posts`);
            setPostArry(response.data);
        } catch (err) {
            console.log(err);
        }
    }


    return(
        <div className="container">
            <h2 className="text-center mt-4">Example 1</h2>
            <div className="row">
                <div className="col-lg mb-4 mt-4">
                    <div>
                        <label>Enter User ID:</label>
                        <input type="text" onChange={($e) => setUserId($e.target.value)} className="form-control"/>
                        <br/>
                         <button className="btn btn-primary" onClick={getPosts}>Show Posts</button>
                    </div>
                </div>
            </div>

            <div className="row">
                {postArry.map((post) => (
                    <div  className="card mb-3 shadow-sm" key={post.id}>
                        <div className="card-header">Post ID: {post.id}</div>
                        <div className="card-body">
                            <h5 className="card-title">{post.title}</h5>
                            <p className="card-text">{post.body}</p>
                        </div>
                    </div>
                ))}
            </div>

        </div>
    )
}

export default Example1;
