package com.karrierekind.karrierekindapp.entity;

import jakarta.persistence.*;

import java.util.Map;

@Entity
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private SurveyQuestion question;

    @ElementCollection
    @MapKeyJoinColumn(name="meta_value_id")
    @Column(name="score")
    private Map<MetaValue, Integer> scores; // This maps each meta value to a score

    // getters, setters, etc.


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SurveyQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SurveyQuestion question) {
        this.question = question;
    }

    public Map<MetaValue, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<MetaValue, Integer> scores) {
        this.scores = scores;
    }

    public Long getQuestionId() {
        return question.getId();
    }
}