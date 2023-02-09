package com.yuzi.server.utils;

import java.util.Random;

/**
 * 字符串工具类
 *
 * @author 星涯
 */
public class StringUtil {
    public static String createRandomString() {
        return getRandomString(12) + System.currentTimeMillis() + getRandomString(13);
    }

    public static String getRandomString(int length) {
        String base = "0123456789qazwsxedcrfvtgbyhnujmikolpQAZWSXEDCRFVTGBYHNUJMIKOLP";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
