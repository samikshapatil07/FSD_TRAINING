import { InputText } from 'primereact/inputtext';
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import { useState } from 'react';
import axios from 'axios';

function JsHome() {
    const [jobTitle, setJobTitle] = useState('');
    const [location, setLocation] = useState('');
    const [company, setCompany] = useState('');
    const [results, setResults] = useState([]);
    const [experience, setExperience] = useState('');

    const experienceOptions = [
        { label: 'Fresher', value: 'fresher' },
        { label: '1+ years', value: '1+ years' },
        { label: '2+ years', value: '1+ years' },
        { label: '3+ years', value: '3+ years' },
        { label: '4+ years', value: '1+ years' },
        { label: '5+ years', value: '5+ years' },
    ];

    const searchJobs = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/jobs/search`, {
                params: { jobTitle, location, company }
            });
            setResults(response.data);
        } catch (error) {
            console.error("Search error", error);
        }
    };

    return (
        <div className="p-4 text-center" >
            <h2 className="fw-bold mb-2">Find your dream job now</h2>
            <p className="mb-4">5 lakh+ jobs for you to explore</p>

            <div className="d-flex justify-content-center gap-2 flex-wrap">
                <span className="p-input-icon-left">
                    <InputText value={jobTitle} onChange={(e) => setJobTitle(e.target.value)} placeholder="Enter skills" />
                </span>

                <Dropdown value={experience} options={experienceOptions} onChange={(e) => setExperience(e.value)} placeholder="Select experience" />

                <InputText value={location} onChange={(e) => setLocation(e.target.value)} placeholder="Enter location" />

                <InputText value={company} onChange={(e) => setCompany(e.target.value)} placeholder="Enter company" />

                <Button label="Search" onClick={searchJobs} />
            </div>

            <div className="mt-4">
                {results.length > 0 && (
                    <ul className="list-group">
                        {results.map(job => (
                            <li key={job.jobId} className="list-group-item">
                                <strong>{job.jobTitle}</strong> at {job.company} - {job.location}
                                <div>{job.skills}</div>
                            </li>
                        ))}
                    </ul>
                )}
            </div>
        </div>
    );
}

export default JsHome;
