package com.merkone.quiz.repository;

import com.merkone.quiz.domain.QQuestions;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vescudero
 */
public interface QuestionsRepository extends JpaRepository<QQuestions, UUID> {

}
