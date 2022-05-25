package com.xjtu.qgsystem.service;


import com.xjtu.qgsystem.entity.Context;
import com.xjtu.qgsystem.repository.ContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContextService {

    @Autowired
    ContextRepository contextRepository;

    /**
     * 返回所有查询结果的分页
     * @param pageNum 第几页
     */
    public Page<Context> getAllPage(int pageNum) {
        // 定义一页有多少条数据
        int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Context> page = contextRepository.findAll(pageable);
//        //获得总页数(这些数据需要分几页)
//        System.out.println("查询总页数："+page.getTotalPages());
//        //获得总记录数（数据库的总记录数）
//        System.out.println("查询总记录数："+page.getTotalElements());
//        //得到数据集合列表
//        System.out.println("数据集合列表:"+page.getContent());

        return page;
    }


    //新增
    /**
     * 返回所有条件查询上下文结果的分页
     * @param pageNum 第几页
     * @param pageSize 每页多少条数据
     * @param sort 排序方式
     * @param subject 学科
     * @param language 语言
     */
    public Page<Context> getAllPagebyCondition(int pageNum,int pageSize,String sort,String subject,String language) {
        Pageable pageable=PageRequest.of(pageNum, pageSize,Sort.Direction.DESC);
        if (sort=="+id"){
            pageable = PageRequest.of(pageNum, pageSize,Sort.Direction.ASC);
        }
        Page<Context> page = contextRepository.findBySubjectAndLanguage(subject,language,  pageable);
//        //获得总页数(这些数据需要分几页)
//        System.out.println("查询总页数："+page.getTotalPages());
//        //获得总记录数（数据库的总记录数）
//        System.out.println("查询总记录数："+page.getTotalElements());
//        //得到数据集合列表
//        System.out.println("数据集合列表:"+page.getContent());

        return page;
    }
}
