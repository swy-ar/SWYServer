package com.swy.server.utils;

import javolution.util.FastMap;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouxj6112 on 2017/11/12.
 */
public final class HttpUtil {

    // 禁止实例化和继承
    private HttpUtil() {

    }

    // 直接页面输出字符串
    public static void setResponse(HttpServletResponse response, Map<String , Object> result)
    {
        // 允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");

        String contentType	= "text/html; charset=utf-8";
        if (result.containsKey ( "contentType" ))
        {
            if ( ( ( String ) result.get ( "contentType" ) ).toLowerCase ( ).equals ( "json" ) ) {
                contentType = "application/json;charset=utf-8";
            }
        }
        response.setContentType ( contentType );

        java.io.Writer out;
        try {
            out = response.getWriter ( );
            out.write ( (String)result.get ( "content" ) );
            out.flush ( );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    // 直接输出接口数据
    public static void setApiResponse(HttpServletResponse response, Map<String, Object> result)
    {
        // 允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");

        String contentType = "text/html; charset=utf-8";
        if (result.containsKey ( "contentType" ))
        {
            if ( ( ( String ) result.get ( "contentType" ) ).toLowerCase ( ).equals ( "json" ) ) {
                contentType = "application/json;charset=utf-8";
            }
        }
        response.setContentType ( contentType );

        java.io.Writer out;
        try {
//            JSONObject jsonObject = JSONObject.fromObject(result);
//            String content = jsonObject.toString();
            String content = TimestampUtil.beanToJson(result, "yyyy-MM-dd HH:mm:ss");
            //
            out = response.getWriter ( );
            out.write ( content );
            out.flush ( );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    // 从request里面获取参数
    public static Map<String, String> convertToLowerParams(HttpServletRequest request) {
        Map<String, String> params = FastMap.newInstance();

        for (String key : request.getParameterMap().keySet()) {
            String[] values = request.getParameterValues(key);
            if (null != values && values.length >= 1) {
                params.put(key.toLowerCase(), values[0]);
            } else {
                params.put(key.toLowerCase(), "");
            }
        }
        return params;
    }

}
