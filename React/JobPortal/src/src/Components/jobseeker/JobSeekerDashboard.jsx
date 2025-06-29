import { useEffect, useRef, useState } from 'react';
import { Outlet, useNavigate,useLocation } from 'react-router-dom';
import JsNavbar from './JsNavbar';
import JsSidebar from './JsSidebar';
import "../../css/js.css";
import JsHome from './JsHome';

function JobseekerDashboard() {

  const location = useLocation();
  const navigate = useNavigate();
  const [showToast, setShowToast] = useState(false);


  return (
    <div>

        <JsNavbar />
        <JsSidebar />
        <Outlet />
  
    </div>
  )


}

export default JobseekerDashboard;



