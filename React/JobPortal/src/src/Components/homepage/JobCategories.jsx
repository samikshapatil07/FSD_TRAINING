import React from "react";
import { BsCodeSlash,BsPaintBucket, BsCurrencyRupee } from "react-icons/bs";
import { HiSpeakerphone } from "react-icons/hi";
import { PiAtomBold } from "react-icons/pi";
import "../../css/jobcategories.css";


function JobCategories ()  {
  const categories = [
    { title: "Software Development", icon: <BsCodeSlash />, bg: "#EDE9FE" },
    { title: "Data Science", icon: <PiAtomBold />, bg: "#DCFCE7" },
    { title: "Graphic Design", icon: <BsPaintBucket />, bg: "#FEF3C7" },
    { title: "Marketing", icon: <HiSpeakerphone />, bg: "#DBEAFE" },
    { title: "Finance", icon: <BsCurrencyRupee />, bg: "#FEF2E2" }
  ];

  return (
    <div className="job-categories-container">
      <h5 className="job-categories-title">Jobs Category</h5>
      <div className="job-category-wrapper">
        {
        categories.map((cat, index) => (
          <div key={index} className="job-category-card">
            
              <div className="job-category-icon" style={{ backgroundColor: cat.bg } }>
               {cat.icon}
            </div>

            <span className="job-category-title">{cat.title}</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default JobCategories;
