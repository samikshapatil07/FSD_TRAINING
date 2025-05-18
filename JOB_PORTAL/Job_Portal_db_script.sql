create database Job_Portal;

use Job_Portal;

CREATE TABLE job_seeker (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    education TEXT,
    skills TEXT,
    experience TEXT
);

CREATE TABLE admin (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    company_name VARCHAR(100)
);

CREATE TABLE job_posting (
    job_id INT PRIMARY KEY AUTO_INCREMENT,
    posted_by INT,
    job_title VARCHAR(100),
    description TEXT,
    skills TEXT,
    location VARCHAR(100),
    salary DECIMAL(10,2),
    department VARCHAR(100),
    company VARCHAR(100),
    experience VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (posted_by) REFERENCES admin(user_id)
);

CREATE TABLE applications (
    app_id INT PRIMARY KEY AUTO_INCREMENT,
    job_id INT,
    user_id INT,
    status ENUM('Applied', 'Accepted', 'Rejected') DEFAULT 'Applied',
    resume_path VARCHAR(255),
    applied_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (job_id) REFERENCES job_posting(job_id),
    FOREIGN KEY (user_id) REFERENCES job_seeker(user_id)
);

CREATE TABLE application_updates (
    update_id INT PRIMARY KEY AUTO_INCREMENT,
    app_id INT,
    updated_resume_path VARCHAR(255),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (app_id) REFERENCES applications(app_id)
);



CREATE TABLE interviews (
    interview_id INT PRIMARY KEY AUTO_INCREMENT,
    app_id INT,
    interview_date DATE,
    interview_location VARCHAR(255),
    FOREIGN KEY (app_id) REFERENCES applications(app_id)
);

CREATE TABLE resume_history (
    resume_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    file_path VARCHAR(255),
    uploaded_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES job_seeker(user_id)
);

CREATE TABLE notifications (
    notif_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES job_seeker(user_id)
);

CREATE TABLE seeker_activity_log (
    user_id INT,
    activity_type ENUM('view_job', 'apply_job', 'edit_profile', 'upload_resume'),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES job_seeker(user_id)
);