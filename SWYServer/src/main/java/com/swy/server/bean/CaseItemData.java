package com.swy.server.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by zhouxj6112 on 2017/12/31.
 */
public class CaseItemData {

    private Long id;
    private String userId;
    private String exampleUrl;
    private String auditStatus;
    private Timestamp createTime;
    private Timestamp updateTime;

    public CaseItemData(ResultSet result) throws SQLException {
        this.id = result.getLong("id");
        this.userId = result.getString("user_id");
        this.exampleUrl = result.getString("example_url");
        this.auditStatus = result.getString("audit_status");
        if (this.auditStatus == null) {
            this.auditStatus = "0";
        }
        this.createTime = result.getTimestamp("create_time")==null?new Timestamp(0):result.getTimestamp("create_time");
        this.updateTime = result.getTimestamp("update_time")==null?new Timestamp(0):result.getTimestamp("update_time");
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return this.userId;
    }

    public void setExampleUrl(String url) {
        this.exampleUrl = url;
    }
    public String getExampleUrl() {
        return this.exampleUrl;
    }

    public void setAuditStatus(String status) {
        this.auditStatus = status;
    }
    public String getAuditStatus() {
        return this.auditStatus;
    }

    public void setCreateTime(Timestamp time) {
        this.createTime = time;
    }
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setUpdateTime(Timestamp time) {
        this.updateTime = time;
    }
    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

}
