package com.swy.server.dao.impl;

import com.swy.server.bean.CaseItemData;
import com.swy.server.dao.ICaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhouxj6112 on 2017/12/31.
 */
@Repository
public class CaseDao implements ICaseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addShareExample(String userId, String exampleUrl) {
        final String sql = "insert into share_example (user_id, example_url, audit_status, create_time, update_time) values (?, ?, ?, now(), now())";
        jdbcTemplate.update(sql, userId, "0", exampleUrl);
    }

    @Override
    public List<CaseItemData> getCaseList() {
        final String sql = "select * from share_example order by create_time desc";
        List<CaseItemData> list = jdbcTemplate.query(sql, new String[]{}, new RowMapper<CaseItemData>() {
            public CaseItemData mapRow(ResultSet resultSet, int i) throws SQLException {
                return new CaseItemData(resultSet);
            }
        });
        return list;
    }

    @Override
    public void changeStatus(String id, String status) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        final String sql = "update share_example set audit_status = ?, update_time = ? where id = ?";
        jdbcTemplate.update(sql, status, time, id);
    }

}
