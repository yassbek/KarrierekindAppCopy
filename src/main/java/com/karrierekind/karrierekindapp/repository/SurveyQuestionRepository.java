package com.karrierekind.karrierekindapp.repository;

import com.karrierekind.karrierekindapp.entity.QuestionType;
import com.karrierekind.karrierekindapp.entity.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {
    List<SurveyQuestion> findByType(QuestionType type);
}

