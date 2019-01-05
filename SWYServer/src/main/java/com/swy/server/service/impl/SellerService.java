package com.swy.server.service.impl;

import com.swy.server.bean.BrandItemData;
import com.swy.server.bean.SellerItemData;
import com.swy.server.bean.TypeItemData;
import com.swy.server.dao.ISellerDao;
import com.swy.server.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhouxj6112 on 2017/11/12.
 */
@Repository
public class SellerService implements ISellerService {

    @Autowired
    private ISellerDao iDao;

    @Override
    public List<SellerItemData> getAllSellers() {
        return iDao.getAllSelllers();
    }

    @Override
    public SellerItemData getSellerInfoWithId(Integer sellerId) {
        return iDao.getSellerInfoWithId(sellerId);
    }

    @Override
    public void addSeller(String sellerName, String sellerDesc, String sellerAddr, String sellerMobile, String website) {
        iDao.addSeller(sellerName, sellerDesc, sellerAddr, sellerMobile, website);
    }

    @Override
    public void deleteSeller(String sellerId) {
        iDao.deleteSeller(sellerId);
    }

    @Override
    public List<TypeItemData> getTypeList() {
        return iDao.getTypeList();
    }
    @Override
    public void addType(String typeName, String typeDesc, Integer parentType) {
        iDao.addType(typeName, typeDesc, parentType);
    }

    @Override
    public List<BrandItemData> getBrandList() {
        return iDao.getBrandList();
    }
    @Override
    public void addBrand(String brandName, String brandDesc, String brandLogo) {
        iDao.addBrand(brandName, brandDesc, brandLogo);
    }
}
