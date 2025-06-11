import React from "react";
import Concepts from "./components/Concepts";
import Task from "./components/Task";
import TodoList from "./components/Todo";
import UserDetails from "./components/UserDetails";
import Post from "./components/Post";
import Albums from "./components/Albums";
import AddPost from "./components/AddPost";
import Example1 from "./components/Example1";
import Example2 from "./components/Example2";

function App() {

  return (
    <div>
      {/** <Concepts />**/}
      {/** <Task />**/}
      {/** <TodoList />**/}
      {/** <UserDetails />**/}
       {/* <Post /> */}
        {/* <Albums/> */}
        {/* <AddPost/> */}
        {/* <Example1/> */}
         <Example2/>
      
    </div>
  )
}

export default App;

/**
 * private int count; //0
 * getter and setter 
 * 
 * let [count,setCount]
 * 
 * ()=>{}
 * if u change the value of state variable, I will re-render it. 
 */
