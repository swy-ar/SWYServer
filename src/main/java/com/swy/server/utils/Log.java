package com.swy.server.utils;

import com.swy.server.utils.PropertiesUtil;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.PropertyResourceBundle;

/**
 * 为避免写日志成为瓶颈，因而将日志分成多个文件来写
 *
 * @author zhiquan.yuan
 */
public class Log {
    public static boolean systemRunning = false;


    //取得日志目录路径
    public static String LOG_PATH = "";
    public static int INFO_LOG_NUM = 10;

    static {

        try {
            LOG_PATH = PropertiesUtil.getStringConfig("SWY_SERVER_LOG_PATH", new PropertyResourceBundle(new BufferedInputStream(new FileInputStream(new File(Thread.currentThread()
                    .getContextClassLoader().getResource("log.properties").getPath())))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (LOG_PATH.equals("")) {
            LOG_PATH = System.getProperty("vipmePushServerRoot");
        }
    }

    //事件日志分多个文件写
    private static Logger infoWriter = null;
    private static Logger errorWriter = null;
    private static Logger debugWriter = null;

    //初始化日志文件
    static {
        try {
            infoWriter = createLogger("vipme_push_server_event_", Level.INFO);
            errorWriter = createLogger("vipme_push_server_error_", Level.ERROR);
            debugWriter = createLogger("vipme_push_server_debug_", Level.DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Logger createLogger(String name, Level level) throws IOException {
        name = name + ".log"; // log日志文件必须以.log为后缀
        Logger logger = Logger.getLogger(name);
        logger.removeAllAppenders();
        logger.setAdditivity(false); // 设置继承输出root
        Appender appender = null;
        PatternLayout layout = new PatternLayout();
//		layout.setConversionPattern ( "[%p]%d{yyyy-MM-dd HH:mm:ss,SSS} [%c]-[%M line:%L]%n %m%n" );
        layout.setConversionPattern("%d - %m%n ");

        appender = new org.apache.log4j.DailyRollingFileAppender(layout, LOG_PATH + "/" + name, "yyyyMMdd");
        logger.addAppender(appender);
        logger.setLevel(level);
        return logger;
    }

    public static void info(String logStr) {
        if (infoWriter == null) {
            return;
        }
        infoWriter.info(logStr);
    }

    public static void debug(String logStr) {
        if (debugWriter == null) {
            return;
        }
        debugWriter.debug(logStr);
    }

    public static void error(String logStr) {
        if (errorWriter == null) {
            return;
        }
        errorWriter.error(logStr);
    }

    public static void error(Exception ex) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            errorWriter.error(sw.toString());
            sw.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void error(String logStr, Exception ex) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            errorWriter.error(logStr + ":" + sw.toString());
            sw.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String getCommonParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

            sb.append("md=" + request.getMethod() + "`");
            if (request.getHeader("vipme_apps") == null) {
                sb.append("pf=website`");
            } else {
                sb.append("pf=app`");
            }
//			sb.append ( "uid=" + HttpUtil.getUserId(request , response)+ "`" );
            sb.append("ip=" + getIpAddress(request) + "`");
            sb.append("ua=" + request.getHeader("user-agent") + "`");

        } catch (Exception e) {
            error(e);
        }
        return sb.toString();
    }

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getIpAddress(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getRemoteAddr();
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

}
