import React, { useEffect, useState } from "react";
import axios from "axios";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Card } from "primereact/card";
import { Tag } from "primereact/tag";

function MyInterviews() {
  const [interviews, setInterviews] = useState([]);

  useEffect(() => {
    const fetchInterviews = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/interviews/for-js", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        });
        setInterviews(response.data);
      } catch (error) {
        console.error("Error fetching interviews", error);
      }
    };

    fetchInterviews();
  }, []);

  const formatDate = (dateStr) => {
    return new Date(dateStr).toLocaleDateString();
  };

  const outcomeBodyTemplate = (rowData) => {
    const outcome = rowData.outcome;
    let severity = "info";
    if (outcome === "PASSED") severity = "success";
    else if (outcome === "FAILED") severity = "danger";
    else if (outcome === "PENDING") severity = "warning";

    return <Tag value={outcome} severity={severity} />;
  };

  return (
    <div className="container mt-5">
      <Card title="My Scheduled Interviews" className="shadow">
        <DataTable value={interviews} paginator rows={5} stripedRows >
          <Column field="interviewId" header="Interview ID" sortable></Column>
          <Column field="applicationId" header="Application ID" sortable></Column>
          <Column field="interviewDate" header="Date" body={(rowData) => formatDate(rowData.interviewDate)} sortable />
          <Column field="interviewLocation" header="Location" sortable></Column>
          <Column field="interviewMode" header="Mode" sortable></Column>
          <Column header="Outcome" body={outcomeBodyTemplate} sortable />
        </DataTable>
        
      </Card>
    </div>
  );
}

export default MyInterviews;
