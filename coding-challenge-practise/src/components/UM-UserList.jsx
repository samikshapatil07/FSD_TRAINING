import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";


function UserList() {
    const navigate = useNavigate()
    let [getArry, setGetArry] = useState([])
    let [msg, setMsg] = useState("")

    //api to get all users
    useEffect(() => {
        const getUsers = async () => {
            try {
                const response = await axios.get('https://jsonplaceholder.typicode.com/users')
                setGetArry(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        getUsers()
    }, [])


    
    //api to delete the user
   const deleteUser = async(userId) => {
    try{
        await axios.delete('https://jsonplaceholder.typicode.com/users/${userId}')
        let temp =[...getArry]
        temp = temp.filter(u => u.id !== userId)
        setGetArry(temp) 
      setMsg("deleted")   }
    catch(err)
    {
        console.log(err)
    }
   }

   
     return (
    <div className="container mt-5">
      <h3 className="text-center mb-4">User List</h3>

      {msg && <div className="alert alert-danger text-center">{msg}</div>}

      <table className="table ">
        <thead className="table">
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Company</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {
          getArry.map((user) => (
            <tr key={user.id}>
              <td>{user.name}</td>
              <td>{user.email}</td>
              <td>{user.phone}</td>
              <td>{user.company?.name}</td>
              <td>
                <button className="btn btn-danger btn-sm" onClick={() => deleteUser(user.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default UserList; 