//src/store/reducers/CourseReducer
const initialState = {
    courses: []
}
const CourseReducer = (state = initialState, action) => {
    if (action.type === "FETCH_ALL_COURSES") {
        console.log('in reducer ' + action.payload)
        return {
            ...state,
            courses: action.payload
        }
    }
    return state;
}
export default CourseReducer;