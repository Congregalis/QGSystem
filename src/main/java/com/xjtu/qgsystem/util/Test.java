package com.xjtu.qgsystem.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
    public static void main(String[] args) {
        String jsonString="{\"code\":200,\"msg\":\"操作成功\",\"data\":{\"status\":0,\"msg\":null,\"result\":[[{\"question\":\"在寒冷的冬天,农夫在哪里发现了一条可怜的蛇?\",\"answer\":{\"score\":0.9894602298736572,\"start\":18,\"end\":20,\"text\":\"路边\"}},{\"question\":\"农夫是如何看待蛇的?\",\"answer\":{\"score\":0.04896458610892296,\"start\":33,\"end\":127,\"text\":\"可怜蛇，就把它放在怀里。当他身上的热气把蛇温暖以后，蛇很快苏醒了，露出了残忍的本性，给了农夫致命的伤害——咬了农夫一口。农夫临死之前说：“我竟然救了一条可怜的毒蛇，就应该受到这种报应啊！”\"}},{\"question\":\"当农夫遇到蛇时,他做了什么?\",\"answer\":{\"score\":0.21051561832427979,\"start\":38,\"end\":44,\"text\":\"把它放在怀里\"}}]],\"appendInfo\":null,\"reqId\":1229164469061222}}\n";
        JSONObject json = JSON.parseObject(jsonString);
        System.out.println(json);
        String data=json.getString("data");
        JSONObject result = JSON.parseObject(data);
        JSONArray qAList=result.getJSONArray("result");
        qAList=qAList.getJSONArray(0);
        for (int i = 0; i < qAList.size(); i++) {
            String qaJson=qAList.getString(i);
            JSONObject qa = JSON.parseObject(qaJson);
            String question=qa.getString("question");
            String answer=qa.getString("answer");
            JSONObject aJson = JSON.parseObject(answer);
            answer=aJson.getString("text");
            System.out.println("question:"+question);
            System.out.println("answer:"+answer);
        }
    }



}
