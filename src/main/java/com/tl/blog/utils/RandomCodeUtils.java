package com.tl.blog.utils;

import java.util.Random;

/**
 * @author tl
 */
public class RandomCodeUtils {
    /**
     * 获取四位验证码
     * @return
     */
    public static String get4BitRandom(){
        return String.valueOf((new Random().nextDouble())).substring(2,6);
    }

    /**
     * 获取六位验证码
     * @return
     */
    public static String get6BitRandom(){
        return String.valueOf((new Random().nextDouble())).substring(2,8);
    }
}
