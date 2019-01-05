package com.swy.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.swy.server.bean.*;
import com.swy.server.service.ICaseService;
import com.swy.server.service.IModelService;
import com.swy.server.service.ISellerService;
import com.swy.server.service.ITypeService;
import com.swy.server.utils.HttpUtil;
import com.swy.server.utils.ImageUtil;
import com.swy.server.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

/**
 * Created by zhouxj6112 on 2017/11/13.
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    final static String nginxDocPath = GlobalEnv.nginxDocPath;
    final static String nginxFilePath = GlobalEnv.nginxFilePath;
    final static String nginxMoviePath = GlobalEnv.nginxMoviePath;

    final static String serverBaseUrl = GlobalEnv.serverBaseUrl;
    final static String serverFileUrl = GlobalEnv.serverFileUrl;
    final static String serverMovieUrl = GlobalEnv.serverMovieUrl;

    @Autowired
    ISellerService sellerService;
    @Autowired
    IModelService modelService;
    @Autowired
    ICaseService caseService;
//    @Autowired
    ITypeService typeService;

    @RequestMapping("/getAllSellers")
    public @ResponseBody
    void getSellerList(HttpServletRequest request, HttpServletResponse response) {
        List<SellerItemData> list = sellerService.getAllSellers();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "succ");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("items", list);
        result.put("data", data);
        HttpUtil.setApiResponse(response, result);
    }

    @RequestMapping("/getModelsList")
    public @ResponseBody
    void getModelsList(HttpServletRequest request, HttpServletResponse response) {
        final Map<String, String> params = HttpUtil.convertToLowerParams(request);
        String sellerId = params.get("sellerid");
        List<ModelItemData> list = modelService.getModelList(new Integer(sellerId));
        // 分组 (按模型类别)
        List bList = sellerService.getBrandList();
        List results = new ArrayList<JSONObject>();
        for (int i=0; i<list.size(); i++) {
            ModelItemData item = list.get(i);
            // 给品牌字段赋值
            if (item.getBrandId() > 0) {
                for (int j=0; j<bList.size(); j++) {
                    BrandItemData bItem = (BrandItemData) bList.get(j);
                    if (bItem.getBrandId() == item.getBrandId()) {
                        item.setBrandName(bItem.getBrandName());
                        item.setBrandDesc(bItem.getBrandDesc());
                        break;
                    }
                }
            }
            if (item.getIsDelete()) {
                continue; // 排除已经删除过的
            }
            boolean bFind = false;
            for (int n=0; n<results.size(); n++) {
                JSONObject obj = (JSONObject) results.get(n);
                if (obj.get("typeId").equals(item.getTypeId())) {
                    List models = (ArrayList<ModelItemData>)obj.get("list");
                    models.add(item);
                    bFind = true;
                }
            }
            if (!bFind) {
                JSONObject json = new JSONObject();
                json.put("typeId", item.getTypeId());
                json.put("typeName", item.getTypeName());
                json.put("typeDesc", item.getTypeDesc());
                List models = new ArrayList<ModelItemData>();
                models.add(item);
                json.put("list", models);
                results.add(json);
            }
        }
        //
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", results);
        result.put("code", 200);
        result.put("msg", "succ");
        HttpUtil.setApiResponse(response, result);
    }

    // 获取某个模型的详情 (可以批量)
    @RequestMapping("/modelInfo")
    public @ResponseBody
    void getModelInfo(HttpServletRequest request, HttpServletResponse response) {
        final Map<String, String> params = HttpUtil.convertToLowerParams(request);
        String ids = params.get("modelids"); // 传参: 1000,1001,1002
        String[] idList = ids.split(",");
        List<ModelItemData> results = new ArrayList<ModelItemData>();
        for (int i=0; i<idList.length; i++) {
            String id = idList[i];
            try {
                ModelItemData item = modelService.getModelInfo(id);
                if (item != null) {
                    results.add(item);
                }
            } catch (Exception e) {
                Log.debug(e.getLocalizedMessage());
            }
        }
        //
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", results);
        result.put("code", 200);
        result.put("msg", "succ");
        HttpUtil.setApiResponse(response, result);
    }

    // 查找所有品牌
    @RequestMapping("/allBrands")
    public @ResponseBody
    void allBrands(HttpServletRequest request, HttpServletResponse response) {
        List<BrandItemData> list;
        try {
           list = sellerService.getBrandList();
        } catch (Exception e) {
            Log.error(e);
            list = new ArrayList<BrandItemData>();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", list);
        result.put("code", 200);
        result.put("msg", "succ");
        HttpUtil.setApiResponse(response, result);
    }

    /**
     *  根据品牌查找所有模型
     * @param request
     * @param response
     */
    @RequestMapping("/queryModelsListInBrand")
    public @ResponseBody
    void queryModelsListInBrand(HttpServletRequest request, HttpServletResponse response) {
        final Map<String, String> params = HttpUtil.convertToLowerParams(request);
        String brandId = params.get("brandid");
        List<ModelItemData> list;
        try {
            list = modelService.queryModelList(null, null, brandId);
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

    // 查找所有分类
    @RequestMapping("/allCategories")
    public @ResponseBody
    void allCategories(HttpServletRequest request, HttpServletResponse response) {
        List<TypeItemData> list;
        try {
            list = sellerService.getTypeList();
        } catch (Exception e) {
            Log.error(e);
            list = new ArrayList<TypeItemData>();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", list);
        result.put("code", 200);
        result.put("msg", "succ");
        HttpUtil.setApiResponse(response, result);
    }

    /**
     *  根据分类查找所有模型
     * @param request
     * @param response
     */
    @RequestMapping("/queryModelsListInCategory")
    public @ResponseBody
    void queryModelsListInCategory(HttpServletRequest request, HttpServletResponse response) {
        final Map<String, String> params = HttpUtil.convertToLowerParams(request);
        String typeId = params.get("typeid");
        List<ModelItemData> list;
        try {
            list = modelService.queryModelList(null, typeId, null);
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

    @RequestMapping("/uploadModel")
    public @ResponseBody
    void uploadModel(@RequestParam(value = "modelName", required = false) String modelName,
                     @RequestParam(value = "modelImage") MultipartFile modelImage,
                     @RequestParam(value = "modelFile") MultipartFile modelFile,
                     @RequestParam(value = "modelType", required = true) String typeId,
                     @RequestParam(value = "modelBrand", required = true) String brandId,
                     @RequestParam(value = "sellerId", required = true) String sellerId,
                     HttpServletRequest request, HttpServletResponse response) {
        // loading进度条
        ProcessHandler handler = new ProcessHandler(response);

        // 先检查输入参数
        if (sellerId.length() == 0) {
            handler.showProcessInfo("ERROR:sellerId不能为空", "showResult");
            return;
        } else if (typeId.length() == 0) {
            handler.showProcessInfo("ERROR:typeId不能为空", "showResult");
            return;
        } else if (brandId.length() == 0) {
            handler.showProcessInfo("ERROR:brandId不能为空", "showResult");
            return;
        }

        sellerId = sellerId.replace(",", "");
        // 创建文件夹(以商家为目录)
        String dirPath = nginxFilePath + sellerId + "/";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.setWritable(true);
            Boolean b = dir.mkdirs();
            if (!b) {
                Log.error("创建存放模型的文件夹失败");
            }
        }

        handler.showProcessInfo("正在上传图片...", "showProcess");
        Date curDate = new Date();

        // 将图片存放到nginx的路径中去
        String imageFileUrl = "";
        try {
            // 加上时间戳,重命名文件名,防止重名
            String fileName = curDate.getTime() + "-" + modelImage.getOriginalFilename();
            String imageFilePath = dirPath + fileName;
            File file = new File(imageFilePath);
            modelImage.transferTo(file);
            imageFileUrl = serverFileUrl + sellerId + "/" + fileName;
            // 保存完大图后，再裁剪出缩略图
            ImageUtil.thumbnailImage(file.getPath(), 180, 180);
        } catch (IOException e) {
            Log.error(e);
        }
        handler.showProcessInfo("正在上传模型...", "showProcess");
        // 压缩包
        String zipFileUrl = "";
        int zipFileSize = 0;
        try {
            byte[] fileData = modelFile.getBytes();
            zipFileSize = fileData.length;
            // 加上时间戳,重命名文件名,防止重名
            String fileName = curDate.getTime() + "-" + modelFile.getOriginalFilename();
            String zipFilePath = dirPath + fileName;
            File file = new File(zipFilePath);
            modelFile.transferTo(file);
            zipFileUrl = serverFileUrl + sellerId + "/" + fileName;
            // 备注:阴影文件,也在这个压缩包里面
        } catch (IOException e) {
            Log.error(e);
        }
        handler.showProcessInfo("正在写入数据库...", "showProcess");
        // 写入数据库
        try {
            Integer sId = new Integer(sellerId);
            Integer tId = new Integer(typeId);
            Integer bId = new Integer(brandId);
            modelService.saveModelToDB(modelName, imageFileUrl, zipFileUrl, zipFileSize, tId, bId, sId);
        } catch (Exception e) {
            Log.error(e);
        }

        // 返回结果给js
        handler.showProcessInfo("success", "showResult");
    }

    @RequestMapping("/addSeller")
    public @ResponseBody
    void addSeller(@RequestParam(value = "sellerName") String sellerName,
                   @RequestParam(value = "sellerDesc") String sellerDesc,
                   @RequestParam(value = "address") String address,
                   @RequestParam(value = "mobile", required = true) String mobile,
                   @RequestParam(value = "website", required = false) String website,
                   HttpServletRequest request, HttpServletResponse response) {
        // loading进度条
        ProcessHandler handler = new ProcessHandler(response);

        handler.showProcessInfo("正在写入数据库...", "showProcess");
        // 写入数据库
        try {
            sellerService.addSeller(sellerName, sellerDesc, address, mobile, website);
        } catch (Exception e) {
            Log.error(e);
        }

        // 返回结果给js
        handler.showProcessInfo("success", "showResult");
    }

    @RequestMapping("/addType")
    public @ResponseBody
    void addType(HttpServletRequest request, HttpServletResponse response) {
        ProcessHandler handler = new ProcessHandler(response);
        handler.showProcessInfo("正在写入数据库...", "showProcess");
        final Map<String, String> params = HttpUtil.convertToLowerParams(request);
        String typeName = params.get("typename");
        String typeDesc = params.get("typedesc");
        String parentType = params.get("parenttype");
        try {
            Integer parentTypeId = new Integer(parentType);
            sellerService.addType(typeName, typeDesc, parentTypeId);
        } catch (Exception e) {
            Log.error(e);
        }
        // 返回结果给js
        handler.showProcessInfo("success", "showResult");
    }

    @RequestMapping("/deleteType")
    public @ResponseBody
    void deleteType(HttpServletRequest request, HttpServletResponse response) {
        ProcessHandler handler = new ProcessHandler(response);
        handler.showProcessInfo("正在写入数据库...", "showProcess");
        final Map<String, String> params = HttpUtil.convertToLowerParams(request);
        String typeId = params.get("typeid");
        try {
            // 先查找删除分类下的商品
            List<ModelItemData> list = modelService.queryModelList(null, typeId, null);
            for (int i=0; i<list.size(); i++) {
                modelService.deleteModel(list.get(i).getModelId());
            }
            // 先删除分类
            typeService.deleteType(typeId);
        } catch (Exception e) {
            Log.error(e);
        }
        // 返回结果给js
        handler.showProcessInfo("success", "showResult");
    }

    @RequestMapping("/addBrand")
    public @ResponseBody
    void addBrand(@RequestParam(value = "brandName") String brandName,
                  @RequestParam(value = "brandDesc") String brandDesc,
                  @RequestParam(value = "brandLogo") MultipartFile brandLogo,
                  HttpServletRequest request, HttpServletResponse response) {
        ProcessHandler handler = new ProcessHandler(response);
        handler.showProcessInfo("正在写入数据库...", "showProcess");

        // 将视频文件存放到nginx的路径中去
        String logoFileUrl = "";
        String dirPath = nginxMoviePath;
        try {
            Date curDate = new Date();
            // 加上时间戳,重命名文件名,防止重名
            String fileName = curDate.getTime() + "-" + brandLogo.getOriginalFilename();
            String imageFilePath = dirPath + fileName;
            File file = new File(imageFilePath);
            brandLogo.transferTo(file);
            //
            logoFileUrl = serverMovieUrl  + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            Log.error(e);
        }

        try {
            sellerService.addBrand(brandName, brandDesc, logoFileUrl);
        } catch (Exception e) {
            Log.error(e);
        }
        // 返回结果给js
        handler.showProcessInfo("success", "showResult");
    }

    @RequestMapping("/deleteSeller")
    public @ResponseBody
    void deleteSeller(HttpServletRequest request, HttpServletResponse response) {
        final Map<String, String> params = HttpUtil.convertToLowerParams(request);
        String sellerId = params.get("sellerid");
        try {
            sellerService.deleteSeller(sellerId);
            // 再删除该商家下所有模型
            List<ModelItemData> list = modelService.getModelList(Integer.valueOf(sellerId));
            for (int i=0; i<list.size(); i++) {
                ModelItemData item = list.get(i);
                modelService.deleteModel(item.getModelId());
            }
        } catch (Exception e) {
            Log.error(e);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "succ");
        HttpUtil.setApiResponse(response, result);
    }

    @RequestMapping("/deleteModel")
    public @ResponseBody
    void deleteModel(HttpServletRequest request, HttpServletResponse response) {
        final Map<String, String> params = HttpUtil.convertToLowerParams(request);
        String modelId = params.get("modelid");
        try {
            modelService.deleteModel(modelId);
        } catch (Exception e) {
            Log.error(e);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "succ");
        HttpUtil.setApiResponse(response, result);
    }

    // 前端用户分享录屏内容
    @RequestMapping("/shareExample")
    public @ResponseBody
    void shareExample(@RequestParam(value = "userToken") String token,
                      @RequestParam(value = "shareMovie") MultipartFile movie,
                      HttpServletRequest request, HttpServletResponse response) {
        // 确认token的合法性,并根据token解析出userid
        String userId = "30001";

        // 将视频文件存放到nginx的路径中去
        String movieFileUrl = "";
        String dirPath = nginxMoviePath;
        try {
            Date curDate = new Date();
            // 加上时间戳,重命名文件名,防止重名
            String fileName = curDate.getTime() + "-" + movie.getOriginalFilename();
            String imageFilePath = dirPath + fileName;
            File file = new File(imageFilePath);
            movie.transferTo(file);
            //
            movieFileUrl = serverMovieUrl  + fileName;

        } catch (IOException e) {
            Log.error(e);
        }
        // 保存记录到数据库
        try {
            caseService.addShareExample(userId, movieFileUrl);
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "succ");
        result.put("contentType", "json");
        HttpUtil.setApiResponse(response, result);
    }

    //
    @RequestMapping("/examples")
    public @ResponseBody
    void getExamples(@RequestParam(value = "userToken") String token,
                      HttpServletRequest request, HttpServletResponse response) {
        List<CaseItemData> list = caseService.getCaseList();
        // 展示给前端用户的,要过滤还未审核的,或者审核未通过的
        List<CaseItemData> array = new ArrayList<CaseItemData>();
        for (int i=0; i<list.size(); i++) {
            CaseItemData item = list.get(i);
            if (item.getAuditStatus().equalsIgnoreCase("1")) {
                array.add(item);
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "succ");
        //
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("items", array);
        result.put("data", data);
        HttpUtil.setApiResponse(response, result);
    }

    /**
     * 审核分享视频接口
     * @param id
     * @param status 0 待审核 1 审核通过 -1 审核不通过
     * @param request
     * @param response
     */
    @RequestMapping("/changeStatus")
    public @ResponseBody
    void changeStatus(@RequestParam(value = "id") String id,
                      @RequestParam(value = "status") String status,
                     HttpServletRequest request, HttpServletResponse response) {
        try {
            caseService.changeStatus(id, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "succ");
        HttpUtil.setApiResponse(response, result);
    }

}
