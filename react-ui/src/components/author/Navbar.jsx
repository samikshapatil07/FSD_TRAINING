import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { setUserDetails } from "../../store/actions/UserAction";

function Navbar() {
    //const [name,] = useState(localStorage.getItem('name'));
    //instead of this use hook
    const [user,] = useState(useSelector(state => state.user));
    const navigate = useNavigate();
      const dispatch = useDispatch();

    const logout = () => {
        let u = {
            "username": "",
            "role": ""
        }

        setUserDetails(dispatch)(u);
        localStorage.clear();
        navigate("/")
    }
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light" style={{ width: '100%', position: 'fixed', height: '70px', padding: '20px 40px', zIndex: 999, top: 0, left: 0 }}>
            <div className="container-fluid">
                <div className="d-flex ms-auto align-items-center">
                    <div>
                        <div style={{
                            position: 'absolute',
                            left: '50%',
                            transform: 'translateX(-50%)',
                            fontSize: '20px',
                            fontWeight: 'bold'
                        }}>
                            Welcome! {name}
                        </div>
                    </div>
                    {/* <span className="me-3">Welcome severus snape</span> */}
                    <button className="btn btn-outline-success" onClick={logout}>Logout</button>
                </div>
            </div>
        </nav>

    )
}

export default Navbar; 