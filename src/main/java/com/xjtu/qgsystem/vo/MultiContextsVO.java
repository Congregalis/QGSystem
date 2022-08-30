package com.xjtu.qgsystem.vo;

import com.xjtu.qgsystem.entity.MultiContext;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class MultiContextsVO {
    Integer total;
    List<MultiContextVO> contextList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<MultiContextVO> getContextList() {
        return contextList;
    }

    public void setContextList(List<MultiContext> multiContextList) {
        List<MultiContextVO> temp=new ArrayList<>();
        for (MultiContext context:multiContextList
             ) {
            temp.add(new MultiContextVO(context));
        }
        this.contextList=temp;
    }
}
