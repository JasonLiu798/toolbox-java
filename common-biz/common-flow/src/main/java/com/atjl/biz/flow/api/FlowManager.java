package com.atjl.biz.flow.api;

//import com.sf.inv.dto.flow.FlowResponse;

import com.atjl.biz.flow.cache.SpanCache;
import com.atjl.common.api.resp.ResponseDataDto;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.biz.flow.core.Span;
import com.atjl.biz.flow.core.SpanExecutor;
import com.atjl.biz.flow.core.SpanUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * flow manager
 * TODO: 验证线程安全
 */
@Component
public class FlowManager {
    @Resource
    private SpanCache sequenceCache;
    @Resource
    private SpanExecutor spanExecutor;

    /**
     * @return
     */
    public int registe(String id, List<Flow> snippets) {
        if (StringCheckUtil.isEmpty(id)) {
            return -1;
        }
        if (CollectionUtil.isEmpty(snippets)) {
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
        return sequenceCache.contain(id);
    }

    /**
     * 执行
     *
     * @param reqParam
     * @param globParam
     * @param snippets
     * @return
     */
    public ResponseDataDto execute(Object reqParam, Object globParam, Flow... snippets) {
        if (CollectionUtil.isEmpty(snippets)) {
            return new ResponseDataDto();//error
        }
        String id = SpanUtil.generateId(snippets);
        if (StringCheckUtil.isEmpty(id)) {
            return new ResponseDataDto();//error
        }
        boolean isReg = isRegiste(id);
        if (!isReg) {
            List<Flow> flowList = CollectionUtil.array2List(snippets);
            int regRes = registe(id, flowList);
            if (regRes < 0) {
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

    public Set<String> getSeqs() {
        return sequenceCache.getKeys();
    }

    public String getGraph(String id) {
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
