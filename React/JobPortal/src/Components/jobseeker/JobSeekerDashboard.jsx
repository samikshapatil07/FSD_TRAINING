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
  
  //for the popup notification using toast(bootstrap)
  useEffect(() => {
    if (location.state?.showToast) {
      setShowToast(true);
      navigate(location.pathname, { replace: true, state: {} });
    }
  }, [location, navigate]);

  return (
    <div>

        <JsNavbar />
        <JsSidebar />
        <Outlet />
      

        {showToast && <PopUp />}
  
    </div>
  )


}

export default JobseekerDashboard;



