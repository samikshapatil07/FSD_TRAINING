
import axios from "axios"

export const fetchAllCourses = (dispatch) => () => {
    console.log('In action....')
    //call the API 
    axios.get('http://localhost:8080/api/course/all')
        .then(function (response) {
            console.log(response.data)
            dispatch({
                'payload': response.data,
                'type': 'FETCH_ALL_COURSES'
            })
        })

}
