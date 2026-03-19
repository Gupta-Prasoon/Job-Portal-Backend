package com.prasoon.jobportal.service;

import com.prasoon.jobportal.dto.JobRequest;
import com.prasoon.jobportal.model.*;
import com.prasoon.jobportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    public String postJob(JobRequest request, String recruiterEmail) {
        User recruiter = userRepository.findByEmail(recruiterEmail)
                .orElseThrow(() -> new RuntimeException("Recruiter not found!"));
        Job job = new Job();
        job.setTitle(request.title);
        job.setCompany(request.company);
        job.setLocation(request.location);
        job.setDescription(request.description);
        job.setSalary(request.salary);
        job.setStatus(Job.JobStatus.OPEN);
        job.setRecruiter(recruiter);
        jobRepository.save(job);
        return "Job posted successfully!";
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> filterJobs(String location, Double salary) {
        return jobRepository
                .findByLocationContainingAndSalaryGreaterThanEqual(location, salary);
    }

    public String applyJob(Long jobId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found!"));
        if (applicationRepository.findByUserAndJob(user, job).isPresent()) {
            throw new RuntimeException("Already applied to this job!");
        }
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setStatus(Application.ApplicationStatus.APPLIED);
        applicationRepository.save(application);
        return "Applied successfully!";
    }

    public List<Application> getMyApplications(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return applicationRepository.findByUser(user);
    }
}