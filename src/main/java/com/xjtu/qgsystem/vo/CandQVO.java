package com.xjtu.qgsystem.vo;

import com.xjtu.qgsystem.entity.Question;

import java.util.List;

public class CandQVO {
    Long cId;
    String cText;
    List<Question> qList;
    String cLanguage;
    String cSubject;
    String cTitle;

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public String getcText() {
        return cText;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }

    public List<Question> getqList() {
        return qList;
    }

    public void setqList(List<Question> qList) {
        this.qList = qList;
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

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }
}
