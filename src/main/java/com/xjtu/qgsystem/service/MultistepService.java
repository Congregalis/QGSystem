package com.xjtu.qgsystem.service;

import com.spire.presentation.IAutoShape;
import com.spire.presentation.ISlide;
import com.spire.presentation.ParagraphEx;
import com.spire.presentation.Presentation;
import com.xjtu.qgsystem.entity.MultiContext;
import com.xjtu.qgsystem.repository.MultiContextRepository;
import com.xjtu.qgsystem.vo.MultiContextsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultistepService {
    @Autowired
    MultiContextRepository multiContextRepository;
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
}
