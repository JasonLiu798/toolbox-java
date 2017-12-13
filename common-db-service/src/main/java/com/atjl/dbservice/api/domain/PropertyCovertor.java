package com.atjl.dbservice.api.domain;

import com.atjl.dbservice.api.CoverteDataService;

public class PropertyCovertor {

    private String srcCol;

    private String tgtCol;

    private CoverteDataService covertor;


    public CoverteDataService getCovertor() {
        return covertor;
    }

    public void setCovertor(CoverteDataService covertor) {
        this.covertor = covertor;
    }

    public String getSrcCol() {
        return srcCol;
    }

    public void setSrcCol(String srcCol) {
        this.srcCol = srcCol;
    }

    public String getTgtCol() {
        return tgtCol;
    }

    public void setTgtCol(String tgtCol) {
        this.tgtCol = tgtCol;
    }

//    private Class<R> srcType;

//    private Class<T> tgtType;

//    private R srcVal;

//    private T tgtVal;
//    public R getSrcVal(Object src) {
//        if (src == null) {
//            return null;
//        }
//        return CovertUtil.covertObj(src, srcType);
//    }

//    public T getTgtVal(Object val) {
//        return CovertUtil.covertObj(val, tgtType);
//    }

//    public CoverteDataService<R, T> getCovertor() {
//        return covertor;
//    }

//    public void setCovertor(CoverteDataService<R, T> covertor) {
//        this.covertor = covertor;
//    }

//
//    public String getSrcCol() {
//        return srcCol;
//    }
//
//    public void setSrcCol(String srcCol) {
//        this.srcCol = srcCol;
//    }
//
//    public String getTgtCol() {
//        return tgtCol;
//    }
//
//    public void setTgtCol(String tgtCol) {
//        this.tgtCol = tgtCol;
//    }

}
