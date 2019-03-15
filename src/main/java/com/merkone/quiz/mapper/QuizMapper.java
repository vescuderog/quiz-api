package com.merkone.quiz.mapper;

import com.merkone.api.quiz.model.AnswerDTO;
import com.merkone.api.quiz.model.QuestionDTO;
import com.merkone.quiz.domain.QAnswers;
import com.merkone.quiz.domain.QQuestions;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author vescudero
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuizMapper {

    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    @Mappings({
        @Mapping(source = "isCorrect", target = "correct")})
    QAnswers map(AnswerDTO answerDTO);

    AnswerDTO map(QAnswers qAnswers);

    List<QAnswers> map(List<AnswerDTO> answerDTO);

    @Mappings({
        @Mapping(source = "answers", target = "QAnswersList")})
    QQuestions map(QuestionDTO questionDTO);

    @Mappings({
        @Mapping(source = "QAnswersList", target = "answers")})
    QuestionDTO map(QQuestions qQuestions);

    // Custom map
    default UUID fromStringToUUID(String uuid) {
        return uuid != null ? UUID.fromString(uuid) : null;
    }

    default String fromUUIDToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    default Date fromLongToDate(Long date) {
        return date != null ? new Date(date) : null;
    }

    default Long fromDateToLong(Date date) {
        return date != null ? date.getTime() : null;
    }

}
