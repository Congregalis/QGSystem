package com.xjtu.qgsystem.vo;

import com.xjtu.qgsystem.entity.Question;
import com.xjtu.qgsystem.util.DistractorSplit;

/**
 * @author：yuehao
 * @version:v1.0
 */
public class QuestionVO extends Question {
    private String[] qDistractorList;

    public QuestionVO(Question question) {
        super(question);
        qDistractorList = DistractorSplit.split(this.getDistractors());
    }

    public String[] getqDistractorList() {
        return qDistractorList;
    }

    public void setqDistractorList(String[] qDistractorList) {
        this.qDistractorList = qDistractorList;
    }

    @Override
    public String toString() {
        return "QuestionVO{}";
    }
}
