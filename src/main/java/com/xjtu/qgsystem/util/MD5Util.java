package com.xjtu.qgsystem.util;

import org.springframework.util.DigestUtils;

public class MD5Util {
    //md5加密函数
    public static String md5(String s){
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }
    // 测试主函数

    public static void main(String args) {
        String s = "zzm";
        System.out.println("原始：" + s);
        System.out.println("MD5后：" +md5(s) );//923047ae3f8d11d8b19aeb9f3d1bc002
    }
}
