package com.swy.server.service.impl;

import com.swy.server.dao.ITypeDao;
import com.swy.server.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhouxj6112 on 2018/6/10.
 */
public class TypeService implements ITypeService {

    @Autowired
    ITypeDao iDao;

    @Override
    public void deleteType(String typeId) {
        iDao.deleteType(typeId);
    }

}
