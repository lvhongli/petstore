package com.dreamsuan.common;

import static com.alibaba.citrus.util.Assert.*;
import static com.alibaba.citrus.util.StringUtil.*;
import static com.dreamsuan.common.PetstoreConstant.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.springext.support.BeanSupport;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.pipeline.valve.PageAuthorizationValve.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PetstoreUserAuth extends BeanSupport implements Callback<PetstoreUserAuth.Status> {

    private static final Logger log = LoggerFactory.getLogger(PetstoreUserAuth.class);

    @Autowired
    private URIBrokerService    uriBrokerService;

    private String              sessionKey;
    private String              brokerId;
    private String              returnKey;

    public void setSessionKey(String sessionKey) {
        this.sessionKey = trimToNull(sessionKey);
    }

    public void setRedirectURI(String brokerId) {
        this.brokerId = trimToNull(brokerId);
    }

    public void setReturnKey(String returnKey) {
        this.returnKey = trimToNull(returnKey);
    }

    @Override
    public void init() {
        assertNotNull(uriBrokerService, "could not get URIBrokerService");

        if (sessionKey == null) {
            sessionKey = PETSTORE_USER_SESSION_KEY;
        }

        if (brokerId == null) {
            brokerId = PETSTORE_LOGIN_LINK;
        }

        if (returnKey == null) {
            returnKey = LOGIN_RETURN_KEY;
        }
    }

    public Status onStart(TurbineRunData rundata) {
        HttpSession session = rundata.getRequest().getSession();
        PetstoreUser user = null;

        try {
            user = (PetstoreUser) session.getAttribute(sessionKey);
        } catch (Exception e) {
            log.warn("Failed to read PetstoreUser from session: " + sessionKey, e);
        }

        if (user == null) {
            // 创建匿名用户
            user = new PetstoreUser();
            session.setAttribute(sessionKey, user);
        }

        // 将user设置到rundata中，以便其它程序使用。
        PetstoreUser.setCurrentUser(user);

        return new Status(rundata, user);
    }

    public String getUserName(Status status) {
        return status.user.getId();
    }

    public String[] getRoleNames(Status status) {
        return status.user.getRoles();
    }

    public String[] getActions(Status status) {
        return null;
    }

    public void onAllow(Status status) throws Exception {
    }

    public void onDeny(Status status) throws Exception {
        HttpServletRequest request = status.rundata.getRequest();
        HttpServletResponse response = status.rundata.getResponse();
        URIBroker redirectURI = uriBrokerService.getURIBroker(brokerId);

        assertNotNull(redirectURI, "no URI broker found: %s", brokerId);

        StringBuffer buf = request.getRequestURL();
        String queryString = trimToNull(request.getQueryString());

        if (queryString != null) {
            buf.append('?').append(queryString);
        }

        String returnURL = buf.toString();

        response.sendRedirect(redirectURI.addQueryData(returnKey, returnURL).render());
    }

    static class Status {

        private final TurbineRunData rundata;
        private PetstoreUser         user;

        public Status(TurbineRunData rundata, PetstoreUser user){
            this.rundata = rundata;
            this.user = user;
        }
    }
}
