package com.swy.server.controller;

import com.swy.server.bean.*;
import com.swy.server.config.Config;
import com.swy.server.service.ICaseService;
import com.swy.server.service.IModelService;
import com.swy.server.service.ISellerService;
import com.swy.server.template.FreeMarkertUtil;
import com.swy.server.utils.HttpUtil;
import com.swy.server.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouxj6112 on 2017/11/12.
 */
@Controller
@RequestMapping("/seller")
public class PageController {

    @Autowired
    ISellerService sellerService;
    @Autowired
    IModelService modelService;
    @Autowired
    ICaseService caseService;

    @RequestMapping("/list")
    public @ResponseBody
    void getSellerList(HttpServletRequest request, HttpServletResponse response) {
        List<SellerItemData> list = sellerService.getAllSellers();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("list", list);
        Map<String, Object> result = FreeMarkertUtil.parseTemplate(Config.freemarkerFilePath, "seller_list.ftl", root);
        HttpUtil.setResponse(response, result);
    }

    @RequestMapping("/page_model_list")
    public @ResponseBody
    void getModelList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = HttpUtil.convertToLowerParams(request);
        String sellerId = map.get("sellerId");
        String typeId = map.get("typeId");
        String brandId = map.get("brandId");
        List<ModelItemData> list = modelService.queryModelList(sellerId, typeId, brandId);
        List<TypeItemData> tList = sellerService.getTypeList();
        List<BrandItemData> bList = sellerService.getBrandList();
        List<SellerItemData> sList = sellerService.getAllSellers();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("list", list);
        root.put("tList", tList);
        root.put("bList", bList);
        root.put("sList", sList);
        Map<String, Object> result = FreeMarkertUtil.parseTemplate(Config.freemarkerFilePath, "model_list.ftl", root);
        HttpUtil.setResponse(response, result);
    }

    @RequestMapping("/info")
    public @ResponseBody
    void getSellerInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = HttpUtil.convertToLowerParams(request);
        String sellerId = map.get("sid").replace(",", "");
        SellerItemData item = sellerService.getSellerInfoWithId(new Integer(sellerId));
        List<ModelItemData> list = modelService.getModelList(new Integer(sellerId));
        List<TypeItemData> tList = sellerService.getTypeList();
        List<BrandItemData> bList = sellerService.getBrandList();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("info", item);
        root.put("list", list);
        root.put("tList", tList);
        root.put("bList", bList);
        Map<String, Object> result = FreeMarkertUtil.parseTemplate(Config.freemarkerFilePath, "seller_info.ftl", root);
        HttpUtil.setResponse(response, result);
    }

    // 分类管理页
    @RequestMapping("/type_list")
    public @ResponseBody
    void getTypeList(HttpServletRequest request, HttpServletResponse response) {
        List<TypeItemData> tList = sellerService.getTypeList();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("list", tList);
        Map<String, Object> result = FreeMarkertUtil.parseTemplate(Config.freemarkerFilePath, "type_list.ftl", root);
        HttpUtil.setResponse(response, result);
    }

    @RequestMapping("/brand_list")
    public @ResponseBody
    void getBrandList(HttpServletRequest request, HttpServletResponse response) {
        List<BrandItemData> bList = sellerService.getBrandList();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("list", bList);
        Map<String, Object> result = FreeMarkertUtil.parseTemplate(Config.freemarkerFilePath, "brand_list.ftl", root);
        HttpUtil.setResponse(response, result);
    }

    // 模型h5查看页 (可以作为分享的外部页)
    @RequestMapping("/model_share")
    public @ResponseBody
    void shareModel(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = HttpUtil.convertToLowerParams(request);
        String modelId = map.get("mid");
        //
        ModelItemData data = modelService.getModelInfo(modelId);
        String zipFileUrl = data.getFileUrl();

        Map<String, Object> root = new HashMap<String, Object>();
        root.put("fileUrl", zipFileUrl);
        Map<String, Object> result = FreeMarkertUtil.parseTemplate(Config.freemarkerFilePath, "model_share.ftl", root);
        HttpUtil.setResponse(response, result);
    }

    // 分享案例管理界面
    @RequestMapping("/example_manager")
    public @ResponseBody
    void exampleManager(HttpServletRequest request, HttpServletResponse response) {
        List<CaseItemData> list = caseService.getCaseList();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("list", list);
        Map<String, Object> result = FreeMarkertUtil.parseTemplate(Config.freemarkerFilePath, "example_list.ftl", root);
        HttpUtil.setResponse(response, result);
    }

    //***************************************** 后台接口层(ajax异步请求使用的)*****************************************

    @RequestMapping("/model_list")
    public @ResponseBody
    void queryModelList(HttpServletRequest request, HttpServletResponse response) {
        final Map<String, String> params = HttpUtil.convertToLowerParams(request);
        String sellerId = params.get("sellerid");
        sellerId = sellerId.replace(",", "");
        String typeId = params.get("typeid");
        String brandId = params.get("brandid");
        List<ModelItemData> list;
        try {
           list = modelService.queryModelList(sellerId, typeId, brandId);
        } catch (Exception e) {
            Log.error(e);
           list = new ArrayList<ModelItemData>();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", list);
        result.put("code", 200);
        result.put("msg", "succ");
        HttpUtil.setApiResponse(response, result);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping("/list.html")
    public @ResponseBody
    void htmlSellerList(HttpServletRequest request, HttpServletResponse response) {
        // 返回HTML页面
        Map<String, Object> map = new HashMap<String, Object>();
        // 读取seller_list.html文件内容
        String htmlString = FreeMarkertUtil.parseFileContent(Config.htmlFilePath, "seller_list.html");
        map.put("content", htmlString);
        HttpUtil.setResponse(response, map);
    }

}
