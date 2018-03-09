package com.sf.inv.process.api;

import com.sf.inv.dto.common.ResponseDataDto;
//import com.sf.inv.dto.flow.FlowResponse;
import com.sf.inv.process.cache.SpanCache;
import com.sf.inv.process.core.Span;
import com.sf.inv.process.core.SpanExecutor;
import com.sf.inv.process.core.SpanUtil;
import com.sf.inv.util.CollectionHelper;
import com.sf.inv.util.StringCheckUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * flow manager
 * TODO: 验证线程安全
 * com.sf.inv.process
 */
@Component
public class FlowManager {
    @Resource
    SpanCache sequenceCache;
    @Resource
    SpanExecutor spanExecutor;

    /**
     * @return
     */
    public int registe(String id, List<Flow> snippets) {
        if (StringCheckUtil.isEmpty(id)) {
            return -1;
        }
        if (CollectionHelper.isEmpty(snippets)) {
            return -2;
        }
        //String spanId = SpanUtil.generateId(snippets);
        Span span = new Span();
        for (Flow snippet : snippets) {
            span.addSnippet(snippet);
        }
        sequenceCache.put(id, span);
        return 0;
    }

    public boolean isRegiste(String id) {
        return sequenceCache.exist(id);
    }

    /**
     * 执行
     * @param reqParam
     * @param globParam
     * @param snippets
     * @return
     */
    public ResponseDataDto execute(Object reqParam, Object globParam, Flow... snippets) {
        if (CollectionHelper.isEmpty(snippets)) {
            return new ResponseDataDto();//error
        }
        String id = SpanUtil.generateId(snippets);
        if (StringCheckUtil.isEmpty(id)) {
            return new ResponseDataDto();//error
        }
        boolean isReg = isRegiste(id);
        if (!isReg) {
            List<Flow> flowList = CollectionHelper.array2List(snippets);
            int regRes = registe(id,flowList);
            if(regRes<0){
                return null;//error
            }
        }
        //FlowRequest req = new FlowRequest(reqParam);
        return execute(id, reqParam, globParam);
    }

    public ResponseDataDto execute(String id, Object req, Object globParam) {
        Span span = sequenceCache.get(id);
        return spanExecutor.process(span, req, globParam);
    }

    public Set<String> getSeqs(){
        return sequenceCache.getKeys();
    }

    public String getGraph(String id){
        Span span = sequenceCache.get(id);
        return spanExecutor.getFlowExecuteGraph(span);
    }
    public SpanCache getSequenceCache() {
        return sequenceCache;
    }

    public void setSequenceCache(SpanCache sequenceCache) {
        this.sequenceCache = sequenceCache;
    }

    public SpanExecutor getSpanExecutor() {
        return spanExecutor;
    }

    public void setSpanExecutor(SpanExecutor spanExecutor) {
        this.spanExecutor = spanExecutor;
    }
}
