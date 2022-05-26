package com.xjtu.qgsystem.service;

import com.xjtu.qgsystem.entity.Context;
import com.xjtu.qgsystem.entity.Question;
import com.xjtu.qgsystem.entity.Type;
import com.xjtu.qgsystem.repository.ContextRepository;
import com.xjtu.qgsystem.repository.QuestionRepository;
import com.xjtu.qgsystem.repository.TypeRepository;
import com.xjtu.qgsystem.repository.redis.RedisStatisticRepository;
import com.xjtu.qgsystem.util.RandomUtil;
import com.xjtu.qgsystem.util.TokenUtil;
import com.xjtu.qgsystem.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    ContextRepository contextRepository;

    @Autowired
    RedisStatisticRepository redisStatisticRepository;

    // 定义分页结果一页有多少数据
    private final int pageSize = 10;

    /**
     * 返回所有查询结果的分页
     * @param pageNum 第几页
     */
    public Page<QuestionVO> getAllPage(int pageNum) {

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Question> page = questionRepository.findAll(pageable);
        return listConvertToPage(page, pageable);
    }

    /**
     * 根据用户 token 获取用户评估过的所有问题
     * @param pageNum 第几页
     * @param token 用户的token
     * @return 分页结果
     */
    public Page<QuestionVO> getCheckedPageByToken(int pageNum, String token) {
        long userId = TokenUtil.getInstance().getUserIdFromToken(token);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Question> page = questionRepository.findAllByCheckedTimesAndUserId(1, userId, pageable);
        return listConvertToPage(page, pageable);
    }

