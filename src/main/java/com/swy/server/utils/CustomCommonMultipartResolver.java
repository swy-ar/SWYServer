package com.swy.server.utils;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Json on 7/7/2016.
 */
public class CustomCommonMultipartResolver extends CommonsMultipartResolver {

    @Override
    public boolean isMultipart(HttpServletRequest request) {
        if (request.getRequestURI().equals("/api/uploadModel")) {
            return super.isMultipart(request);
        } else if (request.getRequestURI().equals("/api/addSeller")) {
            return super.isMultipart(request);
        } else if (request.getRequestURI().equals("/api/shareExample")) {
            return super.isMultipart(request);
        } else if (request.getRequestURI().equals("/api/addBrand")) {
            return super.isMultipart(request);
        }
        return false;
    }
}
