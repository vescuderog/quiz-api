package com.merkone.quiz.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "q_questions", schema = "public")
public class QQuestions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Type(type = "pg-uuid")
    @Column(name = "id")
    private UUID id;
    @Size(max = 2147483647)
    @Column(name = "question")
    private String question;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QAnswers> qAnswersList;

    public QQuestions() {
    }

    public QQuestions(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public List<QAnswers> getQAnswersList() {
        return qAnswersList;
    }

    public void setQAnswersList(List<QAnswers> qAnswersList) {
        this.qAnswersList = qAnswersList;
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
        final QQuestions other = (QQuestions) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "QQuestions{id=" + id + ", question=" + question + ", creationDate="
                + creationDate + ", updateDate=" + updateDate + ", qAnswersList="
                + qAnswersList + "}";
    }

}
