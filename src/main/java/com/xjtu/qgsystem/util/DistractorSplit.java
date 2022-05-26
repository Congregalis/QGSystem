package com.xjtu.qgsystem.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//将美元符号分割的干扰项字符串分割成字符串列表
public class DistractorSplit {
    public static String[] split(String distractor){
        return distractor.split("\\$");
    }

    public static void main(String[] args) {
        String s="无主$私有地$地权";
        System.out.println(Arrays.toString(split(s)));
    }
}

