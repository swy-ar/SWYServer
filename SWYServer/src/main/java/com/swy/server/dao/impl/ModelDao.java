package com.swy.server.dao.impl;

import com.swy.server.bean.BrandItemData;
import com.swy.server.bean.ModelItemData;
import com.swy.server.bean.TypeItemData;
import com.swy.server.dao.IModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSetMetaData;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxj6112 on 2017/11/17.
 */
@Repository
public class ModelDao implements IModelDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ModelItemData> getModelList(Integer sellerId) {
        final String sql = "select " +
                " seller_model.id,seller_model.model_id,seller_model.model_name,seller_model.model_desc,seller_model.model_type,seller_model.model_img_url,seller_model.model_file_url,seller_model.model_size,seller_model.create_time,seller_model.update_time,seller_model.type_id,model_type.type_name,model_type.type_desc,seller_model.brand_id,shop_brand.brand_name,shop_brand.brand_desc,seller_model.is_delete" +
                " from seller_model" +
                " left join model_type on seller_model.type_id = model_type.type_id" +
                " left join shop_brand on seller_model.brand_id = shop_brand.brand_id" +
                " where seller_id = " + sellerId + " order by update_time desc";
//        final String sql = "select * from seller_model,model_type where seller_id = " + sellerId + " and (isnull(is_delete)=1 or is_delete=0) and seller_model.type_id = model_type.type_id";
        List<ModelItemData> list = jdbcTemplate.query(sql, new String[]{}, new RowMapper<ModelItemData>() {
            public ModelItemData mapRow(ResultSet resultSet, int i) throws SQLException {
                return new ModelItemData(resultSet);
            }
        });
        return list;
    }

    @Override
    public ModelItemData getModelInfo(String modelId) {
        final String sql = "select * from seller_model" +
                " left join model_type on model_type.type_id = seller_model.type_id" +
                " left join shop_brand on shop_brand.brand_id = seller_model.brand_id" +
                " where model_id = '" + modelId + "'";
        List<ModelItemData> list = jdbcTemplate.query(sql, new String[]{}, new RowMapper<ModelItemData>() {
            public ModelItemData mapRow(ResultSet resultSet, int i) throws SQLException {
                return new ModelItemData(resultSet);
            }
        });
        return list.get(list.size()-1);
    }

    @Override
    public void saveModelToDB(String modelName, String imageUrl, String modelFile, Integer modelSize, Integer typeId, Integer brandId, Integer sellerId) {
        int count = 0;
        final String countSql = "select count(*) as count from seller_model where seller_id = " + sellerId;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(countSql);
        while (rowSet.next()) {
            count = rowSet.getInt("count");
        }
        //
        String modelId = sellerId.toString() + "_" + (count + 1); // 保证modelId全局唯一性
        final String sql = "insert into seller_model (seller_id, model_id, model_name, model_img_url, model_file_url, model_size, type_id, brand_id, create_time, update_time) values (?, ?, ?, ?, ?, ?, ?, ?, now(), now())";
        jdbcTemplate.update(sql, sellerId, modelId, modelName, imageUrl, modelFile, modelSize, typeId, brandId);
    }

    @Override
    public void deleteModel(String modelId) {
        final String sql = "update seller_model set is_delete = ?, update_time = now() where model_id = ?";
        jdbcTemplate.update(sql, 1, modelId);
    }

    @Override
    public List<ModelItemData> queryModelList(String sellerId, String typeId, String brandId) {
        String sql = "select * from seller_model,model_type,shop_brand where seller_model.is_delete != 1 and seller_model.type_id = model_type.type_id and seller_model.brand_id = shop_brand.brand_id";
        if (sellerId != null && sellerId.length() > 0) {
            sql += " and seller_model.seller_id = " + sellerId;
        }
        if (typeId != null && typeId.length() > 0) {
            sql += " and seller_model.type_id = " + typeId;
        }
        if (brandId != null && brandId.length() > 0) {
            sql += " and seller_model.brand_id = " + brandId;
        }
        List<ModelItemData> list = jdbcTemplate.query(sql, new String[]{}, new RowMapper<ModelItemData>() {
            public ModelItemData mapRow(ResultSet resultSet, int i) throws SQLException {
                return new ModelItemData(resultSet);
            }
        });
        return list;
    }

}
