package com.jason798.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>
 */
public class PageResp<T> implements Serializable {
    private Long totalSize;
    private List<T> objects;

    public PageResp(){
    }

    public PageResp(Long totalSize, List<T> objects) {
        this.totalSize = totalSize;
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "PageResp{" +
                "totalSize=" + totalSize +
                ", objects=" + objects +
                '}';
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }
}
