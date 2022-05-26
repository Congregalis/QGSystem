package com.xjtu.qgsystem.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//将美元符号分割的干扰项字符串分割成字符串列表
public class DistractorSplit {
    public static String[] split(String distractor){
        List<String> distractors=new ArrayList<>();
        String[] str1Array = distractor.split("$");
        for (String s:str1Array
             ) {
            distractors.add(s);
        }
        return distractors.toArray(new String[distractors.size()]);
    }
}
