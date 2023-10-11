package com.karrierekind.karrierekindapp.service;

import com.karrierekind.karrierekindapp.entity.Company;
import com.karrierekind.karrierekindapp.entity.JobPosting;
import com.karrierekind.karrierekindapp.repository.CompanyRepository;
import com.karrierekind.karrierekindapp.repository.JobPostingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobPosting createJobPosting(JobPosting jobPosting, Long companyId) {
        // Assuming Company has a OneToMany relationship with JobPosting
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("Company not found."));
        jobPosting.setCompany(company);

        return jobPostingRepository.save(jobPosting);
    }

    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    public Optional<JobPosting> getJobPostingById(Long id) {
        return jobPostingRepository.findById(id);
    }

    public JobPosting updateJobPosting(Long id, JobPosting updatedJobPosting) {
        if (!jobPostingRepository.existsById(id)) {
            throw new IllegalArgumentException("Job posting not found.");
        }
        updatedJobPosting.setId(id); // Ensuring the ID is set correctly for the update.
        return jobPostingRepository.save(updatedJobPosting);
    }
    @Transactional
    public List<JobPosting> getJobPostingsByCompanyId(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found."));
        return company.getJobPostings();  // Assuming Company has a OneToMany relationship with JobPosting and has a getJobPostings() method.
    }

    public void deleteJobPosting(Long id) {
        if (!jobPostingRepository.existsById(id)) {
            throw new IllegalArgumentException("Job posting not found.");
        }
        jobPostingRepository.deleteById(id);
    }

    // ... any other methods specific to job posting operations
}
