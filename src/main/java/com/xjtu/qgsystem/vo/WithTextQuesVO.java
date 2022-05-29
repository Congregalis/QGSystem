package com.xjtu.qgsystem.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xjtu.qgsystem.entity.Question;

public class WithTextQuesVO extends QuestionVO{
    private String cText;
    private String cTitle;
    private String cId;
    @JsonIgnore
    private String qCheckedTimes;
    @JsonIgnore
    private String qScore;
    @JsonIgnore
    private String qEvaluatorList;
    @JsonIgnore
    private String qIsChecked;
    @JsonIgnore
    private String qType;
    @JsonIgnore
    private String qCognitiveType;
    @JsonIgnore
    private String qQwType;


    public WithTextQuesVO(Question question) {
        super(question);
    }

    public String getcText() {
        return cText;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }
}
