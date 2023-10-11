package com.karrierekind.karrierekindapp.entity;

import jakarta.persistence.*;

@Entity
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private SurveyQuestion question;

    @ManyToOne
    private Choice chosenChoice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SurveyQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SurveyQuestion question) {
        this.question = question;
    }

    public Choice getChosenOption() {
        return chosenChoice;
    }

    public void setChosenOption(Choice chosenChoice) {
        this.chosenChoice = chosenChoice;
    }
}




