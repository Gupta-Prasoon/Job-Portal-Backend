package com.prasoon.jobportal.repository;

import com.prasoon.jobportal.model.Application;
import com.prasoon.jobportal.model.User;
import com.prasoon.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUser(User user);
    Optional<Application> findByUserAndJob(User user, Job job);
}