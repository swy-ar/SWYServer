package com.swy.server.controller;

import com.swy.server.utils.Log;
import com.swy.server.utils.PropertiesUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;

/**
 * Created by zhouxj6112 on 2018/1/8.
 */
public class GlobalEnv {

    public static String nginxDocPath;
    public static String nginxFilePath;
    public static String nginxMoviePath;
    public static String serverBaseUrl;
    public static String serverFileUrl;
    public static String serverMovieUrl;

    static
    {
        try {
            File file = new File(Thread.currentThread().getContextClassLoader().getResource("log.properties").getPath());
            FileInputStream inputStream = new FileInputStream(file);
            PropertyResourceBundle resourceBundle = new PropertyResourceBundle(new BufferedInputStream(inputStream));
            String filePath = PropertiesUtil.getStringConfig("SWY_FILE_PATH", resourceBundle);
            String moviePath = PropertiesUtil.getStringConfig("SWY_MOVIE_PATH", resourceBundle);
            //
            nginxDocPath = PropertiesUtil.getStringConfig("SWY_NGINX_DOC_PATH", resourceBundle);
            nginxFilePath = nginxDocPath + filePath;
            nginxMoviePath = nginxDocPath + moviePath;

            serverBaseUrl = PropertiesUtil.getStringConfig("SWY_SERVER_BASE_URL", resourceBundle);
            serverFileUrl = serverBaseUrl + filePath;
            serverMovieUrl = serverBaseUrl + moviePath;

        } catch (IOException e) {
            e.printStackTrace();
            //
            Log.error(e);
        }
    }

}
