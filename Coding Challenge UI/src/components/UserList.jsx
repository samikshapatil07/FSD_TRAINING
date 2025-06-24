import { useState, useEffect } from "react"
import axios from "axios";
import { useNavigate } from "react-router-dom";


function UserList() {
    const [msg, setMsg] = useState("")
    const [user, setUser] = useState([])

    const navigate = useNavigate();

    //api call for
    useEffect(() => {
        const getAll = async () => {
            try {
                const response = await axios.get('https://gorest.co.in/public/v2/users')
                setUser(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        getAll()
    }, [])

    //delete api
    const OnDelete = async (id) => {
        try {
            await axios.delete(`https://gorest.co.in/public/v2/users/${id}`, {
                headers: {
                    Authorization: `Bearer a7e8a77501b2dfd9deced04fd01adac1de039af7918884ec035d783ce2758051`
                }
            })

            let temp = [...user]
            temp = temp.filter(u => u.id !== id)
            setUser(temp)
            setMsg("deleted")
        }
        catch (err) {
            console.log(err)
        }
    }


    return (
        <div className="container">

            <table className="table">
                <thead className="table">
                    <tr>
                        <th>Id</th>
                        <th>name</th>
                        <th>email</th>
                        <th>gender</th>
                        <th>status</th>
                        <th>delete</th>
                        <th>edit</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        user.map((user) => (
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.name}</td>
                                <td>{user.email}</td>
                                <td>{user.gender}</td>
                                <td>{user.status}</td>
                                <td>
                                    < button className="btn btn-danger" onClick={() => OnDelete(user.id)}> Delete</button>
                                </td>
                                <td>
                                    < button className="btn btn-primary" onClick={() => navigate("/edituser/${user.id}")}> Edit</button>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}
export default UserList