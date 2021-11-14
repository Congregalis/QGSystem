package com.xjtu.qgsystem.vo;

public class ScoreVO {
    private int uncheckedNum;
    private int checkedNum;
    private int[] fluency;
    private int[] reasonable;
    private int[] relevance;

    public ScoreVO(int uncheckedNum, int checkedNum, int[] fluency, int[] reasonable, int[] relevance) {
        this.uncheckedNum = uncheckedNum;
        this.checkedNum = checkedNum;
        this.fluency = fluency;
        this.reasonable = reasonable;
        this.relevance = relevance;
    }

    public int getUncheckedNum() {
        return uncheckedNum;
    }

    public void setUncheckedNum(int uncheckedNum) {
        this.uncheckedNum = uncheckedNum;
    }

    public int getCheckedNum() {
        return checkedNum;
    }

    public void setCheckedNum(int checkedNum) {
        this.checkedNum = checkedNum;
    }

    public int[] getFluency() {
        return fluency;
    }

    public void setFluency(int[] fluency) {
        this.fluency = fluency;
    }

    public int[] getReasonable() {
        return reasonable;
    }

    public void setReasonable(int[] reasonable) {
        this.reasonable = reasonable;
    }

    public int[] getRelevance() {
        return relevance;
    }

    public void setRelevance(int[] relevance) {
        this.relevance = relevance;
    }
}
