package com.xjtu.qgsystem.controller;

import com.xjtu.qgsystem.entity.Context;
import com.xjtu.qgsystem.repository.ContextRepository;
import com.xjtu.qgsystem.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/context")
public class ContextController {

    @Autowired
    ContextService contextService;

    @RequestMapping("/all/{pageNum}")
    public Page<Context> findAll(@PathVariable("pageNum") Integer pageNum) {
        return contextService.getAllPage(pageNum);
    }

    @RequestMapping("/context/find/{pageNum,pagesize,sort,cSubject,cLanguage}")
    public Page<Context> findByCondition(@PathVariable("pageNum") Integer pageNum,@PathVariable("pagesize") Integer pagesize,@PathVariable("sort") String sort,@PathVariable("cSubject") String subject,@PathVariable("cLanguage") String language) {
        return contextService.getAllPagebyCondition(pageNum,pagesize,sort,subject,language);
    }
}
