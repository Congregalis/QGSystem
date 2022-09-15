package com.xjtu.qgsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class MultiQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qId;
    private String qText;//内容
    private String qAnswer;//
    @ManyToOne
    @JoinColumn(name = "cId")
    private MultiContext reference;
    private int qFluency;//
    private int qReasonability;//
    private int qRelevance;//
    private int qDifficulty;//难度
    private boolean qIsChecked;
    private int qScore;
    private String qType;

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
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

    public MultiContext getReference() {
        return reference;
    }

    public void setReference(MultiContext reference) {
        this.reference = reference;
    }

    public int getqFluency() {
        return qFluency;
    }

    public void setqFluency(int qFluency) {
        this.qFluency = qFluency;
    }

    public int getqReasonability() {
        return qReasonability;
    }

    public void setqReasonability(int qReasonability) {
        this.qReasonability = qReasonability;
    }

    public int getqRelevance() {
        return qRelevance;
    }

    public void setqRelevance(int qRelevance) {
        this.qRelevance = qRelevance;
    }

    public int getqDifficulty() {
        return qDifficulty;
    }

    public void setqDifficulty(int qDifficulty) {
        this.qDifficulty = qDifficulty;
    }

    public boolean isqIsChecked() {
        return qIsChecked;
    }

    public void setqIsChecked(boolean qIsChecked) {
        this.qIsChecked = qIsChecked;
    }

    public int getqScore() {
        return qScore;
    }

    public void setqScore(int qScore) {
        this.qScore = qScore;
    }





    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }
}
