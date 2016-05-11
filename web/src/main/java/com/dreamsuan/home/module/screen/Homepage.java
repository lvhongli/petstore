package com.dreamsuan.home.module.screen;

import com.alibaba.citrus.turbine.Context;

public class Homepage {

    public void execute(Context context) throws Exception {
        context.put("path", this.getProjectPath());
    }
    
    public String getProjectPath() {
        String nowpath = System.getProperty("user.dir");
        String tempdir = nowpath.replace("bin", "webapps");
        return tempdir; 
    }
}
