package com.xjtu.qgsystem.vo;

import java.util.List;

public class GetMultiCQVO {
    Integer total;
    List<MultiCandQ> dataList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<MultiCandQ> getDataList() {
        return dataList;
    }

    public void setDataList(List<MultiCandQ> dataList) {
        this.dataList = dataList;
    }
}
