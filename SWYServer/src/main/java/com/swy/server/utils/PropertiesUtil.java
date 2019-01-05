package com.swy.server.utils;

import java.util.ResourceBundle;

/**
 * 读取properties文件的工具类
 *
 * @author 何霖
 * @ClassName: PropertiesUtil
 * @Description: TODO
 * @date 2016年1月14日 下午3:01:10
 */
public final class PropertiesUtil {

    private PropertiesUtil() {

    }

   public static String getStringConfig(String key, ResourceBundle data) {
        String value;
        if (System.getenv().containsKey(key)) {
            value = System.getenv(key);
        } else {
            value = data.getString(key);
        }
        return value;
    }

    public static int getIntConfig(String key, ResourceBundle data) {
        int value;
        try {
            String s;
            if (System.getenv().containsKey(key)) {
                s = System.getenv(key);
            } else {
                s = data.getString(key);
            }
            value = Integer.parseInt(s);
        } catch (Exception e) {
            throw new NumberFormatException();
        }
        return value;
    }

}
