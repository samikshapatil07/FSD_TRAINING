import React from "react";
import "../../css/companies.css";


function TopCompanies ()  {

  //an arry for company images
  const companies = [
    "reliance.jpg", "tata.jpg", "birla.jpg",  "flipkart.jpg", "samsung.jpg",  "tcs.jpg", "ibm.png"];

  return (
    <div className="top-companies-section">
      <h2 className="top-companies-title">
        Top Companies Listing on <span>CareerCrafter</span></h2>
      

      <p className="top-companies-subtitle">
        Find jobs that fit your career aspirations.
      </p>
      
      <div className="company-logos-container">

        {
        // Loop through each company logo and show the image
        //in the map function logo is each item from the companies array and index is the position number of each item in the array
        //as at index 0 ie reliannce.jpg and so on....
        companies.map((logo, index) => (
          <div  className="company-logo-card" key={index}> {/* Each logo in a box */}
            <img src={`/images/${logo}`}  />
          </div>
        ))
        }

      </div>
    </div>
  );
};

export default TopCompanies;