package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.ApplicationUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationUpdateRepository extends JpaRepository<ApplicationUpdate, Long> {
    List<ApplicationUpdate> findByApplication_ApplicationId(Long applicationId);
}
