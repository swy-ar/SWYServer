package com.swy.server.dao;

import com.swy.server.bean.BrandItemData;
import com.swy.server.bean.SellerItemData;
import com.swy.server.bean.TypeItemData;

import java.util.List;

/**
 * Created by zhouxj6112 on 2017/11/17.
 */
public interface ISellerDao {

    List<SellerItemData> getAllSelllers();

    SellerItemData getSellerInfoWithId(Integer sellerId);

    void addSeller(String sellerName, String sellerDesc, String sellerAddr, String sellerMobile, String website);
    void deleteSeller(String sellerId);

    List<TypeItemData> getTypeList();
    void addType(String typeName, String typeDesc, Integer parentType);

    List<BrandItemData> getBrandList();
    void addBrand(String brandName, String brandDesc, String brandLogo);
}
