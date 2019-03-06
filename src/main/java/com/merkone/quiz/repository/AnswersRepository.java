package com.merkone.quiz.repository;

import com.merkone.quiz.domain.QAnswers;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vescudero
 */
public interface AnswersRepository extends JpaRepository<QAnswers, UUID> {

}
