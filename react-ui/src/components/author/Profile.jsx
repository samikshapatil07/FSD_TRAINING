import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Profile() {
    const [author, setAuthor] = useState(undefined);
    useEffect(() => {
        const getAuthorInfo = async () => {
            try {
                let response = await axios.get('http://localhost:8080/api/author/get',
                    {
                        headers:
                            { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
                    }
                )
                setAuthor(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        getAuthorInfo();
    }, [])
    return (

        <div className="container">
            <div className="row">
                <div className="col-lg-12">
                    <h1>Profile</h1>
                </div>
                <div className="col-md-12">
                    <div className="card">
                        <div className="card-header">
                            Author Profile
                        </div>
                        <div className="card-body">
                            <div className="mt-4">
                                <label>Name: </label>
                                <input type="text" value={author?.name} className="form-control" />
                            </div>
                            <div className="mt-4">
                                <img src={`../images/${author?.profilePic}`} />
                                <br />
                                <Link to="/author/upload-profile-pic">Upload Pic</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>


    )
}

export default Profile;