import axios from "axios"
import { useState, useEffect } from "react"


function AlbumListTable() {
    const [msg, setMsg] = useState("")
    const [album, setAlbum] = useState([])

    //api for get album list in table
    useEffect(() => {
        const getAll = async () => {
            try {
                const response = await axios.get('https://jsonplaceholder.typicode.com/albums')
                setAlbum(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        getAll()
    }, [])

    //delete api
    const OnDelete = async (userId) => {
        try {
            await axios.delete('https://jsonplaceholder.typicode.com/albums/${userId}')
            let temp = [...album]
            temp = temp.filter(u => u.id !== userId)
            setAlbum(temp)
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
                        <th>User Id</th>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        album.map((album) => (
                            <tr key={album.id}>
                                <td>{album.userId}</td>
                                <td>{album.id}</td>
                                <td>{album.title}</td>
                                <td>
                                    < button className="btn btn-danger" onClick={() => OnDelete(album.id)}> Delete</button>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}
export default AlbumListTable