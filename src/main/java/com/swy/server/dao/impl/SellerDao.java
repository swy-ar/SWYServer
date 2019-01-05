package com.swy.server.dao.impl;

import com.swy.server.bean.BrandItemData;
import com.swy.server.bean.SellerItemData;
import com.swy.server.bean.TypeItemData;
import com.swy.server.dao.ISellerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhouxj6112 on 2017/11/17.
 */
@Repository
public class SellerDao implements ISellerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SellerItemData> getAllSelllers() {
        final String sql = "select * from shop_seller where is_delete != 1 order by seller_id";
        List<SellerItemData> list = jdbcTemplate.query(sql, new String[]{}, new RowMapper<SellerItemData>() {
            public SellerItemData mapRow(ResultSet resultSet, int i) throws SQLException {
                return new SellerItemData(resultSet);
            }
        });
        return list;
    }

    @Override
    public SellerItemData getSellerInfoWithId(Integer sellerId) {
        final String sql = "select * from shop_seller where seller_id = " + sellerId;
        List<SellerItemData> list = jdbcTemplate.query(sql, new String[]{}, new RowMapper<SellerItemData>() {
            public SellerItemData mapRow(ResultSet resultSet, int i) throws SQLException {
                return new SellerItemData(resultSet);
            }
        });
        return list.get(0);
    }

    @Override
    public void addSeller(String sellerName, String sellerDesc, String sellerAddr, String sellerMobile, String website) {
        final String sql = "select * from shop_seller order by seller_id desc limit 1";
        List<SellerItemData> list = jdbcTemplate.query(sql, new String[]{}, new RowMapper<SellerItemData>() {
            public SellerItemData mapRow(ResultSet resultSet, int i) throws SQLException {
                return new SellerItemData(resultSet);
            }
        });
        SellerItemData data = list.get(0);
        Integer id = data.getId();
        final String insertSql = "insert into shop_seller (seller_id, seller_name, seller_desc, seller_address, seller_mobile, seller_website, member_level, is_disable, create_time, update_time, is_delete) values (?, ?, ?, ?, ?, ?, 1, 0, now(), now(), 0)";
        jdbcTemplate.update(insertSql, 1000+id, sellerName, sellerDesc, sellerAddr, sellerMobile, website);
    }

    @Override
    public void deleteSeller(String sellerId) {
        final String sql = "update shop_seller set is_delete = ?, update_time = now() where seller_id = ?";
        jdbcTemplate.update(sql, 1 ,sellerId);
    }

    @Override
    public List<TypeItemData> getTypeList() {
        final String sql = "select * from model_type order by type_id";
        List<TypeItemData> list = jdbcTemplate.query(sql, new String[]{}, new RowMapper<TypeItemData>() {
            public TypeItemData mapRow(ResultSet resultSet, int i) throws SQLException {
                return new TypeItemData(resultSet);
            }
        });
        return list;
    }

    @Override
    public void addType(String typeName, String typeDesc, Integer parentType) {
        List<TypeItemData> list = this.getTypeList();
        int typeId = list.get(list.size()-1).getTypeId() + 1;
        final String sql = "insert into model_type (type_id, type_name, type_desc, parent_type, create_time, update_time) values (?, ?, ?, ?, now(), now())";
        jdbcTemplate.update(sql, typeId, typeName, typeDesc, parentType);
    }

    @Override
    public List<BrandItemData> getBrandList() {
        final String sql = "select * from shop_brand order by brand_id";
        List<BrandItemData> list = jdbcTemplate.query(sql, new String[]{}, new RowMapper<BrandItemData>() {
            public BrandItemData mapRow(ResultSet resultSet, int i) throws SQLException {
                return new BrandItemData(resultSet);
            }
        });
        return list;
    }

    @Override
    public void addBrand(String brandName, String brandDesc, String brandLogo) {
        List<BrandItemData> list = this.getBrandList();
        int brandId = list.get(list.size()-1).getBrandId() + 1;
        final String sql = "insert into shop_brand (brand_id, brand_name, brand_desc, brand_logo, create_time, update_time) values (?, ?, ?, ?, now(), now())";
        jdbcTemplate.update(sql, brandId, brandName, brandDesc, brandLogo);
    }
}
