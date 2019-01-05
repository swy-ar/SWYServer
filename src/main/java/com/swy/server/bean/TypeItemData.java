package com.swy.server.bean;

import java.sql.*;

/**
 * Created by zhouxj6112 on 2017/11/29.
 */
public class TypeItemData {
    private Integer typeId;
    private String typeName;
    private String typeDesc;
    private Integer parentType;
    private Timestamp createTime;

    public TypeItemData(ResultSet result) throws SQLException {
        this.typeId = result.getInt("type_id");
        this.typeName = result.getString("type_name");
        this.typeDesc = result.getString("type_desc");
        this.parentType = result.getInt("parent_type");
        this.createTime = new Timestamp(0);
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

    public int getParentType() {
        return this.parentType;
    }
    public void setParentType(int iType) {
        this.parentType = iType;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Timestamp s) {
        this.createTime = s;
    }
}
