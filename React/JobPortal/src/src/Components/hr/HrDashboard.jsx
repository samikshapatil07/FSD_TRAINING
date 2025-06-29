import { useEffect, useRef, useState } from 'react';
import { Outlet, useNavigate, useLocation } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import HrNavbar from './HrNavbar';

import HrSidebar from '../hr/HrSidebar';

function HrDashboard() {

  return (
    <div>

        <HrNavbar />
        <HrSidebar />
        <Outlet />
      
  
    </div>
  )


}

export default HrDashboard;

