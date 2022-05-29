package com.xjtu.qgsystem.vo;

import com.xjtu.qgsystem.entity.Question;

public class WithTextQuesVO extends QuestionVO{
    private String cText;

    public WithTextQuesVO(Question question) {
        super(question);
    }

    public String getcText() {
        return cText;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }
}
