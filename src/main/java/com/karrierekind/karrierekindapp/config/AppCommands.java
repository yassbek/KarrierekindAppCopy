package com.karrierekind.karrierekindapp.config;

import com.google.cloud.talent.v4.*;
import com.karrierekind.karrierekindapp.entity.Company;
import com.karrierekind.karrierekindapp.entity.JobPosting;
import com.karrierekind.karrierekindapp.entity.Talent;
import com.karrierekind.karrierekindapp.model.RegistrationRequest;
import com.karrierekind.karrierekindapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Command(command = "app")
public class AppCommands {

    @Autowired
    private UserService userService;

    @Autowired
    private JobPostingService jobPostingService;

    @Autowired
    private GoogleCloudTalentService googleCloudTalentService;

    @Autowired
    private TalentService talentService;

    @Autowired
    private CompanyService companyService;



    @Command(command = "register-user")
    public String registerUser(String email, String password, String userType, String firstName, String lastName) {
        RegistrationRequest request = new RegistrationRequest(email, password);
        request.setUserType(userType);
        request.setFirstName(firstName);
        request.setLastName(lastName);

        try {
            userService.registerUser(request);
            return "User registered successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Command(command = "get-all-talents")
    public List<Talent> getAllTalents() {
        try {
            return talentService.getAllTalents();
        } catch (Exception e) {
            // Handle the exception or return an appropriate error message
            System.err.println("Error fetching talents: " + e.getMessage());
            return null; // Return null or an empty list if preferred.
        }
    }

    @Command(command = "get-all-companies")
    public List<Company> getAllCompanies() {
        try {
            return companyService.getAllCompanies();
        } catch (Exception e) {
            // Handle the exception or return an appropriate error message
            System.err.println("Error fetching companies: " + e.getMessage());
            return null; // Return null or an empty list if preferred.
        }
    }

    @Command(command = "get-all-jobs")
    public List<JobPosting> getAllJobs() {
        try {
            return jobPostingService.getAllJobPostings();
        } catch (Exception e) {
            // Handle the exception or return an appropriate error message
            System.err.println("Error fetching companies: " + e.getMessage());
            return null; // Return null or an empty list if preferred.
        }
    }

    @Command(command = "delete-all-talents")
    public String deleteAllTalents() {
        try {
             talentService.deleteAllTalents();
             return "All Talents Deleted Successfully";
        } catch (Exception e) {
            // Handle the exception or return an appropriate error message
            System.err.println("Error deleting talents: " + e.getMessage());
            return null; // Return null or an empty list if preferred.
        }
    }
    @Command(command = "delete-all-companies")
    public String deleteAllCompanies() {
        try {
            companyService.deleteAllCompanies();
            return "All Companies Deleted Successfully";
        } catch (Exception e) {
            // Handle the exception or return an appropriate error message
            System.err.println("Error deleting Companies: " + e.getMessage());
            return null; // Return null or an empty list if preferred.
        }
    }
    @Command(command = "set-hard-skills")
    public String setHardSkillsForTalent(Long talentId, List<String> hardSkills) {
        try {
            talentService.setHardSkillsForTalent(talentId, hardSkills);
            return "Hard skills set successfully for talent with ID: " + talentId;
        } catch (Exception e) {
            System.err.println("Error setting hard skills: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    @Command(command = "set-talent-meta-skills-scores")
    public String setMetaSkillsScores(Long talentId, Integer teamorientierung, Integer kundenorientierung, Integer flexibilitaet, Integer regelOrientierung, Integer initiative) {
        try {
            talentService.setMetaSkillsScoresForTalent(talentId, teamorientierung, kundenorientierung, flexibilitaet, regelOrientierung, initiative);
            return "Meta skills scores set successfully for talent with ID: " + talentId;
        } catch (Exception e) {
            System.err.println("Error setting meta skills scores: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    @Command(command = "get-talent-info")
    public String getTalentInfo(Long talentId) {
        Optional<Talent> optionalTalent = talentService.getTalentById(talentId);

        if (!optionalTalent.isPresent()) {
            return "Talent with ID " + talentId + " not found.";
        }

        Talent talent = optionalTalent.get();

        StringBuilder info = new StringBuilder();

        // Basic Info
        info.append(talent.toString()).append("\n");

        // Hard Skills
        info.append("Hard Skills: ");
        if (talent.getHardSkills() != null && !talent.getHardSkills().isEmpty()) {
            info.append(String.join(", ", talent.getHardSkills()));
        } else {
            info.append("None");
        }
        info.append("\n");

        // Meta Skills (Soft Skills)
        info.append("Meta Skills:\n");
        info.append("Teamorientierung: ").append(talent.getTeamorientierung()).append("\n");
        info.append("Kundenorientierung: ").append(talent.getKundenorientierung()).append("\n");
        info.append("Flexibilität: ").append(talent.getFlexibilitaet()).append("\n");
        info.append("Regel-/Weisungsorientierung: ").append(talent.getRegelOrientierung()).append("\n");
        info.append("Initiative: ").append(talent.getInitiative()).append("\n");

        return info.toString();
    }
    @Command(command = "set-job-posting")
    public String createJobPosting(Long companyId, String title, String description) {
        try {
            JobPosting jobPosting = new JobPosting();
            jobPosting.setTitle(title);
            jobPosting.setDescription(description);

            jobPostingService.createJobPosting(jobPosting, companyId);
            return "Job posting created successfully for company with ID: " + companyId;
        } catch (Exception e) {
            return "Error creating job posting: " + e.getMessage();
        }
    }
    @Command(command = "set-company-meta-skills-scores")
    public String setCompanyMetaSkillsScores(Long companyId, Integer teamorientierung, Integer kundenorientierung, Integer flexibilitaet, Integer regelOrientierung, Integer initiative) {
        try {
            companyService.setMetaSkillsScoresForCompany(companyId, teamorientierung, kundenorientierung, flexibilitaet, regelOrientierung, initiative);
            return "Meta skills scores set successfully for Company with ID: " + companyId;
        } catch (Exception e) {
            System.err.println("Error setting meta skills scores: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
    @Command(command = "get-job-posting")
    public String getJobPostings(Long companyId) {
        try {
            List<JobPosting> jobPostings = jobPostingService.getJobPostingsByCompanyId(companyId);

            if (jobPostings.isEmpty()) {
                return "No job postings found for company with ID: " + companyId;
            }

            StringBuilder response = new StringBuilder();
            response.append("Job postings for company with ID: ").append(companyId).append("\n");

            for (JobPosting jobPosting : jobPostings) {
                response.append("- ").append(jobPosting.getId()).append("\n");
                response.append("- ").append(jobPosting.getTitle());
                response.append("- ").append(jobPosting.getDescription()).append("\n");

            }

            return response.toString();
        } catch (Exception e) {
            return "Error fetching job postings: " + e.getMessage();
        }
    }

    @Command(command = "create-custom-job-posting")
    public String createCustomJobPosting(Long talentId) {
        try {
            // Retrieve talent info from your own system
            Optional<Talent> optionalTalent = talentService.getTalentById(talentId);

            if (optionalTalent.isEmpty()) {
                return "Talent with ID " + talentId + " not found.";
            }

            Talent talent = optionalTalent.get();

            // Use the TalentToJobService to convert the Talent to a Job
            TalentToJobService converter = new TalentToJobService();

            // Use the default company name for creating jobs
            String defaultCompanyName = "projects/karriere-kind/tenants/b85029a8-8700-0000-0000-00e115940a1d/companies/de4987d2-83d4-4a7a-82ca-ee82e38bb050";

            com.google.cloud.talent.v4.Job job = converter.convertTalentToJob(talent, defaultCompanyName);

            // Create the job on Google Cloud Talent Solution
            try (JobServiceClient jobServiceClient = JobServiceClient.create()) {
                String parent = "projects/karriere-kind/tenants/b85029a8-8700-0000-0000-00e115940a1d";

                CreateJobRequest createJobRequest = CreateJobRequest.newBuilder()
                        .setParent(parent)
                        .setJob(job)
                        .build();

                com.google.cloud.talent.v4.Job jobResponse = jobServiceClient.createJob(createJobRequest);

                return "Job posting created successfully on Google Cloud with Job ID: " + jobResponse.getName();
            }
        } catch (Exception e) {
            e.printStackTrace(); // This will print the exception stack trace to the console
            return "Error creating job posting on Google Cloud: " + (e.getMessage() != null ? e.getMessage() : "Unknown error");
        }
    }



    @Command(command = "create-default-company")
    public String createDefaultCompany() {
        try {
            // Initialize the client
            try (CompanyServiceClient companyServiceClient = CompanyServiceClient.create()) {

                // Build the company object you want to create
                com.google.cloud.talent.v4.Company companyToBeCreated = com.google.cloud.talent.v4.Company.newBuilder()
                        .setDisplayName("Default Company")
                        .setExternalId("externalId123") // Example external ID
                        .build();

                // Set the parent. For creating a company without explicitly setting a tenant,
                // we'd generally use the format "projects/{project_id}".
                String parent = String.format("projects/%s", "karriere-kind");

                // Use the createCompany method to create the company
                com.google.cloud.talent.v4.Company companyCreated =
                        companyServiceClient.createCompany(parent, companyToBeCreated);

                System.out.println("Company created: " + companyCreated);
                return "Company created with name: " + companyCreated.getDisplayName();

            }

        } catch (Exception e) {
            System.out.println("Got exception while creating company");
            e.printStackTrace();  // This will give you more detailed info about the exception
            return "Error creating company: " + e.getMessage();
        }
    }

    @Command(command = "talent-search-based-on-job-posting")
    public String talentSearchBasedOnJobPosting(Long jobPostingId) {
        try {
            // Retrieve job posting info from your own system
            Optional<JobPosting> optionalJobPosting = jobPostingService.getJobPostingById(jobPostingId);

            if (optionalJobPosting.isEmpty()) {
                return "Job Posting with ID " + jobPostingId + " not found.";
            }

            JobPosting jobPosting = optionalJobPosting.get();

            // Sanitize the title and description to remove special characters and limit the length
            String sanitizedTitle = sanitizeText(jobPosting.getTitle());
            String sanitizedDescription = sanitizeText(jobPosting.getDescription());

            // Variables for your Cloud Talent Solution setup
            String projectId = "karriere-kind"; // Replace with your project ID
            String tenantId = "b85029a8-8700-0000-0000-00e115940a1d";   // Replace with your tenant ID

            // Execute the actual search
            return searchJobs(projectId, tenantId, sanitizedTitle, sanitizedDescription);

        } catch (Exception e) {
            return "Error searching talent based on job posting: " + e.getMessage();
        }
    }

    private String sanitizeText(String text) {
        // Remove quotes, parentheses, and limit length to 100 characters
        String sanitized = text.replaceAll("[\"'()]", " ");
        return sanitized.length() > 100 ? sanitized.substring(0, 100) : sanitized;
    }

    // Search Jobs with title and description.
    private String searchJobs(String projectId, String tenantId, String title, String description) throws IOException {
        StringBuilder resultBuilder = new StringBuilder();

        try (JobServiceClient jobServiceClient = JobServiceClient.create()) {
            TenantName parent = TenantName.of(projectId, tenantId);

            // Construct the JobQuery
            JobQuery jobQuery = JobQuery.newBuilder()
                    .setQuery(title + " OR " + description) // You can expand on this as needed
                    .build();

            SearchJobsRequest request = SearchJobsRequest.newBuilder()
                    .setParent(parent.toString())
                    .setJobQuery(jobQuery)
                    .build();

            for (SearchJobsResponse.MatchingJob responseItem : jobServiceClient.searchJobs(request).getMatchingJobsList()) {
                resultBuilder.append("Job summary: ").append(responseItem.getJobSummary()).append("\n");
                resultBuilder.append("Job title snippet: ").append(responseItem.getJobTitleSnippet()).append("\n");
                Job job = responseItem.getJob();
                resultBuilder.append("Job name: ").append(job.getName()).append("\n");
                resultBuilder.append("Job title: ").append(job.getTitle()).append("\n\n");
            }
        }

        if(resultBuilder.length() == 0) {
            return "No matching jobs found.";
        }
        return resultBuilder.toString();
    }











}
