import { useState } from "react";
import { todos } from "../sample data/todoData";

function TodoList() {
    let [todoArry, setTodoArry] = useState(todos);
    return (
        <div>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">ID</th>
                        <th scope="col">USER ID</th>
                        <th scope="col">TITLE</th>
                        <th scope="col">STATUS</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        todoArry.map((todo, index) => (
                            <tr key={todo.id}>
                                <th scope="row">{index + 1}</th>
                                <td>{todo.id}</td>
                                <td>{todo.userId}</td>
                                <td>{todo.title}</td>
                                <td>{todo.completed == true ? "Completed" : "Not-Completed"}</td>
                            </tr>
                        ))
                    }

                </tbody>
            </table>
        </div>
    )
}

export default TodoList;