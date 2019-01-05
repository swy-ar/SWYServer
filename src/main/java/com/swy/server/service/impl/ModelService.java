package com.swy.server.service.impl;

import com.swy.server.bean.BrandItemData;
import com.swy.server.bean.ModelItemData;
import com.swy.server.bean.TypeItemData;
import com.swy.server.dao.IModelDao;
import com.swy.server.service.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhouxj6112 on 2017/11/13.
 */
@Repository
public class ModelService implements IModelService {

    @Autowired
    IModelDao iDao;

    @Override
    public List<ModelItemData> getModelList(Integer sellerId) {
        return iDao.getModelList(sellerId);
    }

    @Override
    public ModelItemData getModelInfo(String modelId) {
        return iDao.getModelInfo(modelId);
    }

    @Override
    public void saveModelToDB(String modelName, String imageUrl, String modelFile, Integer modelSize, Integer typeId, Integer brandId, Integer sellerId) {
        iDao.saveModelToDB(modelName, imageUrl, modelFile, modelSize, typeId, brandId, sellerId);
    }

    @Override
    public void deleteModel(String modelId) {
        iDao.deleteModel(modelId);
    }

    @Override
    public List<ModelItemData> queryModelList(String sellerId, String typeId, String brandId) {
        return iDao.queryModelList(sellerId, typeId, brandId);
    }
}
