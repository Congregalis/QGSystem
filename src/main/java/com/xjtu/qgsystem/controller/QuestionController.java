package com.xjtu.qgsystem.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.xjtu.qgsystem.entity.Context;
import com.xjtu.qgsystem.service.ContextService;
import com.xjtu.qgsystem.service.QuestionService;
import com.xjtu.qgsystem.util.result.Result;
import com.xjtu.qgsystem.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @SaCheckLogin
    @RequestMapping("/all/{pageNum}")
    public Result findAll(@PathVariable("pageNum") Integer pageNum) {
        return ResultUtil.success(questionService.getAllPage(pageNum));
    }

    @RequestMapping("/all/{token}/{pageNum}")
    public Result findAll(@PathVariable("token") String token, @PathVariable("pageNum") Integer pageNum) {
        return ResultUtil.success(questionService.getCheckedPageByToken(pageNum, token));
    }

    @SaCheckRole("admin")
    @RequestMapping("/unchecked")
    public Result getFirstUnchecked() {
        return ResultUtil.success(questionService.getFirstUnchecked());
    }

    @SaCheckRole(value= {"annotator", "admin"}, mode = SaMode.OR)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateQuestion(@RequestParam(value = "id", required = true) Long id,
                                 @RequestParam(value = "contextId", required = true) Long contextId,
                                 @RequestParam(value = "context", required = true) String context,
                                 @RequestParam(value = "question", required = true) String question,
                                 @RequestParam(value = "answer", required = true) String answer,
                                 @RequestParam(value = "type", required = true) String type,
                                 @RequestParam(value = "evaluationSpans", required = true) String evaluationSpans,
                                 @RequestParam(value = "distractors", required = true) String distractors) {
        return ResultUtil.success(questionService.updateQuestion(id, contextId, context, question, answer, type, evaluationSpans, distractors));
    }

    @SaCheckRole(value = {"annotator", "admin"}, mode = SaMode.OR)
    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public Result rateQuestion(@RequestParam(value = "id", required = true) Long id,
                               @RequestParam(value = "fluency", required = true) int fluency,
                               @RequestParam(value = "reasonable", required = true) int reasonable,
                               @RequestParam(value = "relevance", required = true) int relevance,
                               @RequestParam(value = "difficulty", required = true) int difficulty,
                               @RequestParam(value = "token", required = true) String token) {
        return ResultUtil.success(questionService.rateQuestion(id, fluency, reasonable, relevance, difficulty, token));
    }

    @SaCheckRole(value = {"admin"})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deletedQuestion(@PathVariable("id") Long id) {
        boolean res = questionService.deleteQuestion(id);

        return res ? ResultUtil.success("删除成功") : ResultUtil.fail("删除失败");
    }

    @SaCheckRole(value = {"annotator", "admin"}, mode = SaMode.OR)
    @RequestMapping("/random")
    public Result getRandomQuestion() {
        return ResultUtil.success(questionService.getRandomQuestion());
    }

    @RequestMapping("/score")
    public Result getScorePicData() {
        return ResultUtil.success((questionService.getScorePicData()));
    }

    @RequestMapping("/distribution")
    public Result getDistribution() {
        return ResultUtil.success(questionService.getDistributionByTitle());
    }

    @RequestMapping("/picData")
    public Result getPicData() {
        return ResultUtil.success((questionService.getScorePicData()));
    }

    @RequestMapping("/type")
    public Result getTypeDistribution() {return ResultUtil.success(questionService.getTypeDistribution());}

    @RequestMapping("/difficulty")
    public Result getDifficultyDistribution() {return ResultUtil.success(questionService.getDifficultyDistribution());}

    @RequestMapping(value = "/findbycondition",method = RequestMethod.POST)
    public Result findbyCondition(@RequestBody List<Map<String, String>> mapList) {
        Integer page = Integer.parseInt(mapList.get(0).get("page"));
        Integer pageSize = Integer.parseInt(mapList.get(1).get("limit"));
        //String sort=mapList.get(2).get("sort");
        String title=mapList.get(2).get("cTitle");
        String language=mapList.get(3).get("cLanguage");
        String subject=mapList.get(4).get("cSubject");
        Integer fluency = Integer.parseInt(mapList.get(5).get("qFluency"));
        Integer reasonability = Integer.parseInt(mapList.get(6).get("qReasonability"));
        Integer relevance = Integer.parseInt(mapList.get(7).get("qRelevance"));
        Integer difficulty= Integer.parseInt(mapList.get(8).get("qDifficulty"));
        Integer score = Integer.parseInt(mapList.get(9).get("qScore"));
        return ResultUtil.success(questionService.findbycondition(page,pageSize,title,language,subject,fluency,reasonability,relevance,difficulty,score));
    }

}
