package integration.questions;

import com.merkone.api.quiz.model.AnswerDTO;
import com.merkone.api.quiz.model.QuestionDTO;
import com.merkone.quiz.app.boot.QuizApplication;
import com.merkone.quiz.app.utils.UtilJson;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author vescudero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = QuizApplication.class)
@AutoConfigureMockMvc
public class QuestionsIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenAddQuestionThenStatus201() throws Exception {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestion("Question test?");
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswer("Answer test");
        answerDTO.setIsCorrect(true);
        questionDTO.setAnswers(Arrays.asList(answerDTO));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/quiz/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(UtilJson.asJsonString(questionDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenAddQuestionThenStatus400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/quiz/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(UtilJson.asJsonString(new QuestionDTO())))
                .andExpect(status().isBadRequest());
    }

}
