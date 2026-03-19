package com.prasoon.jobportal.controller;

import com.prasoon.jobportal.dto.JobRequest;
import com.prasoon.jobportal.model.*;
import com.prasoon.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    JobService jobService;

    @PostMapping("/post")
    public String postJob(@RequestBody JobRequest request, Authentication auth) {
        return jobService.postJob(request, auth.getName());
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/filter")
    public List<Job> filterJobs(@RequestParam String location,
                                @RequestParam Double salary) {
        return jobService.filterJobs(location, salary);
    }

    @PostMapping("/apply/{jobId}")
    public String applyJob(@PathVariable Long jobId, Authentication auth) {
        return jobService.applyJob(jobId, auth.getName());
    }

    @GetMapping("/my-applications")
    public List<Application> getMyApplications(Authentication auth) {
        return jobService.getMyApplications(auth.getName());
    }

    @GetMapping("/test")
    public String test(Authentication auth) {
        return "Email: " + auth.getName() + " Roles: " + auth.getAuthorities();
    }
}