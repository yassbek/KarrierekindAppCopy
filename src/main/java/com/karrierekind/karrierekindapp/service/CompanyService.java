package com.karrierekind.karrierekindapp.service;

import com.karrierekind.karrierekindapp.entity.Company;
import com.karrierekind.karrierekindapp.entity.JobPosting;
import com.karrierekind.karrierekindapp.entity.SurveyQuestion;
import com.karrierekind.karrierekindapp.repository.CompanyRepository;
import com.karrierekind.karrierekindapp.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    // Firebase-related components should also be injected, possibly using a separate Firebase service.

    public Company registerCompany(Company company) {
        // TODO: Integrate with Firebase for authentication.
        // After successful Firebase registration, store the company in the database.
        return companyRepository.save(company);
               }

    public Company updateCompanySurvey(Long companyId, List<SurveyQuestion> surveyQuestions) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setSurveyQuestions(surveyQuestions);
            return companyRepository.save(company);
        }
        // Handle the case where the company is not found or throw an exception.
        return null;
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public JobPosting createJobPosting(Long companyId, JobPosting jobPosting) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            jobPosting.setCompany(companyOptional.get());
            // TODO: Register the job with Google Cloud Talent Solution.
            return jobPostingRepository.save(jobPosting);
        }
        // Handle the case where the company is not found or throw an exception.
        return null;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    public void deleteAllCompanies() {companyRepository.deleteAll();}
    public Optional<Company> getCompanyById(Long companyId) {
        return companyRepository.findById(companyId);
    }

    public void setMetaSkillsScoresForCompany(Long talentId, Integer teamorientierung, Integer kundenorientierung, Integer flexibilitaet, Integer regelOrientierung, Integer initiative) {
        Optional<Company> optionalCompany = companyRepository.findById(talentId);

        if (optionalCompany.isEmpty()) {
            throw new RuntimeException("Talent with ID " + talentId + " not found.");
        }

        Company company = optionalCompany.get();

        company.setTeamorientierung(teamorientierung);
        company.setKundenorientierung(kundenorientierung);
        company.setFlexibilitaet(flexibilitaet);
        company.setRegelOrientierung(regelOrientierung);
        company.setInitiative(initiative);

        companyRepository.save(company);
    }

    // Add other service methods as required.

}

