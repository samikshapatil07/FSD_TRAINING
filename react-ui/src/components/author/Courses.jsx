import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from 'react-router-dom';

function Courses() {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        const getAllCourses = async () => {
            try {
                let response = await axios.get('http://localhost:8080/api/course/by-author',
                    { headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') } }
                )
                setCourses(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        getAllCourses();
    }, [])
    return (
        <>
            <h1>Courses</h1>
            <div className="container">
                <div className="row">
                    {
                        courses.map((c, index) => (
                            <div className="col-md-4 mb-4" key={index}>
                                <div className="card" >
                                    <img className="card-img-top" style={{ padding: '50px', height: '21rem' }} src={`../images/${c.imageUrl}`} alt={c.title} />
                                    <div className="card-body">
                                        <h5 className="card-title">{c.title}</h5>
                                        <p className="card-text">Credits : {c.credits}Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                        <p className="card-text">Author: {c.author.name}</p>
                                        <Link className="btn btn-primary" to={`/author/course-details/${c.id}`}>View Details</Link>                                    </div>
                                </div>
                            </div>
                        ))
                    }

                </div>
            </div>
        </>
    )
}

export default Courses;