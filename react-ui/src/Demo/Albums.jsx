import { useEffect, useState } from "react";

function Album(){

    let[albumArry, setAlbumArry] = useState([]);
    
    useEffect(() =>{
                // call api using fetch : example 2
    fetch('https://jsonplaceholder.typicode.com/albums')
         .then( reps => reps.json())
         .then(data => setAlbumArry(data))
}, []);
    return(
 <div className="container my-4">
            <h1 className="mb-4 text-center">Albums</h1>
            <div className="row">
                {
                    albumArry.map((album) => (
                        <div className="col-md-3 mb-4" key={album.id}>
                            <div className="card h-100 shadow-sm">
                                <div className="card-body">
                                    <h5 className="card-title">{album.title.substring(0, 30)}...</h5>
                                </div>
                                <div className="card-footer">
                                    <small className="text-muted">User ID: {album.userId}</small>
                                </div>
                            </div>
                        </div>
                    ))
                }
            </div>
        </div>
    )
}

export default Album;