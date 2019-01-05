package com.swy.server.config;

/**
 * Created by zhouxj6112 on 2017/11/12.
 */
public final class Config {

    public static final String freemarkerFilePath = System.getProperty ("appRootPath", "/usr/share/tomcat7/apps/sanweiyun-server/").replace ("\\" , "/") + "WEB-INF/templates";

    public static final String htmlFilePath = System.getProperty ("appRootPath", "/usr/share/tomcat7/apps/sanweiyun-server/").replace ("\\" , "/") + "WEB-INF/pages";

}
