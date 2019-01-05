package com.swy.server.dao;

import com.swy.server.bean.CaseItemData;

import java.util.List;

/**
 * Created by zhouxj6112 on 2017/12/31.
 */
public interface ICaseDao {

    void addShareExample(String userId, String exampleUrl);
    List<CaseItemData> getCaseList();

    void changeStatus(String id, String status);

}
