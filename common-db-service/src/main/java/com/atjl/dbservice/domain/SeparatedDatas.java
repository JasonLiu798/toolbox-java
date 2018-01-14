package com.atjl.dbservice.domain;


import java.util.List;
import java.util.Map;

public class SeparatedDatas {

    //需要 添加
    private int needAddCount;

    //存在，需要更新
    private int existNeedUpdateCount;

    //存在，但不需要更新，（根据load_tm排除的个数）
    private int existNoNeedUpdateCount;

    //不存在数据列表
    private List<Map> notExistDatas;
    //已经存在数据列表
    private List<Map> existDatas;

    public List<Map> getNotExistDatas() {
        return notExistDatas;
    }

    public void setNotExistDatas(List<Map> notExistDatas) {
        this.notExistDatas = notExistDatas;
    }

    public List<Map> getExistDatas() {
        return existDatas;
    }

    public void setExistDatas(List<Map> existDatas) {
        this.existDatas = existDatas;
    }

    public int getNeedAddCount() {
        return needAddCount;
    }

    public void setNeedAddCount(int needAddCount) {
        this.needAddCount = needAddCount;
    }

    public int getExistNeedUpdateCount() {
        return existNeedUpdateCount;
    }

    public void setExistNeedUpdateCount(int existNeedUpdateCount) {
        this.existNeedUpdateCount = existNeedUpdateCount;
    }

    public int getExistNoNeedUpdateCount() {
        return existNoNeedUpdateCount;
    }

    public void setExistNoNeedUpdateCount(int existNoNeedUpdateCount) {
        this.existNoNeedUpdateCount = existNoNeedUpdateCount;
    }
}
