package com.swy.server.service.impl;

import com.swy.server.bean.CaseItemData;
import com.swy.server.dao.ICaseDao;
import com.swy.server.dao.IModelDao;
import com.swy.server.service.ICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhouxj6112 on 2017/12/29.
 */
@Repository
public class CaseService implements ICaseService {

    @Autowired
    ICaseDao iCaseDao;

    @Override
    public void addShareExample(String userId, String exampleUrl) {
        iCaseDao.addShareExample(userId, exampleUrl);
    }

    @Override
    public List<CaseItemData> getCaseList() {
        return iCaseDao.getCaseList();
    }

    @Override
    public void changeStatus(String id, String status) {
        iCaseDao.changeStatus(id, status);
    }

}
