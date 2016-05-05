package com.dreamsuan.dal.sample.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.dreamsuan.dal.sample.dao.SampleUserDao;
import com.dreamsuan.dal.sample.dataobject.SampleUser;

@SuppressWarnings("deprecation")
public class IbatisSampleUserDao extends SqlMapClientDaoSupport implements SampleUserDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<SampleUser> getSampleUserList() {
        return getSqlMapClientTemplate().queryForList("SampleUser.getSampleUserList");
    }

}
