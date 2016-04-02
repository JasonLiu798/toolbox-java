package com.jason.testutil;


/**
 * @author JianLong
 * @date 2015/11/27 11:13
 */
public class AddUserFavoritesReqDto {

    private static final long serialVersionUID = 6870064307071900837L;

    /** 用户id **/
    private Long uid;
    /** 商品id **/
    private Long gid;
    /** 应用：1-卷皮、2-九块邮 **/
    private Integer app;
    /** 来源：1-pc、2-m版、3-android、4-iphone、5-ipad **/
    private Integer from;
    /** 商品类型：1-普通商品、2-采集商品、3-特卖商品 **/
    private Integer gtype;
    /** 用户IP **/
    private String ip;
    /** url **/
    private String local;
    /** 平台来源，微信-wx，其他平台-pc (默认：pc)**/
    private String site;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public String getLocal() {
        return local;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Integer getApp() {
        return app;
    }

    public void setApp(Integer app) {
        this.app = app;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getGtype() {
        return gtype;
    }

    @Override
    public String toString() {
        return "AddUserFavoritesReqDto{" +
                "uid=" + uid +
                ", gid=" + gid +
                ", app=" + app +
                ", from=" + from +
                ", gtype=" + gtype +
                ", ip='" + ip + '\'' +
                ", local='" + local + '\'' +
                ", site='" + site + '\'' +
                '}';
    }

    public void setGtype(Integer gtype) {
        this.gtype = gtype;
    }
}