package com.merkone.quiz.service.impl;

import com.merkone.api.quiz.model.QuestionDTO;
import com.merkone.api.quiz.server.QuestionsApiDelegate;
import com.merkone.quiz.mapper.QuizMapper;
import com.merkone.quiz.repository.QuestionsRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author vescudero
 */
@Service
public class QuestionsApiDelegateImpl implements QuestionsApiDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionsApiDelegateImpl.class);

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public ResponseEntity<Void> addQuestion(QuestionDTO question) {
        return QuestionsApiDelegate.super.addQuestion(question); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<Void> deleteQuestionById(String questionId) {
        return QuestionsApiDelegate.super.deleteQuestionById(questionId); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<QuestionDTO> getQuestionById(String questionId) {
        return QuestionsApiDelegate.super.getQuestionById(questionId); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<List<QuestionDTO>> getQuestions() {
        LOGGER.debug("Getting questions");
        List<QuestionDTO> list
                = this.questionsRepository.findAll()
                        .stream()
                        .map(QuizMapper.INSTANCE::map)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
