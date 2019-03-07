package com.merkone.quiz.service.impl;

import com.merkone.api.quiz.model.QuestionDTO;
import com.merkone.api.quiz.server.QuestionsApiDelegate;
import com.merkone.quiz.app.exception.NotFoundException;
import com.merkone.quiz.domain.QQuestions;
import com.merkone.quiz.mapper.QuizMapper;
import com.merkone.quiz.repository.QuestionsRepository;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
        LOGGER.debug("Adding question");
        // TODO: Validate mandatory parameters
        QQuestions qquestion = QuizMapper.INSTANCE.map(question);
        qquestion.setId(UUID.randomUUID());
        qquestion.setCreationDate(new Date());
        qquestion.setUpdateDate(new Date());
        qquestion.getQAnswersList().stream().forEach(a -> {
            a.setId(UUID.randomUUID());
            a.setQuestionId(qquestion);
            a.setCreationDate(new Date());
            a.setUpdateDate(new Date());
        });

        questionsRepository.save(qquestion);
        // TODO: Return header location
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<QuestionDTO> updateQuestion(String questionId, QuestionDTO question) {
        // TODO: Pending to do
        return QuestionsApiDelegate.super.updateQuestion(questionId, question);
    }

    @Override
    public ResponseEntity<Void> deleteQuestionById(String questionId) {
        // TODO: Pending to do
        return QuestionsApiDelegate.super.deleteQuestionById(questionId);
    }

    @Override
    public ResponseEntity<QuestionDTO> getQuestionById(String questionId) {
        LOGGER.debug("Getting question with id: " + questionId);
        QQuestions question = questionsRepository.findById(UUID.fromString(questionId)).orElse(null);
        if (null == question) {
            throw new NotFoundException(questionId);
        }
        return new ResponseEntity<>(QuizMapper.INSTANCE.map(question), HttpStatus.OK);
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
