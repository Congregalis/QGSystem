package com.xjtu.qgsystem.entity;
//这个表格用来保存分步问题生成中临时生成的上下文
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
public class MultiContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)

    private Long cId;

    private String cText;

    private boolean cIsChecked=false;

    public Long getCid() {
        return cId;
    }

    public void setCid(Long cId) {
        this.cId = cId;
    }

    public String getcText() {
        return cText;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
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
