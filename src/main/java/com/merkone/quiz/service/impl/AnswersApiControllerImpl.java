package com.merkone.quiz.service.impl;

import com.merkone.api.quiz.model.ResultDTO;
import com.merkone.api.quiz.server.AnswersApiDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author vescudero
 */
@Service
public class AnswersApiControllerImpl implements AnswersApiDelegate {

    @Override
    public ResponseEntity<ResultDTO> checkAnswer(String answerId, String questionId) {
        return AnswersApiDelegate.super.checkAnswer(answerId, questionId); //To change body of generated methods, choose Tools | Templates.
    }

}
