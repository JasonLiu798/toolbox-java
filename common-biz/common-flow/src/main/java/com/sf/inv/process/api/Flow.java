package com.sf.inv.process.api;

import com.sf.inv.dto.common.ResponseDataDto;
//import com.sf.inv.dto.flow.FlowResponse;
import com.sf.inv.log.LogContext;
import com.sf.inv.process.dto.FlowConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * for extend
 */
public abstract class Flow<P,R> {
    protected ThreadLocal<Map<String, Object>> contextData = new ThreadLocal() {
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    public Flow() {
    }

    /**
     *
     */
    public String getId(){
        //LogContext.debug( "clz name "+this.getClass().getCanonicalName());
        //LogContext.debug( "clz name "+this.getClass().getSimpleName());
        //LogContext.debug( "clz name simple "+this.getClass().getName());
        return this.getClass().getName();
    }

    //protected String fid;

    public abstract void action() throws Exception;

    public <T> T getContextData(String key, Class<T> clz) {
        T res = null;
        try {
            res = clz.cast(contextData.get().get(key));
        } catch (Exception e) {
            LogContext.debug("context data get or cast error,key {},clz {}", e, key, clz);
        }
        return res;
    }

    public Map<String,Object> getAllDataContext(){
        return contextData.get();
    }

    public void setContextData(String key, Object data) {
        Map<String, Object> map = contextData.get();
        map.put(key, data);
    }

    public Object getContextData(String key) {
        return contextData.get().get(key);
    }


    public Map<String,Object> getContextDataAll() {
        return contextData.get();
    }

    public void setContextDataAll(Map<String,Object>  map) {
        contextData.set(map);
    }

    public <P> FlowRequest<P> getRequest() {
        return getContextData(FlowConstant.KEY_REQUEST,FlowRequest.class);
    }

    public <P> P getRequestParam() {
        return (P) getContextData(FlowConstant.KEY_REQUEST,FlowRequest.class).getParam();
    }

    public void setResquest(FlowRequest req) {
        setContextData(FlowConstant.KEY_REQUEST, req);
    }

    public <R> void setResponse(ResponseDataDto<R> resp) {
        setContextData(FlowConstant.KEY_RESPONSE, resp);
    }

    public <R> void setSuccessWithData(R respData) {
        ResponseDataDto resp = new ResponseDataDto(respData);
        setContextData(FlowConstant.KEY_RESPONSE, resp);
    }

    public void setSuccess() {
        ResponseDataDto resp = new ResponseDataDto();
        setContextData(FlowConstant.KEY_RESPONSE, resp);
    }

    public void setFail(int code, String msg) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setCode(code);
        resp.setMsg(msg);
        setContextData(FlowConstant.KEY_RESPONSE, resp);
    }


    public ResponseDataDto getResponse() {
        return (ResponseDataDto) getContextData(FlowConstant.KEY_RESPONSE);
    }

    public Object getGlobal() {
        return getContextData(FlowConstant.KEY_GLOBAL);
    }

    public <T> T getGlobal(Class<T> clz) {
        return getContextData(FlowConstant.KEY_GLOBAL,clz);
    }

    public void setGlobal(Object obj) {
        setContextData(FlowConstant.KEY_GLOBAL, obj);
    }

    public void setNextFlow(String fid) {
        setContextData(FlowConstant.KEY_NEXT, fid);
    }

    public String getNextFlow(String fid) {
        return String.valueOf(getContextData(FlowConstant.KEY_NEXT));
    }


}
