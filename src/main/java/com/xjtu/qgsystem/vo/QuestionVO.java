package com.xjtu.qgsystem.vo;

//import com.sun.org.apache.bcel.internal.generic.I2F;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xjtu.qgsystem.entity.Question;
import com.xjtu.qgsystem.util.DistractorSplit;

import java.util.Arrays;

/**
 * @author：yuehao
 * @version:v1.0
 */
public class QuestionVO {
    private String qId;
    private String qText;//内容
    @JsonIgnore
    private int aStart = -1;
    private String qAnswer;//
    private int qFluency;//
    private int qReasonability;//
    private int qRelevance;//
    private int qDifficulty;//难度
    private int qCheckedTimes;
    private int qScore;
    private String [] qEvaluatorList={};
    private boolean qIsChecked;//是否标注过0、1
    private String qType;//题型
    private String qCognitiveType;//认知类型
    private String qQwType;
    private String[] qDistractorList={};
    public QuestionVO(){

    }
    public QuestionVO(Question question) {
        if (question.getDistractors()!=null){
            this.qDistractorList = DistractorSplit.split(question.getDistractors());
        }
        this.aStart=question.getAnswerStart();
        this.qAnswer= question.getAnswerText();
        this.qId=Long.toString(question.getId());
        this.qText=question.getText();//内容
        this.qFluency=question.getFluency();//
        this.qReasonability=question.getReasonable();//
        this.qRelevance= question.getRelevance();//
        this.qDifficulty=question.getDifficulty();//难度
        this.qCheckedTimes=question.getCheckedTimes();
        this.qScore=question.getScore();
        if (question.getUserId()!=null){
            this.qEvaluatorList =DistractorSplit.split(Long.toString(question.getUserId()));
        }

        if (question.getIsChecked()==0){
            this.qIsChecked=false;//是否标注过0、1
        }else {
            this.qIsChecked=true;
        }
        this.qType=question.getQuestionType();//题型
        this.qCognitiveType=question.getCognitiveType();//认知类型
        this.qQwType=question.getWhType();
    }

    public String[] getqDistractorList() {
        return qDistractorList;
    }

    public void setqDistractorList(String[] qDistractorList) {
        this.qDistractorList = qDistractorList;
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

    public int getaStart() {
        return aStart;
    }

    public void setaStart(int aStart) {
        this.aStart = aStart;
    }

    public String getqAnswer() {
        return qAnswer;
    }

    public void setqAnswer(String qAnswer) {
        this.qAnswer = qAnswer;
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

    public int getqCheckedTimes() {
        return qCheckedTimes;
    }

    public void setqCheckedTimes(int qCheckedTimes) {
        this.qCheckedTimes = qCheckedTimes;
    }

    public int getqScore() {
        return qScore;
    }

    public void setqScore(int qScore) {
        this.qScore = qScore;
    }

    public String[] getqEvaluatorList() {
        return qEvaluatorList;
    }

    public void setqEvaluatorList(String[] qEvaluatorList) {
        this.qEvaluatorList = qEvaluatorList;
    }

    public boolean isqIsChecked() {
        return qIsChecked;
    }

    public void setqIsChecked(boolean qIsChecked) {
        this.qIsChecked = qIsChecked;
    }

    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }

    public String getqCognitiveType() {
        return qCognitiveType;
    }

    public void setqCognitiveType(String qCognitiveType) {
        this.qCognitiveType = qCognitiveType;
    }

    public String getqQwType() {
        return qQwType;
    }

    public void setqQwType(String qQwType) {
        this.qQwType = qQwType;
    }

    @Override
    public String toString() {
        return "QuestionVO{" +
                "qId='" + qId + '\'' +
                ", qText='" + qText + '\'' +
                ", aStart=" + aStart +
                ", qAnswer='" + qAnswer + '\'' +
                ", qFluency=" + qFluency +
                ", qReasonability=" + qReasonability +
                ", qRelevance=" + qRelevance +
                ", qDifficulty=" + qDifficulty +
                ", qCheckedTimes=" + qCheckedTimes +
                ", qScore=" + qScore +
                ", UserIdList=" + Arrays.toString(qEvaluatorList) +
                ", qIsChecked=" + qIsChecked +
                ", qType='" + qType + '\'' +
                ", qCognitiveType='" + qCognitiveType + '\'' +
                ", qQwType='" + qQwType + '\'' +
                ", qDistractorList=" + Arrays.toString(qDistractorList) +
                '}';
    }
}
