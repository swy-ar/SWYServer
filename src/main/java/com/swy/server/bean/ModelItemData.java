package com.swy.server.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by zhouxj6112 on 2017/11/12.
 */
public class ModelItemData {

    private Integer id;
    private String modelId;
    private String modelName;
    private String modelDesc;
    private int typeId;
    private String typeName;
    private String typeDesc;
    private String imageUrl;
    private String compressImage; // 压缩之后的图片url (用于列表页的展示)
    private String fileUrl;
    private boolean isDelete;
    private int brandId;
    private String brandName;
    private String brandDesc;
    private Timestamp createTime;
    private Timestamp updateTime;

    public ModelItemData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.modelId = result.getString("model_id");
        this.modelName = result.getString("model_name");
        this.modelDesc = result.getString("model_desc");
        this.typeId = result.getInt("type_id");
        this.typeName = result.getString("type_name")==null?"":result.getString("type_name");
        this.typeDesc = result.getString("type_desc");
        this.imageUrl = result.getString("model_img_url");
        {
            String[] strings = this.imageUrl.split("/");
            String name = strings[strings.length-1];
            String[] comps = name.split("\\.");
            String sName = comps[0];
            this.compressImage = this.imageUrl.replaceFirst(sName, "thumb_" + sName);
        }
        this.fileUrl = result.getString("model_file_url");
        this.isDelete = result.getBoolean("is_delete");
        this.createTime = result.getTimestamp("create_time")==null?new Timestamp(0):result.getTimestamp("create_time");
        this.updateTime = result.getTimestamp("update_time")==null?new Timestamp(0):result.getTimestamp("update_time");
        //
        this.brandId = result.getInt("brand_id");
        this.brandName = result.getString("brand_name");
        this.brandDesc = result.getString("brand_desc");
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelId() {
        return this.modelId;
    }
    public void setModelId(String mId) {
        this.modelId = mId;
    }

    public String getModelName() {
        return this.modelName;
    }
    public void setModelName(String mName) {
        this.modelName = mName;
    }

    public String getModelDesc() {
        return this.modelDesc;
    }
    public void setModelDesc(String mDesc) {
        this.modelDesc = mDesc;
    }

    public int getTypeId() {
        return this.typeId;
    }
    public void setTypeId(int iType) {
        this.typeId = iType;
    }

    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String mName) {
        this.typeName = mName;
    }

    public String getTypeDesc() {
        return this.typeDesc;
    }
    public void setTypeDesc(String mName) {
        this.typeDesc = mName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String mName) {
        this.imageUrl = mName;
    }

    public String getCompressImage() {
        return this.compressImage;
    }
    public void setCompressImage(String mUrl) {
        this.compressImage = mUrl;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }
    public void setFileUrl(String mName) {
        this.fileUrl = mName;
    }

    public boolean getIsDelete() {
        return this.isDelete;
    }
    public void setIsDelete(boolean is) {
        this.isDelete = is;
    }

    public int getBrandId() {
        return this.brandId;
    }
    public void setBrandId(int iType) {
        this.brandId = iType;
    }

    public String getBrandName() {
        return this.brandName;
    }
    public void setBrandName(String mName) {
        this.brandName = mName;
    }

    public String getBrandDesc() {
        return this.brandDesc;
    }
    public void setBrandDesc(String mDesc) {
        this.brandDesc = mDesc;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Timestamp context) {
        this.createTime = context;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(Timestamp context) {
        this.updateTime = context;
    }

}
