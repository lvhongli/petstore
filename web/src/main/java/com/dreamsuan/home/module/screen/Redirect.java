package com.dreamsuan.home.module.screen;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Redirect {

    private final static Logger log = LoggerFactory.getLogger(Redirect.class);

    public void execute(@Param("location") String location, Navigator nav) {
        if (location != null) {
            log.info("Redirect to location which is not in whitelist: {}", location);
            nav.redirectToLocation(location);
        } else {
            nav.forwardTo("homepage");
        }
    }
}
