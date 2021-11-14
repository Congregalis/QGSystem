package com.xjtu.qgsystem.controller;

import com.xjtu.qgsystem.entity.Question;
import com.xjtu.qgsystem.service.QuestionService;
import com.xjtu.qgsystem.util.result.Result;
import com.xjtu.qgsystem.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @RequestMapping("/all/{pageNum}")
    public Result findAll(@PathVariable("pageNum") Integer pageNum) {
        return ResultUtil.success(questionService.getAllPage(pageNum));
    }

    @RequestMapping("/unchecked")
    public Result getFirstUnchecked() {
        return ResultUtil.success(questionService.getFirstUnchecked());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateQuestion(@RequestParam(value = "id", required = true) Long id,
                                 @RequestParam(value = "contextId", required = true) Long contextId,
                                 @RequestParam(value = "context", required = true) String context,
                                 @RequestParam(value = "question", required = true) String question,
                                 @RequestParam(value = "answer", required = true) String answer) {
        return ResultUtil.success(questionService.updateQuestion(id, contextId, context, question, answer));
    }

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public Result rateQuestion(@RequestParam(value = "id", required = true) Long id,
                               @RequestParam(value = "fluency", required = true) int fluency,
                               @RequestParam(value = "reasonable", required = true) int reasonable,
                               @RequestParam(value = "relevance", required = true) int relevance) {
        return ResultUtil.success(questionService.rateQuestion(id, fluency, reasonable, relevance));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deletedQuestion(@PathVariable("id") Long id) {
        boolean res = questionService.deleteQuestion(id);
        
        return res ? ResultUtil.success("删除成功") : ResultUtil.fail("删除失败");
    }

    @RequestMapping("/random")
    public Result getRandomQuestion() {
        return ResultUtil.success(questionService.getRandomQuestion());
    }

    @RequestMapping("/score")
    public Result getScorePicData() {
        return ResultUtil.success((questionService.getScorePicData()));
    }
}
