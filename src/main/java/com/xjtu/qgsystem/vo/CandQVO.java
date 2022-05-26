package com.xjtu.qgsystem.vo;



import java.util.List;

public class CandQVO {
     Long cId;
     String cText;
     String cLanguage;
     String cTitle;
     String cSource;
     List<QuestionVO> questionVOS;

     public Long getcId() {
          return cId;
     }

     public void setcId(Long cId) {
          this.cId = cId;
     }

     public String getcText() {
          return cText;
     }

     public void setcText(String cText) {
          this.cText = cText;
     }

     public String getcLanguage() {
          return cLanguage;
     }

     public void setcLanguage(String cLanguage) {
          this.cLanguage = cLanguage;
     }

     public String getcTitle() {
          return cTitle;
     }

     public void setcTitle(String cTitle) {
          this.cTitle = cTitle;
     }

     public String getcSource() {
          return cSource;
     }

     public void setcSource(String cSource) {
          this.cSource = cSource;
     }

     public List<QuestionVO> getQuestionVOS() {
          return questionVOS;
     }

     public void setQuestionVOS(List<QuestionVO> questionVOS) {
          this.questionVOS = questionVOS;
     }
}
