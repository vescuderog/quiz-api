package com.merkone.quiz.service.impl;

import com.merkone.api.quiz.model.QuestionDTO;
import com.merkone.api.quiz.server.QuestionsApiDelegate;
import com.merkone.quiz.app.exception.NotFoundException;
import com.merkone.quiz.domain.QAnswers;
import com.merkone.quiz.domain.QQuestions;
import com.merkone.quiz.mapper.QuizMapper;
import com.merkone.quiz.repository.QuestionsRepository;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

        // Add the location header with the question id
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(qquestion.getId())
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<QuestionDTO> updateQuestion(String questionId, QuestionDTO question) {
        LOGGER.debug("Updating question with id: {}", questionId);
        QQuestions qquestion = getQQuestion(questionId);
        if (!StringUtils.isEmpty(question.getQuestion())) {
            qquestion.setQuestion(question.getQuestion());
            qquestion.setUpdateDate(new Date());
        }
        // Replace the questions
        if (null != question.getAnswers() && !question.getAnswers().isEmpty()) {
            // Delete the old answers
            if (null != qquestion.getQAnswersList() && !qquestion.getQAnswersList().isEmpty()) {
                qquestion.getQAnswersList().clear();
            }

            // Add the new answers
            List<QAnswers> answers = QuizMapper.INSTANCE.map(question.getAnswers());
            answers.stream().forEach(a -> {
                a.setId(qquestion.getId());
                a.setQuestionId(qquestion);
                a.setCreationDate(new Date());
                a.setUpdateDate(new Date());
            });
            qquestion.getQAnswersList().addAll(answers);
        }
        return new ResponseEntity<>(QuizMapper.INSTANCE.map(questionsRepository.save(qquestion)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteQuestionById(String questionId) {
        LOGGER.debug("Deleting question with id: {}", questionId);
        questionsRepository.delete(getQQuestion(questionId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<QuestionDTO> getQuestionById(String questionId) {
        LOGGER.debug("Getting question with id: {}", questionId);
        return new ResponseEntity<>(QuizMapper.INSTANCE.map(getQQuestion(questionId)), HttpStatus.OK);
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

    private QQuestions getQQuestion(String questionId) {
        QQuestions question = questionsRepository.findById(UUID.fromString(questionId)).orElse(null);
        if (null == question) {
            throw new NotFoundException(questionId);
        }
        return question;
    }

}
