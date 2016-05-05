package com.dreamsuan.sample.module.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.dreamsuan.biz.sample.SampleUserService;
import com.dreamsuan.dal.sample.dataobject.SampleUser;

public class SampleAccount {

    @Autowired
    private SampleUserService sampleUserService;

    public void execute(Context context) throws Exception {
        List<SampleUser> userList = sampleUserService.getSampleUserList();
        context.put("userList", userList);
    }
}
