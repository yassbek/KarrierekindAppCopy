package com.karrierekind.karrierekindapp.entity;

import jakarta.persistence.*;
import java.util.List;
@Entity
public class Talent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // Store this hashed using bcrypt
    private String email;
    // Other attributes specific to a talent

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "talent")
    private List<SurveyQuestion> surveyQuestions;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "talent_hard_skills", joinColumns = @JoinColumn(name = "talent_id"))
    @Column(name = "skill")
    private List<String> hardSkills;

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

    public List<String> getHardSkills() {
        return hardSkills;
    }

    public void setHardSkills(List<String> hardSkills) {
        this.hardSkills = hardSkills;
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

    public Talent(User user) {
        this.user = user;
    }

    public Talent() {
    }

    // Getters, setters, etc.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "Talent{" +
                "firstName='" + (user != null ? user.getFirstName() : "") + '\'' +
                ", lastName='" + (user != null ? user.getLastName() : "") + '\'' +
                ", email='" + (user != null ? user.getEmail() : "") + '\'' +
                ", ID='" + id + '\'' + +
                '}';
    }

}

