package com.xjtu.qgsystem.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.xjtu.qgsystem.service.QuestionService;
import com.xjtu.qgsystem.util.result.Result;
import com.xjtu.qgsystem.util.result.ResultUtil;
import com.xjtu.qgsystem.vo.ConditionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//
//    @SaCheckRole(value= {"annotator", "admin"}, mode = SaMode.OR)
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public Result updateQuestion(@RequestParam(value = "id", required = true) Long id,
//                                 @RequestParam(value = "contextId", required = true) Long contextId,
//                                 @RequestParam(value = "context", required = true) String context,
//                                 @RequestParam(value = "question", required = true) String question,
//                                 @RequestParam(value = "answer", required = true) String answer,
//                                 @RequestParam(value = "questionType", required = true) String questionType,
//                                 @RequestParam(value = "cognitiveType", required = true) String cognitiveType,
//                                 @RequestParam(value = "distractorsArray", required = true) String[] distractorsArray,
//                                 @RequestParam(value = "whType", required = true) String whType) {
//        return ResultUtil.success(questionService.updateQuestion(id, contextId, context, question, answer, questionType, cognitiveType, distractorsArray, whType));
//    }

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

//    @SaCheckRole(value = {"admin"})
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public Result deletedQuestion(@PathVariable("id") Long id) {
//        boolean res = questionService.deleteQuestion(id);
//
//        return res ? ResultUtil.success("删除成功") : ResultUtil.fail("删除失败");
//    }

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

    @RequestMapping("/findbycondition")
    public Result findByCondition(ConditionVO findByConditionVO){
            return ResultUtil.success(questionService.findByCondition(findByConditionVO));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateContextAndQuestion(@RequestParam(value = "cId", required = true) String cId,
                                 @RequestParam(value = "cTitle", required = true) String cTitle,
                                 @RequestParam(value = "cText", required = true) String cText,
                                 @RequestParam(value = "qId", required = true) String qId,
                                 @RequestParam(value = "qText", required = true) String qText,
                                 @RequestParam(value = "qAnswer", required = true) String qAnswer,
                                 @RequestParam(value = "qFluency", required = true) String qFluency,
                                 @RequestParam(value = "qReasonability", required = true) String qReasonability,
                                 @RequestParam(value = "qRelevence", required = true) String qRelevence,
                                 @RequestParam(value = "qDifficulty", required = true) String qDifficulty,
                                 @RequestParam(value = "qDistractorList", required = true) String qDistractorList) {
        System.out.println(qDistractorList);
        return ResultUtil.success(questionService.updateContextAndQuestion(cId, cTitle, cText, qId, qText, qAnswer, qFluency, qReasonability, qRelevence, qDifficulty, qDistractorList));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result deletedQuestion(@RequestParam(value = "cId", required = true) String cId,
                                  @RequestParam(value = "qId", required = true) String qId) {
        boolean res = questionService.deleteQuestion(Long.parseLong(cId), Long.parseLong(qId));
        return res ? ResultUtil.success("删除成功") : ResultUtil.fail("删除失败");
    }
}

