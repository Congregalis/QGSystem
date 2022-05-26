package com.xjtu.qgsystem.vo;



import java.util.List;
//上下文展示数据模型
public class ContextShowVO {
     Long cId;
     String cText;
     String cLanguage;
     String cTitle;
     String cSource;
     String cSubject;
     List<QuestionVO> qList;

     public String getcSubject() {
          return cSubject;
     }

     public void setcSubject(String cSubject) {
          this.cSubject = cSubject;
     }

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

     public List<QuestionVO> getqList() {
          return qList;
     }

     public void setqList(List<QuestionVO> qList) {
          this.qList = qList;
     }
}
