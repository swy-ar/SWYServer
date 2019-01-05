package com.swy.server.dao.impl;

import com.swy.server.dao.ITypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by zhouxj6112 on 2018/6/10.
 */
public class TypeDao implements ITypeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void deleteType(String typeId) {
        final String sql = "delete from model_type where type_id = " + typeId;
        jdbcTemplate.execute(sql);
    }
}
