package com.jobportal.JobPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.JobPortal.model.ApplicationUpdate;

@Repository
public interface ApplicationUpdateRepository extends JpaRepository<ApplicationUpdate, Integer> {

    List<ApplicationUpdate> findByApplicationApplicationId(Integer applicationId);

    List<ApplicationUpdate> findByJobSeekerJobSeekerId(Integer jobSeekerId);
}
