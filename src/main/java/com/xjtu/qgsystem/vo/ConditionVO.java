package com.xjtu.qgsystem.vo;
//用于接受前端筛选条件
public class ConditionVO {
    Integer pageNum;
    Integer pageLimit;
    String sort;
    String cTitle;
    String cLanguage;
    String cSubject;
    String cSource;
    String qType;
    String qQwType;
    String qCognitiveType;
    Integer qFluency;
    Integer qRelevance;
    Integer qDifficulty;
    Integer qReasonability;
    Integer qScore;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getcSource() {
        return cSource;
    }

    public void setcSource(String cSource) {
        this.cSource = cSource;
    }

    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }

    public String getqQwType() {
        return qQwType;
    }

    public void setqQwType(String qQwType) {
        this.qQwType = qQwType;
    }

    public String getqCognitiveType() {
        return qCognitiveType;
    }

    public void setqCognitiveType(String qCognitiveType) {
        this.qCognitiveType = qCognitiveType;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getcLanguage() {
        return cLanguage;
    }

    public void setcLanguage(String cLanguage) {
        this.cLanguage = cLanguage;
    }

    public String getcSubject() {
        return cSubject;
    }

    public void setcSubject(String cSubject) {
        this.cSubject = cSubject;
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

    public Integer getqScore() {
        return qScore;
    }

    public void setqScore(Integer qScore) {
        this.qScore = qScore;
    }
}
