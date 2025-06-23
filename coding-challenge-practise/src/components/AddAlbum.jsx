import axios from "axios"
import { useEffect, useState } from "react"
import { Navigate } from "react-router-dom"

function Add(){
const[albumId, setAlbumId] = useState("")
const[title, setTitle] = useState("")
const[url,setUrl]  = useState("")
const[msg, setMsg] = useState("")

//api to add photo
const add = async() => {
    try{
        await axios.post('https://jsonplaceholder.typicode.com/posts',{
            'albumId': albumId,
            'title' : title,
            'url' :url
        })
        setMsg("added successfully")
    }
    catch(err)
    {
        console.log(err)
    }
}


    return(
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col md-6">
                    
                    <div>
                        <h3>Enter Detailes:</h3>
                        {
                            msg!== ""?
                            <div className="row">
                                <div className="alert alert-primary">
                                    {msg}
                                </div>

                            </div> :""
                        }
                    </div>

                    <div className="row">
                        <label>Enter Album ID:</label>
                        <input className="form-control mb-2" type="number" onChange={$e => setAlbumId($e.target.value)}></input>
                    </div>
                    <div className="row">
                        <label>Enter Title:</label>
                        <input className="form-control mb-2" type="text" onChange={$e => setTitle($e.target.value)}></input>
                    </div>
                    <div className="row">
                        <label>Enter url:</label>
                        <input className="form-control mb-2" type="text" onChange={$e => setUrl($e.target.value)}></input>
                    </div>

                                     <div className="row">
                        <button className="btn btn-primary" onClick={()=>add()} >Add</button>
                    </div>

                </div>
            </div>
        </div>
    )

}
export default Add