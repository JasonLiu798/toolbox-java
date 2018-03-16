package com.atjl.biz.flow.core;


import com.atjl.biz.flow.api.Flow;
import com.atjl.common.api.resp.ResponseDataDto;
import com.atjl.biz.flow.api.FlowRequest;

import java.lang.reflect.Method;
import java.util.Map;

//import com.sf.inv.dto.flow.FlowResponse;

/**
 * span unit
 * package of snippet
 * 对Snippet的封装
 */
public class SpanUnit {
    private Method actionMethod;
    private String suid;
    private int option;
    private Flow snippet;

    public SpanUnit() {
    }


    public void setRequest(FlowRequest req) {
        snippet.setResquest(req);
    }

    public Map<String, Object> getDataContextAll() {
        return snippet.getContextDataAll();
    }

    public void setDataContextAll(Map<String, Object> data) {
        snippet.setContextDataAll(data);
    }


    public void setGlobal(Object param) {
        snippet.setGlobal(param);
    }


    public ResponseDataDto getResponse() {
        return snippet.getResponse();
    }

    /**
     * ################ option checkers #####################
     */
    public boolean isEnableErrorCodeContinue() {
        return FlowOptionGen.isEnableErrorCodeContinue(this.option);
    }

    public boolean isEnableExceptionContinue() {
        return FlowOptionGen.isEnableExceptionContinue(this.option);
    }

    public boolean isEnableChangeNextFlowId() {
        return FlowOptionGen.isEnableChangeNextFlowId(this.option);
    }

    /**
     * ################ setters & getters #####################
     */
    public int getOption() {
        return option;
    }

    public SpanUnit setOption(int option) {
        this.option = option;
        return this;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public void invoke() throws Exception {
        actionMethod.setAccessible(true);
        actionMethod.invoke(snippet);
    }

    public SpanUnit setActionMethod(Method actionMethod) {
        this.actionMethod = actionMethod;
        return this;
    }

    public String getSuid() {
        return suid;
    }

    public SpanUnit setSuid(String suid) {
        this.suid = suid;
        return this;
    }

    public Flow getSnippet() {
        return snippet;
    }

    public SpanUnit setSnippet(Flow snippet) {
        this.snippet = snippet;
        return this;
    }

    @Override
    public String toString() {
        return "SpanUnit{" +
                "actionMethod=" + actionMethod +
                ", suid='" + suid + '\'' +
                ", option=" + option +
                ", snippet=" + snippet +
                '}';
    }
}
