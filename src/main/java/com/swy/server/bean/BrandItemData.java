package com.swy.server.bean;

import java.sql.*;

/**
 * Created by zhouxj6112 on 2017/12/1.
 */
public class BrandItemData {
    private Integer brandId;
    private String brandName;
    private String brandDesc;
    private String brandLogo;
    private Timestamp createTime;
    private Timestamp updateTime;

    public BrandItemData(ResultSet result) throws SQLException {
        this.brandId = result.getInt("brand_id");
        this.brandName = result.getString("brand_name");
        this.brandDesc = result.getString("brand_desc");
        this.brandLogo = result.getString("brand_logo");
        this.createTime = result.getTimestamp("create_time")==null?new Timestamp(0):result.getTimestamp("create_time");
        this.updateTime = result.getTimestamp("update_time")==null?new Timestamp(0):result.getTimestamp("update_time");
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
    public void setBrandDesc(String mName) {
        this.brandDesc = mName;
    }

    public String getBrandLogo() {
        return this.brandLogo;
    }
    public void setBrandLogo(String id) {
        this.brandLogo = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp cTime) {
        this.createTime = cTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Timestamp uTime) {
        this.updateTime = uTime;
    }
}
