import { Link } from "react-router-dom";

function Sidebar(){
    
 return(
    <div>
         {/* Sidebar */}
            <nav className="navbar navbar-inverse fixed-top" id="sidebar-wrapper" role="navigation">
                <ul className="nav sidebar-nav">
                    <div className="sidebar-header">
                        <div className="sidebar-brand">
                            <a href="#">LMS Author</a>
                        </div>
                    </div>
                    <li><Link to="/author">Home</Link></li>
                    <li><Link to="/author/courses">Courses</Link></li>
                    <li><Link to="/author/enrollments">Enrollments</Link></li>
                    <li><Link to="/author/profile">Profile</Link></li>

                    <li><a href="#services">Services</a></li>
                    <li><a href="#contact">Contact</a></li>
                    <li><a href="#followme">Follow me</a></li>
                </ul>
            </nav>
    </div>
 )
}
export default Sidebar;