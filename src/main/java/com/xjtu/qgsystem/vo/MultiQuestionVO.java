package com.xjtu.qgsystem.vo;

import com.xjtu.qgsystem.entity.MultiQuestion;

public class MultiQuestionVO {
    String qId;
    String qText;
    String qAnswer;
    boolean qIsChecked;
    Integer qFluency;
    Integer qRelevance;
    Integer qReasonability;
    Integer qDifficulty;

    public MultiQuestionVO(MultiQuestion multiQuestion) {
        this.qId=Long.toString(multiQuestion.getqId());
        this.qText=multiQuestion.getqText();
        this.qDifficulty=multiQuestion.getqDifficulty();
        this.qReasonability=multiQuestion.getqReasonability();
        this.qRelevance=multiQuestion.getqRelevance();
        this.qFluency=multiQuestion.getqFluency();
        this.qAnswer=multiQuestion.getqAnswer();
        this.qIsChecked=multiQuestion.isqIsChecked();
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public String getqAnswer() {
        return qAnswer;
    }

    public void setqAnswer(String qAnswer) {
        this.qAnswer = qAnswer;
    }

    public boolean isqIsChecked() {
        return qIsChecked;
    }

    public void setqIsChecked(boolean qIsChecked) {
        this.qIsChecked = qIsChecked;
    }

    public Integer getqFluency() {
        return qFluency;
    }

    public void setqFluency(Integer qFluency) {
        this.qFluency = qFluency;
    }

    public Integer getqRelevance() {
        return qRelevance;
    }

    public void setqRelevance(Integer qRelevance) {
        this.qRelevance = qRelevance;
    }

    public Integer getqReasonability() {
        return qReasonability;
    }

    public void setqReasonability(Integer qReasonability) {
        this.qReasonability = qReasonability;
    }

    public Integer getqDifficulty() {
        return qDifficulty;
    }

    public void setqDifficulty(Integer qDifficulty) {
        this.qDifficulty = qDifficulty;
    }
}
