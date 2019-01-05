package com.swy.server.interceptor;

import com.swy.server.utils.Log;
import javolution.util.FastMap;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Json on 23/6/2016.
 */
public class GlobalInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //鉴权,验证接口请求的合法性
        String uri = request.getRequestURL().toString();
        Log.debug(uri);

        return true;
    }

//	@Override
//	public boolean preHandle ( HttpServletRequest request , HttpServletResponse response , Object handler ) throws Exception
//	{
//		response.setContentType("application/json;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		HandlerMethod handlerMethod = (HandlerMethod) handler;
//		Method method = handlerMethod.getMethod();

    //LOG.info("请求的方法：" + method.getName());
//
//		LoginRequired loginRequired =  method.getAnnotation(LoginRequired.class);
//		DecodeRequest decodeRequest = method.getAnnotation(DecodeRequest.class);
//		JSONObject params = new JSONObject();
//		String contentString = getRequestString(request);
//
//		if ("".equals(contentString)&&loginRequired==null) {
//			// 没有传入任何参数
//			request.setAttribute("params", params);
//			return true;
//		} else {
//			// 根据注解参数解密
//			if (decodeRequest != null) {
//				try {
//					contentString = AESUtil.decrypt(contentString);
//				} catch (Exception e) {
//					writeResponse(JsonUtil.generateResponse(MsgCode.BAD_REQUEST), response);
//				}
//				if (contentString == null){
//					writeResponse(JsonUtil.generateResponse(MsgCode.BAD_REQUEST),response);
//					return  false;
//				}
//			//	LOG.info("解密后参数：" + contentString);
//			}else {
//				//LOG.info("参数内容：" + contentString);
//			}
//		}
//
//		// 已经传入参数，将其转成JSON对象
//		if(contentString != null && contentString.trim().length()>0){
//			params = (JSONObject) JSON.parseObject(contentString);
//		}
//
////		// 根据注解检查登陆
////		if (loginRequired != null) {
////			if (!loginCheck(response,params,loginRequired.strict())){
////				return false;
////			}
////		}
//		request.setAttribute("params", params);
//		return true;
//	}


//	@Override
//	public void postHandle ( HttpServletRequest request , HttpServletResponse response , Object handler , ModelAndView modelAndView ) throws Exception
//	{
//		super.postHandle ( request , response , handler , modelAndView );
//	}

//
//	private boolean loginCheck(HttpServletResponse response, JSONObject params, boolean strict) {
//		String otoken = null;
//		String token = null;
//		boolean hasToken = params.containsKey("authority")&&params.getJSONObject("authority").containsKey("token");
//		if (hasToken) {
//			String requestToken = params.getJSONObject("authority").getString("token");
//			if (requestToken!=null&&!"".equals(requestToken)) {
//				String tmp = null;
//				try {
//					tmp = AESUtil.decrypt(requestToken);
//				} catch (Exception e) {
////					LOG.info(">-----------------requestToken:" + requestToken);
//					writeResponse(JsonUtil.generateResponse(MsgCode.BAD_REQUEST), response);
//					return false;
//				}
//				if(tmp!=null){
//					if (tmp.split("_").length >= 2) {
//						token = requestToken;
//						otoken = tmp;
//					}
//				}else{
//					if (strict) {
//						writeResponse(JsonUtil.generateResponse(MsgCode.TOKEN_IS_ERROR), response);
//						return false;
//					}else{
//						return true;
//					}
//				}
//			}
//		}
//
//		//如果需要强制登录
//		if (strict) {
//			if (token == null) {
//				writeResponse(JsonUtil.generateResponse(MsgCode.TOKEN_IS_ERROR), response);
//				return false;
//			}
//			String[] ss = otoken.split("_");
//			if (ss.length == 2) {
//				String memberId = ss[1];
//				String value = JedisUtil.getFieldValue(RedisKeyConfig.REDIS_MEMBER_TOKEN_KEY + memberId, "token");
//				if (token.equals(value)) {
//					tokenHandler(memberId,params);
//					return true;
//				}else {
//					writeResponse(JsonUtil.generateResponse(MsgCode.TOKEN_IS_ERROR), response);
//					return false;
//				}
//			}
//		}else {
//			if (otoken!=null) {
//				String[] ss = otoken.split("_");
//				if (ss.length == 2) {
//					String memberId = ss[1];
//					tokenHandler(memberId, params);
//				}
//			}
//			return true;
//		}
//		return true;
//	}
//

//	private void tokenHandler(String memberId, JSONObject params){
//		String memberName = JedisUtil.getFieldValue(RedisKeyConfig.REDIS_MEMBER_TOKEN_KEY + memberId, "memberName");
//		LOG.info("根据token解析出的memberId:"+memberId);
//		params.put("memberId", Integer.valueOf(memberId));
//		params.put("memberName", memberName);
//	}
//

    private void writeResponse(String content, HttpServletResponse response) {
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            Log.info(content);
            pw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            Log.error(e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    private String getRequestString(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            br = request.getReader();
            String tmp;
            StringBuilder sb = new StringBuilder();
            while ((tmp = br.readLine()) != null) {
                sb.append(tmp);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            Log.error(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.error(e);
                }
            }
        }
        return null;
    }

}