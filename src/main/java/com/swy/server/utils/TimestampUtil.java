package com.swy.server.utils;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Created by ZhouXiaoJin on 2018/2/13.
 */
public class TimestampUtil {

    /**
     * 将Bean中的Timestamp、Date转换为json中的日期字符串
     * @author zhangyi
     *
     */
    public static class DateJsonValueProcessor implements JsonValueProcessor {
        public static final String Default_DATE_PATTERN ="yyyy-MM-dd";
        private DateFormat dateFormat ;
        public DateJsonValueProcessor(String datePattern){
            try{
                dateFormat  = new SimpleDateFormat(datePattern);}
            catch(Exception e ){
                dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);
            }
        }
        public Object processArrayValue(Object value, JsonConfig jsonConfig) {
            return process(value);
        }
        public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {
            return process(value);
        }
        private Object process(Object value){
            return dateFormat.format((Date)value);
        }
    }

    /**
     * 将json串中的日期字符串转换为bean中的Timestamp、Date
     *
     * @author zhangyi
     *
     */
    public static class TimestampMorpher extends AbstractObjectMorpher {
        /*** 日期字符串格式 */
        private String[] formats;

        public TimestampMorpher(String[] formats) {
            this.formats = formats;
        }

        public Object morph(Object value) {
            if (value == null) {
                return null;
            }
            if (Timestamp.class.isAssignableFrom(value.getClass())) {
                return (Timestamp) value;
            }
            if (!supports(value.getClass())) {
                throw new MorphException(value.getClass() + " 是不支持的类型");
            }
            String strValue = (String) value;
            SimpleDateFormat dateParser = null;
            for (int i = 0; i < formats.length; i++) {
                if (null == dateParser) {
                    dateParser = new SimpleDateFormat(formats[i]);
                } else {
                    dateParser.applyPattern(formats[i]);
                }
                try {
                    return new Timestamp(dateParser.parse(strValue.toLowerCase()).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        public Class morphsTo() {
            return Timestamp.class;
        }

        public boolean supports(Class clazz) {
            return String.class.isAssignableFrom(clazz);
        }

    }

    public static Object jsonToBean(String json, Class cls) {
        String[] formats = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };
        JSONUtils.getMorpherRegistry().registerMorpher(new TimestampUtil.TimestampMorpher(formats));
        JSONObject jsonObject = JSONObject.fromObject(json);
        return JSONObject.toBean(jsonObject, cls);
    }

    public static String beanToJson(Object obj, String dateFormat) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Timestamp.class, new TimestampUtil.DateJsonValueProcessor(dateFormat));
        JSONObject json = JSONObject.fromObject(obj, config);
        return json.toString();
    }

}
