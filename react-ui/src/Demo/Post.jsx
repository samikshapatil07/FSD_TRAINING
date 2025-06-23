import axios from "axios";
import { useEffect, useState } from "react";

function Post() {
    let[postArry, setPostArry] = useState([]);
    let[msg,setMsg] = useState("");

    useEffect(()=>{
        // usage of axios (use instead of fetch)
    
         //------------- post api---------------------------------------------
        const getPosts = async () =>{   //give async before await
            try{
             const response = await axios.get ('https://jsonplaceholder.typicode.com/posts')
             //console.log(response) //i get respone strucutr from here
             setPostArry(response.data)
            } 
            catch(err)
            {
            console.log(err) //   
            }
        } //axios automatically converts to json

       getPosts(); //<--- calling function to ensure API is called

        //call the api using fetch : example 1
        // fetch('https://jsonplaceholder.typicode.com/posts')
        //   .then( reps => reps.json())
        //   .then(data => setPostArry(data))

    },[]); // give black array compulsory to call api only once

    
    //------------- delete api---------------------------------------
    const onDelete = async (postId)=> {
      try{
        //Step 1: call the api
        //await axios.delete('https://jsonplaceholder.typicode.com/posts/'+ postId);
        await axios.delete('https://jsonplaceholder.typicode.com/posts/${postId}');
        //Step 2 : remove element from array
        let temp = [...postArry];  //create an temprory arry
        temp = temp.filter(p => p.id !== postId);
        //if the condition is true, the element stays else it is deleted
        setPostArry(temp)
        setMsg("Post with id " + postId +" deleted..")
      }
       catch(err){
        setMsg("Could not delete..")
       }
    }


    //-------------------------nav bar--------------------------------
    return (
        <div className="container-fluid">
            <nav className="navbar navbar-expand-lg navbar-light bg-light mb-4">
                <div className="container-fluid">
                    <a className="navbar-brand" href="#">Navbar</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <a className="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Features</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Pricing</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link disabled" href="#" >Disabled</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div><h3 className="display-5">All Posts</h3></div>
            
            {
                msg !== "" ? <div className="row">
                    <div className="alert alert-primary">
                        {msg}
                    </div>
                </div> : ""
            }

            <div className="row">

                {
                    postArry.map((post) => (
                        <div className="col-md-12 mb-2" key={post.id}>
                            <div className="card" >
                                <div className="card-header">
                                    User ID: {post.userId}
                                </div>
                                <div className="card-body">
                                    <h5 className="card-title"> {post.title}</h5>
                                    <p className="card-text">{post.body}</p>
                                    <a href="#" className="btn btn-primary">View Comments</a> &nbsp;&nbsp;
                                    <button className="btn btn-danger" onClick={() =>{onDelete(post.id)}}>Delete</button>
                                </div>
                            </div>
                        </div>
                    ))
                }


            </div>
        </div>
    )
}

export default Post; 