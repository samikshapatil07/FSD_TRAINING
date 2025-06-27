package com.jobportal.JobPortal.dto;

import com.jobportal.JobPortal.model.Application;

public class ApplicationStatusDTO {
    private Application.Status status;

    public Application.Status getStatus() {
        return status;
    }

    public void setStatus(Application.Status status) {
        this.status = status;
    }
}
