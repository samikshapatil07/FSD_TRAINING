import React from "react";
import "../../css/companies.css";


function TopCompanies ()  {

  const companies = [
    "reliance.jpg", "tata.jpg", "birla.jpg",  "flipkart.jpg", "samsung.jpg",  "tcs.jpg", "ibm.png"];

  return (
    <div className="top-companies-section">
      <h2 className="top-companies-title">
        Top Companies Listing on <span>CareerCrafter</span>
      </h2>

      <p className="top-companies-subtitle">
        Find jobs that fit your career aspirations.
      </p>
      
      <div className="company-logos-container">

        {
        companies.map((logo, index) => (
          <div  className="company-logo-card" key={index}>
            <img src={`/images/${logo}`}  />
          </div>
        ))
        }

      </div>
    </div>
  );
};

export default TopCompanies;
