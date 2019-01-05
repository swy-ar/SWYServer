package com.swy.server.utils;

/**
 * Created by ZhouXiaoJin on 16/8/22.
 */
public class StringUtil {

    public final static String LANGUAGE_ENGLIST = "EN";
    public final static String LANGUAGE_SPAIN = "ES";
    public final static String LANGUAGE_RUSSIA = "RU";
    public final static String LANGUAGE_KOREA = "KO";

    public final static String COUNTRY_US = "US";
    public final static String COUNTRY_SPAIN = "ES";
    public final static String COUNTRY_RUSSIA = "RU";
    public final static String COUNTRY_KOREA = "KR";

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

    public static String getUserCountry(String languageType) {
        String country = COUNTRY_US;
        if (LANGUAGE_SPAIN.equalsIgnoreCase(languageType)){
            country = COUNTRY_SPAIN;
        } else if (LANGUAGE_RUSSIA.equalsIgnoreCase(languageType)){
            country = COUNTRY_RUSSIA;
        } else if (LANGUAGE_KOREA.equalsIgnoreCase(languageType)){
            country = COUNTRY_KOREA;
        }

        return country;
    }
}
