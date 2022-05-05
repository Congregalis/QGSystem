package com.xjtu.qgsystem.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;


public class JsonAnalysis {
    public static void main(String[] args) throws SQLException {
        String json = "null";
        try {
            json = readJsonData("Chinese.json");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        jSONStrToJSONArray(json);
    }


    //json字符串-数组类型
    //[
    //    {
    //        "context": "你想说，要有玉一样的品质，铁一般的意志，要有一颗历经沧桑、忠贞不渝的心！呵，卵石，你是大海中的一滴泪；你是岩石的一颗心；你是启迪人生的一首诗；你是激励生命的一章交响乐！",
    //        "questions": [
    //            {
    //                "question": "卵石是什么颜色的心?",
    //                "answer": "",
    //                "distractor": ""
    //            },
    //            {
    //                "question": "卵石是啥颜色的?",
    //                "answer": "",
    //                "distractor": ""
    //            },
    //            {
    //                "question": "你想说,有玉一样的品质,有铁一样的品质,还有什么品质?",
    //                "answer": "",
    //                "distractor": ""
    //            }
    //        ],
    //        "subject": "语文",
    //        "language": "中文",
    //        "source": "generated"
    //    }
    //]
    /**
     * json字符串-数组类型与JSONArray之间的转换
     */
    public static void jSONStrToJSONArray(String json) throws SQLException {
        Connection conn=NewJdbcUtil.getConnection();
        JSONArray jsonArray = JSON.parseArray(json);
        //遍历方式1
        int size = jsonArray.size();
        for (int i = 0; i < size; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String context=jsonObject.getString("context");
            String subject=jsonObject.getString("subject");
            String language=jsonObject.getString("language");
            String source=jsonObject.getString("source");
            Long id=NewJdbcUtil.insertContext(conn,context,language,subject);
            JSONArray quesArray=jsonObject.getJSONArray("questions");
            for (int j = 0; j < quesArray.size(); j++) {
                JSONObject qa = quesArray.getJSONObject(j);
                String question=qa.getString("question");
                String answer=qa.getString("answer");
                String distractor=qa.getString("distractor");
                NewJdbcUtil.insertQuestion(conn,question,answer,id,distractor);
            }
        }
    }


    //将json保存为字符串
    public static String readJsonData(String pactFile) throws IOException {
        // 读取文件数据
        //System.out.println("读取文件数据util");

        StringBuffer strbuffer = new StringBuffer();
        File myFile = new File(pactFile);
        if (!myFile.exists()) {
            System.err.println("Can't Find " + pactFile);
        }
        try {
            FileInputStream fis = new FileInputStream(pactFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader in  = new BufferedReader(inputStreamReader);

            String str;
            while ((str = in.readLine()) != null) {
                strbuffer.append(str);  //new String(str,"UTF-8")
            }
            in.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        //System.out.println("读取文件结束");
        return strbuffer.toString();
    }

}
