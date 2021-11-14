package com.xjtu.qgsystem.service;

import com.xjtu.qgsystem.entity.Context;
import com.xjtu.qgsystem.entity.Question;
import com.xjtu.qgsystem.repository.ContextRepository;
import com.xjtu.qgsystem.repository.QuestionRepository;
import com.xjtu.qgsystem.util.RandomUtil;
import com.xjtu.qgsystem.vo.ScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ContextRepository contextRepository;

    /**
     * 返回所有查询结果的分页
     * @param pageNum 第几页
     */
    public Page<Question> getAllPage(int pageNum) {
        // 定义一页有多少条数据
        int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Question> page = questionRepository.findAll(pageable);
        //获得总页数(这些数据需要分几页)
        System.out.println("查询总页数："+page.getTotalPages());
        //获得总记录数（数据库的总记录数）
        System.out.println("查询总记录数："+page.getTotalElements());
        //得到数据集合列表
        System.out.println("数据集合列表:"+page.getContent());

        return page;
    }

    /**
     * 获得首个未被评分的问题
     * @return Question
     */
    public Question getFirstUnchecked() {
        Question question = questionRepository.findFirstUnchecked();
        return question;
    }

    /**
     *
     * @param id 问题id
     * @param contextId 上下文id
     * @param context 修改的上下文文本
     * @param question 修改的问题
     * @param answer 修改的答案
     * @return 修改后的问题
     */
    public Question updateQuestion(Long id, Long contextId, String context, String question, String answer) {
        Question q = questionRepository.findById(id).get();
        Context c = contextRepository.findById(contextId).get();
        q.setText(question);
        q.setAnswerText(answer);
        /**
         * todo: 此外，还要判断 answerStart 是否改变，再去做修改
         */
        c.setText(context);
        contextRepository.save(c);
        q.setReference(c);
        questionRepository.save(q);

        return q;
    }

    /**
     * 为一个问题打分
     * @param id 问题id
     * @param fluency 流畅性
     * @param reasonable 合理性
     * @param relevance 相关性
     * @return 评分后的问题
     */
    public Question rateQuestion(Long id, int fluency, int reasonable, int relevance) {
        Question q = questionRepository.findById(id).get();
        q.setFluency(fluency * 10);
        q.setReasonable(reasonable * 10) ;
        q.setRelevance(relevance * 10);
        q.setCheckedTimes(q.getCheckedTimes() + 1);
        questionRepository.save(q);

        return q;
    }

    /**
     * 删除问题，实际是给 isDeleted 字段赋 1，实现懒删除
     * @param id 问题id
     * @return 是否删除成功
     */
    public boolean deleteQuestion(Long id) {
        Question q = questionRepository.findById(id).get();
        q.setIsDeleted(1);
        questionRepository.save(q);

        return true;
    }

    /**
     * 基本的 根据id获取问题
     * @param id 问题id
     * @return 问题
     */
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).get();
    }

    /**
     * 生成随机的未被评分的问题
     * @return 随机的未被评分的问题
     */
    public Question getRandomQuestion() {
        long randomId = RandomUtil.getRandomNum(questionRepository.findCountOfUnchecked());
        Question question = questionRepository.findById(randomId).get();
        return question;
    }

    public ScoreVO getScorePicData() {
        int uncheckedNum = questionRepository.findCountOfUnchecked();
        int checkedNum = questionRepository.findCountOfChecked();
        int[] fluency = new int[5];
        int[] reasonable = new int[5];
        int[] relevance = new int[5];

        fluency[0] = questionRepository.findCountOfFluencyOneStar();
        fluency[1] = questionRepository.findCountOfFluencyTwoStar();
        fluency[2] = questionRepository.findCountOfFluencyThreeStar();
        fluency[3] = questionRepository.findCountOfFluencyThirdStar();
        fluency[4] = questionRepository.findCountOfFluencyFifthStar();

        reasonable[0] = questionRepository.findCountOfReasonableOneStar();
        reasonable[1] = questionRepository.findCountOfReasonableTwoStar();
        reasonable[2] = questionRepository.findCountOfReasonableThreeStar();
        reasonable[3] = questionRepository.findCountOfReasonableThirdStar();
        reasonable[4] = questionRepository.findCountOfReasonableFifthStar();

        relevance[0] = questionRepository.findCountOfRelevanceOneStar();
        relevance[1] = questionRepository.findCountOfRelevanceTwoStar();
        relevance[2] = questionRepository.findCountOfRelevanceThreeStar();
        relevance[3] = questionRepository.findCountOfRelevanceThirdStar();
        relevance[4] = questionRepository.findCountOfRelevanceFifthStar();

        return new ScoreVO(uncheckedNum, checkedNum, fluency, reasonable, relevance);
    }
}
