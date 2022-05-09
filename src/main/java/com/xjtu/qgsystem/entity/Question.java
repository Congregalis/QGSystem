package com.xjtu.qgsystem.entity;

import javax.persistence.*;

@Entity
public class Question {

    private Long id;
    private String text;
    private int answerStart = -1;
    private String answerText;
    private Context reference;
    private int fluency;
    private int reasonable;
    private int relevance;
    private int difficulty;//难度
    private int checkedTimes;
    private int score;
    private int isDeleted;
    private Long userId;

    private String type;
    private String evaluationSpans;//评估字段
    private String distractors;

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

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getEvaluationSpans() { return evaluationSpans; }

    public void setEvaluationSpans(String evaluationSpans) { this.evaluationSpans = evaluationSpans; }

    public String getDistractors() { return distractors; }

    public void setDistractors(String distractors) { this.distractors = distractors; }
}
