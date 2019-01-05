package com.swy.server.service;

import com.swy.server.bean.BrandItemData;
import com.swy.server.bean.SellerItemData;
import com.swy.server.bean.TypeItemData;

import java.util.List;

/**
 * Created by zhouxj6112 on 2017/11/12.
 */
public interface ISellerService {

    List<SellerItemData> getAllSellers();

    SellerItemData getSellerInfoWithId(Integer sellerId);

    void addSeller(String sellerName, String sellerDesc, String sellerAddr, String sellerMobile, String website);
    void deleteSeller(String sellerId);

    List<TypeItemData> getTypeList();
    // 添加分类
    void addType(String typeName, String typeDesc, Integer parentType);

    List<BrandItemData> getBrandList();
    // 添加品牌
    void addBrand(String brandName, String brandDesc, String brandLogo);
}
