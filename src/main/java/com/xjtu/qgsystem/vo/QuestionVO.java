package com.xjtu.qgsystem.vo;

import com.xjtu.qgsystem.entity.Question;

/**
 * @author：yuehao
 * @version:v1.0
 */
public class QuestionVO extends Question {
    private String[] distractorsArray;

    public String[] getDistractorsArray() {
        return distractorsArray;
    }

    public void setDistractorsArray(String[] distractorsArray) {
        this.distractorsArray = distractorsArray;
    }
}
