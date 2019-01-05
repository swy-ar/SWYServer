package com.swy.server.controller;

import com.swy.server.utils.Log;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zhouxj6112 on 2017/11/22.
 */
public class ProcessHandler {
    private OutputStream os;

    ProcessHandler(HttpServletResponse response) {
        try {
            os = response.getOutputStream();
        } catch (IOException e) {
            Log.error(e);
        }
    }

    public void showProcessInfo(String content, String clientMethod) {
        if (null != os) {
            try {
                final StringBuilder sb = new StringBuilder("<script type=\"text/javascript\">//<![CDATA[\n")
                        .append("   parent.").append(clientMethod).append("(\"").append(content).append("\");\n")
                        .append("//}}></script>");
                os.write(sb.toString().getBytes("UTF-8"));
                os.flush();
            } catch (IOException e) {
                Log.error(e);
            }
        }
    }

    public void close () {

    }
}
