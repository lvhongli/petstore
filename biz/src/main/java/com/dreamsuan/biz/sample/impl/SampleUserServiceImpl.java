package com.dreamsuan.biz.sample.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dreamsuan.biz.sample.SampleUserService;
import com.dreamsuan.dal.sample.dao.SampleUserDao;
import com.dreamsuan.dal.sample.dataobject.SampleUser;

public class SampleUserServiceImpl implements SampleUserService {

    @Autowired
    private SampleUserDao sampleUserDao;

    @Override
    public List<SampleUser> getSampleUserList() {
        return sampleUserDao.getSampleUserList();
    }

}
