package com.merkone.quiz.service.impl;

import com.merkone.api.quiz.model.AnswerDTO;
import com.merkone.api.quiz.model.QuestionDTO;
import com.merkone.quiz.domain.QQuestions;
import com.merkone.quiz.mapper.QuizMapper;
import com.merkone.quiz.repository.QuestionsRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author vescudero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuestionsApiDelegateImpl.class)
public class QuestionsApiDelegateImplTest {

    private QuestionDTO questionDTO;

    private String id;

    private Optional<QQuestions> qQuestions;

    @Autowired
    QuestionsApiDelegateImpl questionsApiDelegateImpl;

    @MockBean
    QuizMapper quizMapper;

    @MockBean
    QuestionsRepository questionsRepository;

    @Before
    public void setUp() {
        // Init DTO
        questionDTO = new QuestionDTO();
        questionDTO.setQuestion("Question test?");
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswer("Answer test");
        answerDTO.setIsCorrect(true);
        questionDTO.setAnswers(Arrays.asList(answerDTO));

        // Init entity
        id = String.valueOf(UUID.randomUUID());
        qQuestions = Optional.of(new QQuestions(UUID.fromString(id)));
    }

    @Test
    public void addQuestionTest() {
        ResponseEntity response = questionsApiDelegateImpl.addQuestion(questionDTO);

        Mockito.verify(questionsRepository, Mockito.times(1)).save(Mockito.any(QQuestions.class));
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getQuestionByIdTest() {
        Mockito.when(questionsRepository.findById(Mockito.any(UUID.class))).thenReturn(qQuestions);

        ResponseEntity<QuestionDTO> response = questionsApiDelegateImpl.getQuestionById(id);

        Mockito.verify(questionsRepository, Mockito.times(1)).findById(Mockito.any(UUID.class));
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        QuestionDTO question = response.getBody();
        Assert.assertThat(id, equalTo(question != null ? question.getId() : null));
    }

    @Test
    public void getQuestionsTest() {
        Mockito.when(questionsRepository.findAll()).thenReturn(
                Arrays.asList(qQuestions.isPresent() ? qQuestions.get() : null));

        ResponseEntity<List<QuestionDTO>> response = questionsApiDelegateImpl.getQuestions();

        Mockito.verify(questionsRepository, Mockito.times(1)).findAll();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertThat(response.getBody(), hasSize(1));
    }

}
