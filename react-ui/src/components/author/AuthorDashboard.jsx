import { useEffect, useRef, useState } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import '../../css/author.css';
import Sidebar from './Sidebar';
import Navbar from './Navbar';
import { fetchAllCourses } from '../../store/actions/CourseAction';
import { useDispatch } from 'react-redux';

function AuthorDashboard() {

    const navigate = useNavigate();
      const dispatch = useDispatch();

    useEffect(() => {
        let token = localStorage.getItem('token');
        if (token == null || token == undefined || token == "")
            navigate("/")
        
    fetchAllCourses(dispatch)

    }, [dispatch]);
    // State to track if the sidebar/overlay is "closed" (meaning the overlay is hidden and sidebar is collapsed)
    const [isClosed, setIsClosed] = useState(true);

    // Refs to access the DOM elements directly
    const overlayRef = useRef(null);
    const wrapperRef = useRef(null);
    /**
       * Handles the click event on the hamburger icon.
       * This function toggles the state which controls:
       * 1. The visual state of the hamburger icon (is-closed/is-open classes).
       * 2. The visibility of the overlay.
       * 3. The 'toggled' class on the main wrapper, which controls the sidebar's open/close state.
       */
    const handleHamburgerClick = () => {
        // Toggle the 'isClosed' state
        const newState = !isClosed;
        setIsClosed(newState);

        // Update overlay visibility based on the new state
        if (overlayRef.current) {
            overlayRef.current.style.display = newState ? 'none' : 'block';
        }

        // Toggle the 'toggled' class on the #wrapper element
        // This is because the hamburger button also has data-toggle="offcanvas" in the original jQuery
        if (wrapperRef.current) {
            wrapperRef.current.classList.toggle('toggled');
        }
    };

    return (

        <div className='container-fluid' >
            <div className='row'>
                <div className='col-lg-12'>
                    <Navbar />
                </div>
                
                <div className='col-lg-12'>
                    <div id="wrapper" ref={wrapperRef}  >

                        <div
                            className="overlay"
                            ref={overlayRef}
                            style={{ display: isClosed ? 'none' : 'block' }}
                        ></div>
                        <Sidebar />

                        <div id="page-content-wrapper">
                            {/* Hamburger button controlled by state and click handler */}
                            <button
                                type="button"
                                className={`hamburger animated fadeInLeft ${isClosed ? 'is-closed' : 'is-open'}`}
                                data-toggle="offcanvas" // This attribute is for jQuery, handled by handleHamburgerClick in React
                                onClick={handleHamburgerClick}
                            >
                                <span className="hamb-top"></span>
                                <span className="hamb-middle"></span>
                                <span className="hamb-bottom"></span>
                            </button>
                            <div className="container">
                                <div className="row">
                                    <Outlet />
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    )
}

export default AuthorDashboard;