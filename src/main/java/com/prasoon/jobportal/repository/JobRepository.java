package com.prasoon.jobportal.repository;

import com.prasoon.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByLocationContainingAndSalaryGreaterThanEqual(String location, Double salary);
}