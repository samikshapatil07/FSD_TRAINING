import { useEffect, useState } from "react"
import axios from "axios"

function AlbumListCard(){

    const[msg, setMsg] = useState("")
    const[album, setAlbum] = useState([])

    // api to get album list in cards
useEffect(() => {
    const getAll = async() => {
        try{
        const response = await axios.get('https://jsonplaceholder.typicode.com/albums')
        setAlbum(response.data)
        }
        catch(err)
        {
            console.log(err)
        }
    }
   getAll() 
},[])

//delete api
const onDelete = async(userId) => {
    try{
        await axios.delete('https://jsonplaceholder.typicode.com/albums')
        let temp = [...album]
        temp = temp.filter(u => u.id !== userId)
        setAlbum(temp)
        setMsg("deleted")
    }
    catch(err)
    {
        console.log(err)
    }
}

    return(
        <div className="container-fluid">
            <div className="row">
                <div className="col md-6">


                </div>
            </div>
        </div>
    )
}
export default AlbumListCard