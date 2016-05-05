package com.dreamsuan.dal.sample.dataobject;

import java.io.Serializable;

public class SampleUser implements Serializable {

    private static final long serialVersionUID = -7805780516791597032L;

    private String            userId;
    private String            userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
