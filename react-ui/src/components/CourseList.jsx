import axios from "axios";
import { useEffect, useState } from "react";

function CourseList() {
    let [courses, setCourses] = useState([]);

    useEffect(() => {
        const getAllCourses = async () => {
            const response = await axios.get("http://localhost:8080/api/course/all");
            //console.log(response)
            setCourses(response.data);
        }
        getAllCourses();
    }, []);

    return (
        <div className="container">
            <div className="row">
                {
                    courses.map((c) => (
                        <div className="col-md-4">
                            <div className="card" style={{ 'width': '22rem' }}>
                                <div className="card-body">
                                    <h5 className="card-title">{c.title}</h5>
                                    <h6 className="card-subtitle mb-2 text-muted">Author: {c.author.name}</h6>
                                    <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                    <a href="#" className="card-link">View Details</a>
                                    <a href="#" className="card-link">Enroll</a>
                                </div>
                            </div>
                        </div>
                    ))
                }


            </div>
        </div>
    )
}

export default CourseList; 