package com.swy.server.service;

import com.swy.server.bean.CaseItemData;

import java.util.List;

/**
 * Created by zhouxj6112 on 2017/12/29.
 */
public interface ICaseService {

    void addShareExample(String userId, String exampleUrl);
    List<CaseItemData> getCaseList();

    void changeStatus(String id, String status);

}
