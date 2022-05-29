package com.xjtu.qgsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
public class Question {

    private Long id;
    private String text;//内容
    private int answerStart = -1;
    private String answerText;//
    private Context reference;
    private int fluency;//
    private int reasonable;//
    private int relevance;//
    private int difficulty;//难度
    private int checkedTimes;//当标注次数大于2的时候才吧isChecked设置为1
    private int score;
    private int isDeleted;
    private Long userId;
    @JsonIgnore
    private String userList;//标注者的名字，用$分割
    private int isChecked=0;//是否标注过0、1
    private String questionType;//题型
    private String cognitiveType;//认知类型
    private String distractors;//干扰项
    private String whType;

    public Question(Question question) {
        BeanUtils.copyProperties(question, this);
    }

    public Question() {

    }

    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }

    public int getIsChecked() {
        return isChecked;
    }


    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnswerStart() {
        return answerStart;
    }

    public void setAnswerStart(int answerStart) {
        this.answerStart = answerStart;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @ManyToOne(targetEntity = Context.class)
    @JoinColumn(name = "contextId", referencedColumnName = "id")
    public Context getReference() {
        return reference;
    }

    public void setReference(Context reference) {
        this.reference = reference;
    }

    public int getFluency() {
        return fluency;
    }

    public void setFluency(int fluency) {
        this.fluency = fluency;
    }

    public int getReasonable() {
        return reasonable;
    }

    public void setReasonable(int reasonable) {
        this.reasonable = reasonable;
    }

    public int getRelevance() {
        return relevance;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    public int getCheckedTimes() {
        return checkedTimes;
    }

    public void setCheckedTimes(int checkedTimes) {
        this.checkedTimes = checkedTimes;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDistractors() { return distractors; }

    public void setDistractors(String distractors) { this.distractors = distractors; }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getCognitiveType() {
        return cognitiveType;
    }

    public void setCognitiveType(String cognitiveType) {
        this.cognitiveType = cognitiveType;
    }

    public String getWhType() {
        return whType;
    }

    public void setWhType(String whType) {
        this.whType = whType;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answerStart=" + answerStart +
                ", answerText='" + answerText + '\'' +
                ", reference=" + reference +
                ", fluency=" + fluency +
                ", reasonable=" + reasonable +
                ", relevance=" + relevance +
                ", difficulty=" + difficulty +
                ", checkedTimes=" + checkedTimes +
                ", score=" + score +
                ", isDeleted=" + isDeleted +
                ", userId=" + userId +
                ", isChecked=" + isChecked +
                ", questionType='" + questionType + '\'' +
                ", cognitiveType='" + cognitiveType + '\'' +
                ", distractors='" + distractors + '\'' +
                ", whType='" + whType + '\'' +
                '}';
    }
}
