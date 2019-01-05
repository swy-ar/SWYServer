package com.swy.server.service;

import com.swy.server.bean.BrandItemData;
import com.swy.server.bean.ModelItemData;
import com.swy.server.bean.TypeItemData;

import java.util.List;

/**
 * Created by zhouxj6112 on 2017/11/13.
 */
public interface IModelService {

    List<ModelItemData> getModelList(Integer sellerId);
    ModelItemData getModelInfo(String modelId);

    void saveModelToDB(String modelName, String imageUrl, String modelFile, Integer modelSize, Integer typeId, Integer brandId, Integer sellerId);

    void deleteModel(String modelId);

    List<ModelItemData> queryModelList(String sellerId, String typeId, String brandId);
}
