import { useState } from "react";
import { users } from "../sample data/userDetailes";

function UserDetails(){

     let [userArry, setUserArry] = useState(users);

     return(
        <div>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Sr. no</th>
                        <th scope="col">NAME</th>
                        <th scope="col">USERNAME ID</th>
                        <th scope="col">EMAIL</th>
                        <th scope="col">CITY</th>
                        <th scope="col">COMPANY NAME</th>

                    </tr>
                </thead>
                <tbody>
                    {
                        userArry.map((users, index) => (
                            <tr key={todo.Sr.no}>
                                <th scope="row">{index + 1}</th>
                                <td>{todo.name}</td>
                                <td>{todo.username}</td>
                                <td>{todo.email}</td>
                                <td>{todo.city}</td>
                                <td>{todo.company_name}</td>
                            </tr>
                        ))
                    }

                </tbody>
            </table>
        </div>
     )

}

export default UserDetails;