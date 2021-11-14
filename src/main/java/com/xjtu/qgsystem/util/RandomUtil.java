package com.xjtu.qgsystem.util;

import java.util.Random;

public class RandomUtil {
    public static int getRandomNum(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }
}