//   QuestionVO类型的列表
//    转换为 Page类型
    public Page<QuestionVO> listConvertToPage(Page<Question> page, Pageable pageable) {
        List<QuestionVO> list = new ArrayList<>();
        for(Question question : page) {
            list.add(questionToQuestionVo(question));
        }
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }


    /**
     * 获得首个未被评分的问题
     * @return Question
     */
    public QuestionVO getFirstUnchecked() {
        Question question = questionRepository.findFirstUnchecked();
        return questionToQuestionVo(question);
    }

    /**
     *
     * @param id 问题id
     * @param contextId 上下文id
     * @param context 修改的上下文文本
     * @param question 修改的问题
     * @param answer 修改的答案
     * @param questionType 修改的问题类型
     * @param cognitiveType 修改的认知类型
     * @param distractorsArray 修改的干扰项
     * @param whType 修改的wh类型
     * @return 修改后的问题
     */
    public QuestionVO updateQuestion(Long id, Long contextId, String context, String question, String answer, String questionType, String cognitiveType, String[] distractorsArray, String whType) {
        Question q = questionRepository.findById(id).get();
        Context c = contextRepository.findById(contextId).get();
        q.setText(question);
        q.setAnswerText(answer);
        q.setQuestionType(questionType);
        q.setCognitiveType(cognitiveType);
        q.setDistractors(distractorsArrayToString(distractorsArray));
        q.setWhType(whType);
        /**
         * todo: 此外，还要判断 answerStart 是否改变，再去做修改
         */
        c.setText(context);
        contextRepository.save(c);
        q.setReference(c);
        questionRepository.save(q);

        return questionToQuestionVo(q);
    }

    /**
     * 为一个问题打分
     * @param id 问题id
     * @param fluency 流畅性
     * @param reasonable 合理性
     * @param relevance 相关性
     * @return 评分后的问题
     */
    public QuestionVO rateQuestion(Long id, int fluency, int reasonable, int relevance, int difficulty, String token) {
        Question q = questionRepository.findById(id).get();
        q.setFluency(fluency * 10);
        q.setReasonable(reasonable * 10) ;
        q.setRelevance(relevance * 10);
        q.setDifficulty(difficulty);
        q.setCheckedTimes(q.getCheckedTimes() + 1);
        q.setUserId(TokenUtil.getInstance().getUserIdFromToken(token));
        questionRepository.save(q);

        return questionToQuestionVo(q);
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
    public QuestionVO getQuestionById(Long id) {
        Question question = questionRepository.findById(id).get();
        return questionToQuestionVo(question);
    }

    /**
     * 生成随机的未被评分的问题
     * @return 随机的未被评分的问题
     */
    public QuestionVO getRandomQuestion() {
        long randomId = RandomUtil.getRandomNum(questionRepository.findCountOfUnchecked());
        Optional<Question> questionOptional = questionRepository.findQuestionRandomly();

        return questionToQuestionVo(questionOptional.orElse(null));

    }

    /**
     * 获取根据 title 划分的问题分布
     * @return 分布 list，分布由 title 和 count 构成
     */
    public List<QuestionDistributionVO> getDistributionByTitle() {
        List<QuestionDistributionVO> res = new ArrayList<>();
        List<Map<String, Object>> distribution = redisStatisticRepository.getDistributionByTitle();
        if (distribution == null) {
            redisStatisticRepository.saveDistributionByTitle(questionRepository.getDistributionByTitle());
            distribution = redisStatisticRepository.getDistributionByTitle();
        }


        for (Map<String, Object> objectMap : distribution) {
            res.add(new QuestionDistributionVO((String) objectMap.get("title"), ((BigInteger) objectMap.get("COUNT(*)")).intValue()));
        }
        return res;
    }

    /**
     * 获取根据 difficulty 划分的问题分布
     * difficulty 包括 0 - 简单, 1 - 中等, 2 - 困难
     * @return 分布 list，分布由 difficulty 和 count 构成
     */
    public List<QuestionDistributionVO> getDifficultyDistribution() {
        List<QuestionDistributionVO> res = new ArrayList<>();
        List<Map<String, Object>> distribution = redisStatisticRepository.getDistributionByDifficulty();
        if (distribution == null) {
            redisStatisticRepository.saveDistributionByDifficulty(questionRepository.getDistributionByDifficulty());
            distribution = redisStatisticRepository.getDistributionByDifficulty();
        }

        for (Map<String, Object> objectMap : distribution) {
            res.add(new QuestionDistributionVO(String.valueOf(objectMap.get("difficulty")), ((BigInteger) objectMap.get("COUNT(*)")).intValue()));
        }

        return res;
    }

    /**
     * 获取问题的分数分布
     * @return 分数分布
     */
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

    /**
     * 定时任务 每天执行一次 缓解压力
     * 计算问题类型的的分布并存在 type 表中
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void calTypeDistribution() {
        Map<String, Integer> typeMap = new HashMap<String, Integer>() {
            {
                put("what", 0);
                put("when", 0);
                put("where", 0);
                put("which", 0);
                put("who", 0);
                put("how", 0);
                put("why", 0);
                put("others", 0);
            }
        };
        System.out.println("定时任务开始执行...");
        List<String> questions = questionRepository.findAllName();

        for (String q : questions) {
            boolean put = false;
            for (String s : typeMap.keySet()) {
                if (q.contains(s)) {
                    typeMap.put(s, typeMap.get(s) + 1);
                    put = true;
                }
            }
            if (!put) {
                typeMap.put("others", typeMap.get("others") + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : typeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            Optional<Type> byName = typeRepository.findByName(entry.getKey());
            Type type = byName.orElseGet(Type::new);

            type.setName(entry.getKey());
            type.setCount(entry.getValue());
            typeRepository.save(type);
        }
    }

    public List<QuestionDistributionVO> getTypeDistribution() {
        List<QuestionDistributionVO> res = new ArrayList<>();
        List<Type> all = typeRepository.findAll();

        for (Type type : all) {
            res.add(new QuestionDistributionVO(type.getName(), type.getCount()));
        }

        return res;
    }

    /*
    * 将Question对象转换为QuestionVO对象返回给前端
    * */
    public QuestionVO questionToQuestionVo(Question question) {
        QuestionVO questionVO=new QuestionVO(question);
        if (questionVO.getDistractors()!=null){
            questionVO.setDistractorsArray(questionVO.getDistractors().split("$"));
        }
        System.out.println(questionVO);
        return questionVO;
    }
    /*
     * array转String
     * */
    public String distractorsArrayToString(String[] distractorsArray) {
        String tempDistractors = "";
        for(String str : distractorsArray) {
            tempDistractors += str + "$";
        }
        return tempDistractors;
    }

    public List<CandQVO> findByCondition(ConditionVO findByConditionVO) {
        List<CandQVO>list =new ArrayList<>();
        Integer start=(findByConditionVO.getPage()-1)* findByConditionVO.getPagesize();
        List<Context> contexts=contextRepository.findByCondition(start,findByConditionVO.getPagesize(),findByConditionVO.getcLanguage(),findByConditionVO.getcTitle(),findByConditionVO.getcSubject());
        for (Context context:contexts
             ) {
            List<Question>questions = questionRepository.findByCId(context.getId());

            List<QuestionVO>questionVOList=new ArrayList<>();
            for (Question q:questions
                 ) {
                questionVOList.add(questionToQuestionVo(q));
            }
            CandQVO candQVO=new CandQVO();
            candQVO.setQuestionVOS(questionVOList);
            candQVO.setcTitle(context.getTitle());
            candQVO.setcLanguage(context.getLanguage());
            candQVO.setcId(context.getId());
            candQVO.setcText(context.getText());
            list.add(candQVO);
        }
        return list;
    }
}

