package com.xjtu.qgsystem.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    public static void main(String[] args) {

        HashMap<String, String> headers = new HashMap<>(3);
        String cText="在一个寒冷的冬天，赶集完回家的农夫在路边发现了一条冻僵了的蛇。他很可怜蛇，就把它放在怀里。当他身上的热气把蛇温暖以后，蛇很快苏醒了，露出了残忍的本性，给了农夫致命的伤害——咬了农夫一口。农夫临死之前说：“我竟然救了一条可怜的毒蛇，就应该受到这种报应啊！”";
        String requestUrl = "https://pai.tigerobo.com/x-pai-serving/invoke?appId=17037d0593e44cdca96eb7895fdf77e6&apiKey=e234763dfd6a466fb938f2ef34bd533d&accessToken=0262fe4454c33ea4cc458e076ac4cd5c";
        String jsonStr = "{\"text\":\""+cText+"\"}";
        headers.put("content-type", "application/json");
        // 发送post请求
        String resultData = HttpUtils.sendPostWithJson(requestUrl, jsonStr,headers);
        // 并接收返回结果
        System.out.println("结果：");
        System.out.println(resultData);
    }
    public static JSONArray qAGenertion(String cText){
        HashMap<String, String> headers = new HashMap<>(3);
        String requestUrl = "https://pai.tigerobo.com/x-pai-serving/invoke?appId=17037d0593e44cdca96eb7895fdf77e6&apiKey=e234763dfd6a466fb938f2ef34bd533d&accessToken=0262fe4454c33ea4cc458e076ac4cd5c";
        String jsonStr = "{\"text\":\""+cText+"\"}";
        headers.put("content-type", "application/json");
        // 发送post请求
        String resultData = HttpUtils.sendPostWithJson(requestUrl, jsonStr,headers);
        JSONObject json = JSON.parseObject(resultData);
        String data=json.getString("data");
        JSONObject result = JSON.parseObject(data);
        JSONArray qAList=result.getJSONArray("result");
        if (qAList.isEmpty()){return null;}
        qAList=qAList.getJSONArray(0);
        return qAList;


    }
    public static String sendPostWithJson(String url, String jsonStr, HashMap<String,String> headers) {
        // 返回的结果
        String jsonResult = "";
        try {
            HttpClient client = new HttpClient();
            // 连接超时
            client.getHttpConnectionManager().getParams().setConnectionTimeout(3*1000);
            // 读取数据超时
            client.getHttpConnectionManager().getParams().setSoTimeout(3*60*1000);
            client.getParams().setContentCharset("UTF-8");
            PostMethod postMethod = new PostMethod(url);

            postMethod.setRequestHeader("content-type", headers.get("content-type"));

            // 非空
            if (null != jsonStr && !"".equals(jsonStr)) {
                StringRequestEntity requestEntity = new StringRequestEntity(jsonStr, headers.get("content-type"), "UTF-8");
                postMethod.setRequestEntity(requestEntity);
            }
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                jsonResult = postMethod.getResponseBodyAsString();
            } else {
                throw new RuntimeException("接口连接失败！");
            }
        } catch (Exception e) {
            throw new RuntimeException("接口连接失败！");
        }
        return jsonResult;
    }
}
