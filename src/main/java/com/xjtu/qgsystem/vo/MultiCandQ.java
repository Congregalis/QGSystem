package com.xjtu.qgsystem.vo;

import java.util.List;

public class MultiCandQ {
    String cId;
    String cText;
    List<MultiQuestionVO> qList;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcText() {
        return cText;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }

    public List<MultiQuestionVO> getqList() {
        return qList;
    }

    public void setqList(List<MultiQuestionVO> qList) {
        this.qList = qList;
    }
}
