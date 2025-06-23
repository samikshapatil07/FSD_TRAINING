import axios from "axios";
import { useState } from "react";

function UploadProfilePic() {
    let [profileImage, setProfleImage] = useState("");
    let [msg, setMsg] = useState("")
    
    const upload = async () => {
        const formData = new FormData();
        formData.append("file", profileImage);

        try {
            await axios.post('http://localhost:8080/api/author/upload/profile-pic', formData,
                {
                    headers:
                        { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
                }
            )
            setMsg("Profile image uploaded...")
        }
        catch (err) {
            setMsg(err)
        }
    }
    return (
        <div>
            {msg}
            <label>Select your Profile Pic: </label>
            <input type="file" onChange={($e) => { setProfleImage($e.target.files[0]) }} />
            <br />
            <button onClick={() => upload()}>Upload Image</button>
        </div>
    )
}

export default UploadProfilePic; 