package com.xjtu.qgsystem.vo;

import com.xjtu.qgsystem.entity.MultiContext;

public class MultiContextVO {
    private String cId;

    private String cText;

    private boolean cIsChecked=false;
    public MultiContextVO(MultiContext multiContext){
        this.cId=Long.toString(multiContext.getCid());
        this.cIsChecked=multiContext.iscIsChecked();
        this.cText= multiContext.getcText();
    }
    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcText() {
        return cText;
    }

    public boolean iscIsChecked() {
        return cIsChecked;
    }

    public void setcIsChecked(boolean cIsChecked) {
        this.cIsChecked = cIsChecked;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }
}
