package com.xjtu.qgsystem.vo;



import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
//上下文展示数据模型
public class ContextShowVO {
     @JsonIgnore
     Long id;
     String cId;
     String cText;
     String cLanguage;
     String cTitle;
     String cSource;
     String cSubject;
     @JsonIgnore
     Long total;//数据库中总的上下文数量
     List<QuestionVO> qList;

     public String getcId() {
          return cId;
     }

     public void setcId(String cId) {
          this.cId = cId;
     }

     public Long getTotal() {
          return total;
     }

     public void setTotal(Long total) {
          this.total = total;
     }

     public String getcSubject() {
          return cSubject;
     }

     public void setcSubject(String cSubject) {
          this.cSubject = cSubject;
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
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

     @Override
     public String toString() {
          return "ContextShowVO{" +
                  "id=" + id +
                  ", cId='" + cId + '\'' +
                  ", cText='" + cText + '\'' +
                  ", cLanguage='" + cLanguage + '\'' +
                  ", cTitle='" + cTitle + '\'' +
                  ", cSource='" + cSource + '\'' +
                  ", cSubject='" + cSubject + '\'' +
                  ", total=" + total +
                  ", qList=" + qList +
                  '}';
     }
}
