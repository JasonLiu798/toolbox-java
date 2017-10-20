package com.atjl.common.api.req;


public class BaseReqDto {
    private String currentUser;

    public BaseReqDto() {
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
