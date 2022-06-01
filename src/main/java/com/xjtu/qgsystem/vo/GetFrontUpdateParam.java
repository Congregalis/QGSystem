package com.xjtu.qgsystem.vo;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author：yuehao
 * @version:v1.0
 */
public class GetFrontUpdateParam {
    private String cTitle;
    private String cText;
    private String qId;
    private String qText;
    private String qAnswer;
    private Integer qFluency;
    private Integer qReasonability;
    private Integer qRelevance;
    private Integer qDifficulty;
    private String qDistractorList;

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getcText() {
        return cText;
    }

    public void setcText(String cText) {
        this.cText = cText;
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

    public Integer getqFluency() {
        return qFluency;
    }

    public void setqFluency(Integer qFluency) {
        this.qFluency = qFluency;
    }

    public Integer getqReasonability() {
        return qReasonability;
    }

    public void setqReasonability(Integer qReasonability) {
        this.qReasonability = qReasonability;
    }

    public Integer getqRelevance() {
        return qRelevance;
    }

    public void setqRelevance(Integer qRelevance) {
        this.qRelevance = qRelevance;
    }

    public Integer getqDifficulty() {
        return qDifficulty;
    }

    public void setqDifficulty(Integer qDifficulty) {
        this.qDifficulty = qDifficulty;
    }

    public String getqDistractorList() {
        return qDistractorList;
    }

    public void setqDistractorList(String qDistractorList) {
        this.qDistractorList = qDistractorList;
    }

    @Override
    public String toString() {
        return "GetFrontUpdateParam{" +
                "cTitle='" + cTitle + '\'' +
                ", cText='" + cText + '\'' +
                ", qId='" + qId + '\'' +
                ", qText='" + qText + '\'' +
                ", qAnswer='" + qAnswer + '\'' +
                ", qFluency=" + qFluency +
                ", qReasonability=" + qReasonability +
                ", qRelevance=" + qRelevance +
                ", qDifficulty=" + qDifficulty +
                ", qDistractorList='" + qDistractorList + '\'' +
                '}';
    }
}
