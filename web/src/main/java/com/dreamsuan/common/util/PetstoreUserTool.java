package com.dreamsuan.common.util;

import com.alibaba.citrus.service.pull.ToolFactory;
import com.dreamsuan.common.PetstoreUser;

public class PetstoreUserTool implements ToolFactory {

    public boolean isSingleton() {
        return false;
    }

    public Object createTool() throws Exception {
        return PetstoreUser.getCurrentUser();
    }
}
