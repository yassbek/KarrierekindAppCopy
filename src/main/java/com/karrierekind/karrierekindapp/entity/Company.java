package com.karrierekind.karrierekindapp.entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String password; // Store hashed
    private String email;
    // Other attributes specific to a company


    @OneToOne
    private User user;

    public Company() {
    }

    public Company(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "company")
    private List<SurveyQuestion> surveyQuestions;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<JobPosting> jobPostings;

    private Integer teamorientierung;
    private Integer kundenorientierung;
    private Integer flexibilitaet;
    private Integer regelOrientierung;
    private Integer initiative;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTeamorientierung() {
        return teamorientierung;
    }

    public void setTeamorientierung(Integer teamorientierung) {
        this.teamorientierung = teamorientierung;
    }

    public Integer getKundenorientierung() {
        return kundenorientierung;
    }

    public void setKundenorientierung(Integer kundenorientierung) {
        this.kundenorientierung = kundenorientierung;
    }

    public Integer getFlexibilitaet() {
        return flexibilitaet;
    }

    public void setFlexibilitaet(Integer flexibilitaet) {
        this.flexibilitaet = flexibilitaet;
    }

    public Integer getRegelOrientierung() {
        return regelOrientierung;
    }

    public void setRegelOrientierung(Integer regelOrientierung) {
        this.regelOrientierung = regelOrientierung;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    // Getters, setters, etc.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SurveyQuestion> getSurveyQuestions() {
        return surveyQuestions;
    }

    public void setSurveyQuestions(List<SurveyQuestion> surveyQuestions) {
        this.surveyQuestions = surveyQuestions;
    }

    public List<JobPosting> getJobPostings() {
        return jobPostings;
    }

    public void setJobPostings(List<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }


    @Override
    public String toString() {
        return "Company{" +
                "firstName='" + (user != null ? user.getFirstName() : "") + '\'' +
                ", lastName='" + (user != null ? user.getLastName() : "") + '\'' +
                ", email='" + (user != null ? user.getEmail() : "") + '\'' +
                ", ID='" + id + '\'' + +
                '}';
    }
}
