import { useState } from "react";

function Example2() {
    let [firstName, setfirstName] = useState();
    let [lastName, setlastName] = useState();
    let [age, setage] = useState();
    let [userArry, setUserArry] = useState([]);

    const addToUser = async () => {
        // try{
        // const response = await axios.get(`https://dummyjson.com/c/4e67-0b66-4c8f-833a`);
        // setuserArry(response.data);
        // }
        // catch(err){
        //     console.log(err);
        // }
         const newUser = {
        firstName: firstName,
        lastName: lastName,
        age: age
    };
    setUserArry([...userArry, newUser]);
    }

return (
        <div className="container">
            <h2 className="text-center mt-4">Example 2</h2>
            <div className="row">
                <div className="col-md-4 mb-4">
                    <div className="card p-3 shadow-sm">
                        <label>First Name:</label>
                        <input type="text" value={firstName} onChange={$e => setfirstName($e.target.value)} className="form-control mb-2" />

                        <label>Last Name:</label>
                        <input type="text" value={lastName} onChange={$e => setlastName($e.target.value)} className="form-control mb-2" />

                        <label>Age:</label>
                        <input type="text" value={age} onChange={$e => setage($e.target.value)} className="form-control mb-2" />

                        <button className="btn btn-primary" onClick={addToUser}>Add User</button>
                    </div>
                </div>

                <div className="col-md-8">
                    <h4>User Details</h4>
                    <table className="table table-bordered">
                        <thead className="table-light">
                            <tr>
                                <th>Sr.no</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Age</th>
                            </tr>
                        </thead>
                        <tbody>
                            {userArry.map((user, index) => (
                                <tr key={index}>
                                    <td>{index + 1}</td>
                                    <td>{user.firstName}</td>
                                    <td>{user.lastName}</td>
                                    <td>{user.age}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )

}

export default Example2;