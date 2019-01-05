package com.swy.server.controller;

import com.swy.server.config.Config;
import com.swy.server.template.FreeMarkertUtil;
import com.swy.server.utils.HttpUtil;
import com.swy.server.utils.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouxj6112 on 2017/11/12.
 */
@Controller
public class DispatchController {

    @RequestMapping("/")
    public @ResponseBody
    void toIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/home/index");
        } catch (IOException e) {
            Log.error(e);
        }
    }

    @RequestMapping("/index")
    public @ResponseBody
    void index(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> root = new HashMap<String, Object>();
        Map<String, Object> result = FreeMarkertUtil.parseTemplate(Config.freemarkerFilePath, "index.ftl", root);
        HttpUtil.setResponse(response, result);
    }

    @RequestMapping("/home/index")
    public @ResponseBody
    void main(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String htmlString = FreeMarkertUtil.parseFileContent(Config.htmlFilePath, "home.html");
        map.put("content", htmlString);
        HttpUtil.setResponse(response, map);
    }

}
