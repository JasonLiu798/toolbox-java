package com.atjl.biz.flow.core;


import com.atjl.biz.flow.api.Flow;
import com.atjl.biz.flow.dto.FlowConstant;
import com.atjl.log.api.LogUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.reflect.ReflectMethodUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * package of span unit
 */
public class Span {
    private String spanId;
    Map<String, SpanUnit> id2SpanUnit;
    Map<Integer, SpanUnit> index2SpanUnit;
    private List<String> executeSeq;

    //ThreadLocal<Map<String, Object>> contextData;

    public Span() {
        init();
    }

    private void init() {
        this.id2SpanUnit = new HashMap<>();
        this.index2SpanUnit = new HashMap<>();
        this.executeSeq = new LinkedList<>();
    }

    /**
     * 获取fid在executeSeq中的位置
     *
     * @param fid
     * @return 不存在，或 executeSeq为空，返回值 -1
     */
    private Integer getFlowIndex(String fid) {
        int res = -1;
        if (CollectionUtil.isEmpty(this.executeSeq)) {
            return res;
        }
        for (int i = 0; i < this.executeSeq.size(); i++) {
            String seq = this.executeSeq.get(i);
            if (StringCheckUtil.equal(fid, seq)) {
                res = i;
            }
        }
        return res;
    }


    /**
     * add flow use default option
     *
     * @param snippet
     * @return
     */
    public boolean addSnippet(Flow snippet) {
        return addSnippet(snippet, FlowOptionGen.getNormalOption());
    }

    /**
     * add flow use option force goon ,no matter exception or errorcode!=0
     *
     * @param func
     * @return
     */
    public boolean addFlowForceGoon(Flow func) {
        FlowOptionGen gen = new FlowOptionGen();
        int option = gen.enableExceptionContinue().enableErrorCodeContinue().get();
        return addSnippet(func, option);
    }

    /**
     * add flow with option
     *
     * @param snippet
     * @param option
     * @return
     */
    public boolean addSnippet(Flow snippet, int option) {
        //String fid = snippet.getClass().getSimpleName();
        String suid = snippet.getId();
        SpanUnit spanUnit = new SpanUnit();
        Method m = ReflectMethodUtil.getDeclaredMethod(snippet, FlowConstant.METHOD_ACTION);
        if (m == null) {
            return false;
        }
        spanUnit.setActionMethod(m).setOption(option).setSnippet(snippet).setSuid(suid);
        LogUtil.debug("add spanUnit {}", spanUnit);

        int idx = executeSeq.size();//getFlowIndex(fid);
        executeSeq.add(suid);
        id2SpanUnit.put(suid, spanUnit);
        index2SpanUnit.put(idx, spanUnit);
        return true;
    }

    public Map<String, SpanUnit> getId2SpanUnit() {
        return id2SpanUnit;
    }

    public void setId2SpanUnit(Map<String, SpanUnit> id2SpanUnit) {
        this.id2SpanUnit = id2SpanUnit;
    }

    public Map<Integer, SpanUnit> getIndex2SpanUnit() {
        return index2SpanUnit;
    }

    public void setIndex2SpanUnit(Map<Integer, SpanUnit> index2SpanUnit) {
        this.index2SpanUnit = index2SpanUnit;
    }

    public List<String> getExecuteSeq() {
        return executeSeq;
    }

    public void setExecuteSeq(List<String> executeSeq) {
        this.executeSeq = executeSeq;
    }
}
