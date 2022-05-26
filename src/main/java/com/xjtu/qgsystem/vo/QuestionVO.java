package com.xjtu.qgsystem.vo;

import com.xjtu.qgsystem.entity.Question;
import com.xjtu.qgsystem.util.DistractorSplit;

/**
 * @author：yuehao
 * @version:v1.0
 */
public class QuestionVO extends Question {
    private String[] distractorsArray;

    public QuestionVO(Question question) {
        super(question);
        distractorsArray= DistractorSplit.split(this.getDistractors());
    }

    public String[] getDistractorsArray() {
        return distractorsArray;
    }

    public void setDistractorsArray(String[] distractorsArray) {
        this.distractorsArray = distractorsArray;
    }

    @Override
    public String toString() {
        return "QuestionVO{}";
    }
}
