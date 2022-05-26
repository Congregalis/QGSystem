package com.xjtu.qgsystem.vo;

public class ConditionVO {
    Integer page;
    Integer pagesize;
    String cTitle;
    String cLanguage;
    String cSubject;
    Integer qFluency;
    Integer qReasonability;
    Integer qRelevance;
    Integer qDifficulty;
    Integer qScore;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
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
