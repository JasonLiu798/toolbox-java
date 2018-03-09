package com.sf.inv.process.core;

import com.sf.inv.dto.common.ResponseDataDto;
import com.sf.inv.dto.errorcode.CommonOuterError;
import com.sf.inv.dto.errorcode.CommonOuterErrorCode;
import com.sf.inv.process.api.FlowRequest;
import com.sf.inv.log.LogContext;
import com.sf.inv.process.dto.FlowConstant;
import com.sf.inv.util.CollectionHelper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * span 执行
 */
@Component
public class SpanExecutor {

    /**
     * no init parameter
     *
     * @return public <R> FlowResponse<R> process(List<String> executeSeq) {
     * return process(null, executeSeq);
     * }
     */
    public <P, T> ResponseDataDto process(Span span, P initParam, T globalParam) {
        List<String> seq = span.getExecuteSeq();
        if (CollectionHelper.isEmpty(seq)) {
            return null;
        }
        LogContext.debug("execute seq {}", seq);
        ResponseDataDto finalResp = null;
        FlowRequest nextParam = new FlowRequest(initParam);
        int i = 0;

        boolean goon = true;//初始化goon

        Map<String, SpanUnit> id2SpanUnit = span.getId2SpanUnit();
        T initGlobalParam = globalParam;
        Map<String,Object> preDataContext = new HashMap();
        boolean first = true;
        while (goon && i < seq.size()) {
            goon = true;//默认继续运行
            String curFlowId = seq.get(i);
            i++;
            if (!id2SpanUnit.containsKey(curFlowId)) {
                break;
            }
            SpanUnit spanUnit = id2SpanUnit.get(curFlowId);
            //flow.setContextData(this.contextData);//flow共享
            LogContext.debug("execute span unit {}", spanUnit);

            //set request
            preDataContext.put(FlowConstant.KEY_REQUEST, nextParam);
            if (globalParam != null) {
                if (first) {
                    preDataContext.put(FlowConstant.KEY_GLOBAL, initGlobalParam);
                    first = false;
                }
                //spanUnit.setGlobal(globalParam);
            }
            spanUnit.setDataContextAll(preDataContext);
            //set request
            //spanUnit.setRequest(nextParam);


            boolean exceptionOccur = false;
            Exception excep = null;
            try {
                spanUnit.invoke();
            } catch (Exception e) {
                excep = e;
                exceptionOccur = true;
                LogContext.debug("flow invoke exception", e);
            }

            finalResp = null;//resp;
            ResponseDataDto resp = spanUnit.getResponse();
            LogContext.debug("span unit {} response {}", spanUnit.getSuid(), resp);

            if (resp != null) {
                goon = resp.getCode() == 0 ? true : false;
                //check error occur continue 判断出现错误码是否继续
                if (spanUnit.isEnableErrorCodeContinue()) {
                    goon = true;
                }
                if (resp != null) {
                    nextParam = new FlowRequest<>(resp.getData());
                    finalResp = resp;
                } else {
                    LogContext.debug("####response null");
                }
            } else {
                //exception occur; flow not set response
                nextParam = null;
            }

            //check exception occur 判断异常是否发生
            if (exceptionOccur) {
                goon = false;
                finalResp = new ResponseDataDto(CommonOuterErrorCode.FLOW_INVOKE_ERROR, CommonOuterErrorCode.FLOW_INVOKE_ERROR_MSG, excep);
            }
            //check exception occur continue 判断异常发生是否继续选项
            if (spanUnit.isEnableExceptionContinue()) {
                goon = true;
            }
            /**
             * 全部塞入下一个 spanUnit,除了response，和nextflowid
             * 剩下：request(将被覆盖）; globalParam; 用户自定义data
             */
            preDataContext = spanUnit.getDataContextAll();
            preDataContext.remove(FlowConstant.KEY_RESPONSE);
            preDataContext.remove(FlowConstant.KEY_NEXT);
        }
        return finalResp;
    }


    /**
     * get flow type
     *
     * @return public int getFlowType(Flow flow) {
     * //Class flowClz = flow.getClass();
     * //注：判断顺序不可改，依次为父类
     * if (flow instanceof FlowFunc) {
     * return FlowConstant.TP_FLOW_FUNC;
     * //        } else if (flow instanceof FlowProc) {
     * //            return FlowConstant.TP_FLOW_PROC;
     * } else if (flow instanceof Flow) {
     * return FlowConstant.TP_FLOW;
     * }
     * return FlowConstant.TP_UNKNOWN;
     * }
     */
    public String getFlowExecuteGraph(Span span) {
        if (span == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        List<String> seq = span.getExecuteSeq();
        if (CollectionHelper.isEmpty(seq)) {
            return "";
        }
        int lastIdx = seq.size() - 1;
        for (int i = 0; i < seq.size(); i++) {
//            String spanUnitId = ;//seq.get(i);
            String spanUnitId = seq.get(i);
            SpanUnit spanUnit = span.getId2SpanUnit().get(spanUnitId);
            if (spanUnit != null) {
                sb.append("flow[fid:").append(spanUnitId);
                sb.append(",op:");
                //spanUnit.isEnableErrorCodeContinue();
                String fmtOp = FlowOptionGen.fmtOption(spanUnit.getOption());
                sb.append(fmtOp);
            }
            sb.append("]");
            if (i != lastIdx) {
                sb.append("->");
            }
        }
        return sb.toString();
    }

}
