package com.xjtu.qgsystem.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.xjtu.qgsystem.service.QuestionService;
import com.xjtu.qgsystem.util.result.Result;
import com.xjtu.qgsystem.util.result.ResultUtil;
import com.xjtu.qgsystem.vo.ConditionVO;
import com.xjtu.qgsystem.vo.GetFrontUpdateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    @SaCheckRole(value = {"annotator", "admin"}, mode = SaMode.OR)
    @RequestMapping("/random")
    public Result getRandomQuestion() {
        return ResultUtil.success(questionService.getRandomQuestion());
    }

    @RequestMapping("/randomByCondition")
    public Result getRandomByCondition(String cSubject,String cLanguage,String cSource) {
        return ResultUtil.success(questionService.getRandomByCondition(cSubject,cLanguage,cSource));
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

    @RequestMapping(value="/findbycondition",method = RequestMethod.POST)
    public Result findByCondition(@RequestBody ConditionVO findByConditionVO){
            return ResultUtil.success(questionService.findByCondition(findByConditionVO));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateContextAndQuestion(@RequestBody GetFrontUpdateParam updateParam) {
        System.out.println("------");
        System.out.println(updateParam.toString());
        System.out.println("------");
        return ResultUtil.success(questionService.updateContextAndQuestion(updateParam));
    }

    @RequestMapping(value = "/delete/{qid}", method = RequestMethod.DELETE)
    public Result deletedQuestion(@PathVariable("qid") String qId) {
        System.out.println("输出qid的值" + qId);
        boolean res = questionService.deleteQuestion(Long.parseLong(qId));
        return res ? ResultUtil.success("删除成功") : ResultUtil.fail("删除失败");
    }

    @RequestMapping(value = "/updateQuestions", method = RequestMethod.POST)
    public Result updateQuestions(@RequestBody Map<String, Object> params) {
        return ResultUtil.success(questionService.updateQuestions(params));
    }

    @RequestMapping(value = "/deleteQuestions/{cid}", method = RequestMethod.DELETE)
    public Result deletedQuestions(@PathVariable("cid") String cid) {
        System.out.println("输出cid的值" + cid);
        boolean res = questionService.deleteQuestions(Long.parseLong(cid));
        return res ? ResultUtil.success("删除成功") : ResultUtil.fail("删除失败");
    }
}

