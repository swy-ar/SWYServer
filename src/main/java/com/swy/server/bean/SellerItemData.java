package com.swy.server.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by zhouxj6112 on 2017/11/12.
 */
public class SellerItemData {

    private Integer id;
    private String sellerId;
    private String sellerName;
    private String sellerDesc;
    private Timestamp createTime;
    private Timestamp updateTime;
    private int memberLevel;
    private boolean isDisabel;
    private String sellerAddress;
    private String sellerMobile;
    private String sellerWebsite;

    private SellerItemData() {
        //
    }

    public SellerItemData(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.sellerId = result.getString("seller_id");
        this.sellerName = result.getString("seller_name");
        this.sellerDesc = result.getString("seller_desc");
        this.createTime = result.getTimestamp("create_time")==null?new Timestamp(0):result.getTimestamp("create_time");
        this.updateTime = result.getTimestamp("update_time")==null?new Timestamp(0):result.getTimestamp("update_time");
        this.memberLevel = result.getInt("member_level");
        this.isDisabel = result.getBoolean("is_disable");
        this.sellerAddress = result.getString("seller_address");
        this.sellerMobile = result.getString("seller_mobile")==null?"":result.getString("seller_mobile");
        this.sellerWebsite = result.getString("seller_website")==null?"":result.getString("seller_website");
    }

    public Integer getId() {
        return id;
    }

    public String getSellerId() {
        return sellerId;
    }
    public void setSellerId(String sId) {
        this.sellerId = sId;
    }

    public String getSellerName() {
        return sellerName;
    }
    public void setSellerName(String sName) {
        this.sellerName = sName;
    }

    public String getSellerDesc() {
        return sellerDesc;
    }
    public void setSellerDesc(String sDesc) {
        this.sellerDesc = sDesc;
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

    public int getMemberLevel() {
        return memberLevel;
    }
    public void setMemberLevel(int mLevel) {
        this.memberLevel = mLevel;
    }

    public boolean getIsDisabel() {
        return isDisabel;
    }
    public void setIsDisabel(boolean iDisabel) {
        this.isDisabel = iDisabel;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }
    public void setSellerAddress(String sDesc) {
        this.sellerAddress = sDesc;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }
    public void setSellerMobile(String sDesc) {
        this.sellerMobile = sDesc;
    }

    public String getSellerWebsite() {
        return sellerWebsite;
    }
    public void setSellerWebsite(String sDesc) {
        this.sellerWebsite = sDesc;
    }
}
