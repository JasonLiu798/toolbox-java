package com.atjl.cas.api;


public interface CasUserService {

    /**
     * 是否存在
     * @param uid
     * @return
     */
    boolean exist(String uid);

    /**
     * 更新登录状态
     * @param uid
     */
    void updateLoginState(String uid);

    /**
     * 是否登录状态
     * @param uid
     * @return
     */
    boolean stillLogin(String uid);

}
