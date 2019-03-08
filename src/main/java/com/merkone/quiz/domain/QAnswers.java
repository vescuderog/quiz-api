package com.merkone.quiz.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

/**
 *
 * @author vescudero
 */
@Entity
@Table(name = "q_answers", schema = "public")
public class QAnswers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Type(type = "pg-uuid")
    @Column(name = "id")
    private UUID id;
    @Size(max = 2147483647)
    @Column(name = "answer")
    private String answer;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "correct")
    private Boolean correct;
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne
    private QQuestions questionId;

    public QAnswers() {
    }

    public QAnswers(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public QQuestions getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QQuestions questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QAnswers other = (QAnswers) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "QAnswers{id=" + id + ", answer=" + answer + ", creationDate="
                + creationDate + ", updateDate=" + updateDate + ", correct="
                + correct + ", questionId=" + questionId + "}";
    }

}
