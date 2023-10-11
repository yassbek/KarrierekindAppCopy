package com.karrierekind.karrierekindapp.service;

import com.karrierekind.karrierekindapp.entity.Choice;
import com.karrierekind.karrierekindapp.entity.MetaValue;
import com.karrierekind.karrierekindapp.entity.QuestionType;
import com.karrierekind.karrierekindapp.entity.SurveyQuestion;
import com.karrierekind.karrierekindapp.repository.SurveyQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurveyQuestionService {

    @Autowired
    private SurveyQuestionRepository surveyQuestionRepository;

    // Create a new survey question
    public SurveyQuestion createQuestion(SurveyQuestion question) {
        return surveyQuestionRepository.save(question);
    }

    // Fetch all survey questions
    public List<SurveyQuestion> getAllQuestions() {
        return surveyQuestionRepository.findAll();
    }

    // Fetch a specific survey question by its ID
    public Optional<SurveyQuestion> getQuestionById(Long id) {
        return surveyQuestionRepository.findById(id);
    }

    // Update a specific survey question
    public SurveyQuestion updateQuestion(Long id, SurveyQuestion updatedQuestion) {
        if (surveyQuestionRepository.existsById(id)) {
            return surveyQuestionRepository.save(updatedQuestion);
        }
        return null;
    }

    // Delete a specific survey question by its ID
    public void deleteQuestion(Long id) {
        surveyQuestionRepository.deleteById(id);
    }

    // Fetch questions based on type
    public List<SurveyQuestion> getQuestionsByType(String type) {
        return surveyQuestionRepository.findByType(QuestionType.valueOf(type));
    }

    public Map<MetaValue,Integer> calculateMetaValueScore(Long questionId, String answerChosen) {
        // Fetch the question based on questionId
        Optional<SurveyQuestion> questionOpt = surveyQuestionRepository.findById(questionId);
        if (questionOpt.isPresent()) {
            SurveyQuestion question = questionOpt.get();

            // If it's a METACOMPETENCE question, process it
            if (question.getType() == QuestionType.METACOMPETENCE) {
                for (Choice choice : question.getOptions()) {
                    if (choice.getText().equals(answerChosen)) {
                        return choice.getScores();  // Return the MetaValue scores of the chosen option
                    }
                }
            }
        }

        return Collections.emptyMap();  // return an empty map if not found or not a METACOMPETENCE question
    }

    public Map<MetaValue, Integer> calculateMetaScores(List<Choice> selectedChoices) {
        Map<MetaValue, Integer> cumulativeScores = new HashMap<>();

        for (Choice choice : selectedChoices) {
            SurveyQuestion question = choice.getQuestion();
            if (question.getType() == QuestionType.HARD_SKILLS) {
                // Process hard skill option if needed
                // You can store them separately or use them for other logic
            } else if (question.getType() == QuestionType.METACOMPETENCE) {
                Map<MetaValue, Integer> optionScores = choice.getScores();
                for (Map.Entry<MetaValue, Integer> entry : optionScores.entrySet()) {
                    MetaValue metaValue = entry.getKey();
                    int score = entry.getValue();

                    cumulativeScores.put(metaValue, cumulativeScores.getOrDefault(metaValue, 0) + score);
                }
            }
        }

        return cumulativeScores;
    }



}
