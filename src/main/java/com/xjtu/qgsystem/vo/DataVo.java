package com.xjtu.qgsystem.vo;

import java.util.List;
//封装一层又一层。。。
public class DataVo {
    List<ContextShowVO> dataList;
    Integer total;
    public List<ContextShowVO> getDataList() {
        return dataList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setDataList(List<ContextShowVO> dataList) {
        this.dataList = dataList;
    }
}
