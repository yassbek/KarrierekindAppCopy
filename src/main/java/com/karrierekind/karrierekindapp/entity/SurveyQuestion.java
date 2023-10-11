package com.karrierekind.karrierekindapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SurveyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private String answer;


    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Choice> choices;







    public QuestionType getType() {
        return type;
    }



    @ManyToOne
    @JoinColumn(name = "talent_id")
    private Talent talent;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    // Getters, setters, etc.


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Talent getTalent() {
        return talent;
    }

    public void setTalent(Talent talent) {
        this.talent = talent;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public List<Choice> getOptions() {
        return choices;
    }

    public void setOptions(List<Choice> choices) {
        this.choices = choices;
    }
}
