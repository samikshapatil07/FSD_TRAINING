// src/Components/hr/PopUp.jsx
import React, { useEffect, useState } from 'react';

function PopUp({ message = "Done" }) {
    
  const [show, setShow] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => setShow(false), 3000);
    return () => clearTimeout(timer);
  }, []);

  if (!show) return null;

  return (
    
    <div  className="toast-container position-fixed bottom-0 end-0 p-3"
      style={{ zIndex: 9999 }} >
     
      <div className="toast show bg-success text-white">
        <div className="toast-header bg-success text-white">
          <strong className="me-auto">Notification</strong>
          <button
            type="button"
            className="btn-close btn-close-white"
            onClick={() => setShow(false)} />
        </div>

        <div className="toast-body">{message}</div>
      </div>
    </div>
  );
}

export default PopUp;
