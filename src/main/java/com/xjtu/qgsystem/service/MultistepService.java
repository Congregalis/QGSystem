package com.xjtu.qgsystem.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spire.presentation.IAutoShape;
import com.spire.presentation.ISlide;
import com.spire.presentation.ParagraphEx;
import com.spire.presentation.Presentation;
import com.xjtu.qgsystem.entity.MultiContext;
import com.xjtu.qgsystem.entity.MultiQuestion;
import com.xjtu.qgsystem.repository.MultiContextRepository;
import com.xjtu.qgsystem.repository.MultiQuestionRepository;
import com.xjtu.qgsystem.util.HttpUtils;
import com.xjtu.qgsystem.vo.GetMultiCQVO;
import com.xjtu.qgsystem.vo.MultiCandQ;
import com.xjtu.qgsystem.vo.MultiContextsVO;
import com.xjtu.qgsystem.vo.MultiQuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MultistepService {
    @Autowired
    MultiContextRepository multiContextRepository;
    @Autowired
    MultiQuestionRepository multiQuestionRepository;
    private static final String basePath ="tempFile";
    //将上传的ppt中的句子抽取出来写入数据库
    public boolean getContextFromPPT(String filename, boolean res) throws Exception{
        if (res==false){
            return res;
        }
        //创建Presentation实例
        Presentation ppt = new Presentation();
        filename="tempFile\\"+filename;
        //加载PowerPoint文档
        ppt.loadFromFile(filename);

        StringBuilder buffer = new StringBuilder();
        multiQuestionRepository.deleteAll();
        multiContextRepository.deleteAll();
        //遍历文档中的幻灯片，提取文本
        for (Object slide : ppt.getSlides()) {
            for (Object shape : ((ISlide) slide).getShapes()) {
                if (shape instanceof IAutoShape) {
                    for (Object tp : ((IAutoShape) shape).getTextFrame().getParagraphs()) {
                        String text=((ParagraphEx) tp).getText();
                        text=text.trim();
                        if (text.length()>20){
                            MultiContext multiContext=new MultiContext();
                            multiContext.setcText(text);
                            multiContextRepository.save(multiContext);
                        }
                    }
                }
            }
        }
        return true;
    }

    //分页查询句子
    public MultiContextsVO queryContext(Integer pageNum, Integer pageLimit) {
        MultiContextsVO multiContextVO=new MultiContextsVO();
        multiContextVO.setContextList(multiContextRepository.findAllContext(pageNum,pageLimit));
        multiContextVO.setTotal(multiContextRepository.findAll().size());
        return multiContextVO;
    }



    public boolean deleteContext(long parseLong) {
        multiContextRepository.deleteById(parseLong);
        return true;
    }

    public boolean updateContext(long parseLong, String cText) {

        multiContextRepository.updateContext(parseLong,cText);
        return true;
    }

    public boolean generateQuestion(Integer algorithm) {
        List<MultiContext> contexts = multiContextRepository.findAll();
        for (MultiContext context:contexts
             ) {
            Long cId=context.getcId();
            String cText=context.getcText();
            try{
                JSONArray qAList=HttpUtils.qAGenertion(cText);
                if (qAList==null)continue;
                for (int i = 0; i < qAList.size(); i++) {
                    String qaJson=qAList.getString(i);
                    JSONObject qa = JSON.parseObject(qaJson);
                    String question=qa.getString("question");
                    String answer=qa.getString("answer");
                    JSONObject aJson = JSON.parseObject(answer);
                    answer=aJson.getString("text");
                    MultiQuestion multiQuestion=new MultiQuestion();
                    multiQuestion.setqText(question);
                    multiQuestion.setqAnswer(answer);
                    multiQuestion.setReference(context);

                    multiQuestionRepository.save(multiQuestion);

                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                continue;
            }

        }
        return true;
    }

    public GetMultiCQVO queryQuestions(int pageNum, Integer pageLimit) {
        List<MultiContext> multiContexts=multiContextRepository.findAllContext(pageNum,pageLimit);
        GetMultiCQVO getMultiCQVO=new GetMultiCQVO();
        getMultiCQVO.setTotal(multiContextRepository.findAll().size());
        List<MultiCandQ> list=new ArrayList<>();
        for (MultiContext m:multiContexts
             ) {
            MultiCandQ multiCandQ=new MultiCandQ();
            multiCandQ.setcId(Long.toString(m.getcId()));
            multiCandQ.setcText(m.getcText());
            List<MultiQuestion> multiQuestions=multiQuestionRepository.findByCid(m.getCid());
            List<MultiQuestionVO> questionVOList=new ArrayList<>();
            for (MultiQuestion q:multiQuestions
                 ) {
                MultiQuestionVO questionVO=new MultiQuestionVO(q);
                questionVOList.add(questionVO);
            }
            multiCandQ.setqList(questionVOList);
            list.add(multiCandQ);
        }
        getMultiCQVO.setDataList(list);
        return getMultiCQVO;
    }

    public boolean deleteQuestion(long qId) {
        multiQuestionRepository.deleteById(qId);
        return true;
    }

    public boolean updateQuestion(long qId, String qText, String qAnswer, boolean qIsChecked, Integer qFluency, Integer qRelevance, Integer qReasonability, Integer qDifficulty) {
        MultiQuestion m=  multiQuestionRepository.findByQid(qId);
        m.setqText(qText);
        m.setqAnswer(qAnswer);
        m.setqDifficulty(qDifficulty);
        m.setqRelevance(qRelevance);
        m.setqFluency(qFluency);
        m.setqIsChecked(qIsChecked);
        m.setqReasonability(qReasonability);
        multiQuestionRepository.save(m);
        return true;
    }
}
