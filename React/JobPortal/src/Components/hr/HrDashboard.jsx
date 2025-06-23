import { useEffect, useRef, useState } from 'react';
import { Outlet, useNavigate, useLocation } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import PopUp from '../hr/PopUp';
import HrNavbar from './HrNavbar';

import HrSidebar from '../hr/HrSidebar';

function HrDashboard() {

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

        <HrNavbar />
        <HrSidebar />
        <Outlet />
      

        {showToast && <PopUp />}
  
    </div>
  )


}

export default HrDashboard;

