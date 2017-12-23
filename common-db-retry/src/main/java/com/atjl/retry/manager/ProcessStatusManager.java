package com.atjl.retry.manager;

import com.atjl.common.constant.CommonConstant;
import com.atjl.jfeild.util.JFieldUtil;
import com.atjl.retry.api.domain.ExecuteStatusResp;
import com.atjl.retry.api.exception.RetryExecuteException;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.domain.ProcessLogBiz;
import com.atjl.retry.domain.gen.TsProcessLog;
import com.atjl.retry.domain.gen.TsProcessLogDetail;
import com.atjl.retry.domain.gen.TsProcessLogExample;
import com.atjl.retry.mapper.gen.TsProcessLogDetailMapper;
import com.atjl.retry.mapper.gen.TsProcessLogMapper;
import com.atjl.retry.util.ProcessLogMetaUtil;
import com.atjl.util.common.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class ProcessStatusManager {
    private static final Logger logger = LoggerFactory.getLogger(ProcessStatusManager.class);

    @Resource
    private TsProcessLogMapper tsProcessLogMapper;
    @Resource
    private TsProcessLogDetailMapper tsProcessLogDetailMapper;

    public void repeatExecuteCheck(PageOption option) {
        if (!option.isCheckRepeatExecute()) {
            return;
        }
        int interval = option.getCheckRepeatExecuteInterval();
        if (interval <= 0) {
            interval = 10;
        }
        Date d = DateUtil.ts2Date(DateUtil.getNowTS() - interval);

        TsProcessLogExample param = new TsProcessLogExample();
        param.createCriteria().andServiceKeyEqualTo(option.getServiceName()).andCrtTmBetween(d, new Date());
        int cnt = tsProcessLogMapper.countByExample(param);
        if (cnt > 0) {
            throw new RetryExecuteException(interval + "秒内，存在已经执行的服务" + option.getServiceName() + "，共" + cnt + "条");
        }
    }


    public Long addStatus(PageOption opt, boolean generalPreServiceResult) {
        ProcessLogBiz log = new ProcessLogBiz();
        log.setServiceKey(opt.getServiceName());
        log.setPreServiceResult(generalPreServiceResult);
        if (!generalPreServiceResult) {
            if (!opt.isGeneralPreServiceFailContinue()) {
                log.setProcessEnd(CommonConstant.YES);
            }
        }
        TsProcessLog dlog = JFieldUtil.update(log, ProcessLogMetaUtil.getBizTabMeta(), TsProcessLog.class);
        tsProcessLogMapper.insertSelective(dlog);
        return dlog.getDataProcessId();
    }

    public void updateByPk(ProcessLogBiz log) {
        try {
            TsProcessLog dlog = JFieldUtil.update(log, ProcessLogMetaUtil.getBizTabMeta(), TsProcessLog.class);
            tsProcessLogMapper.updateByPrimaryKeySelective(dlog);
        } catch (Exception e) {
            logger.debug("add log fail {}", e);
        }
    }

    public void addLogDetail(ExecuteStatusResp resp) {
        TsProcessLogDetail log = JFieldUtil.update(resp, ProcessLogMetaUtil.getDetailTabMeta(), TsProcessLogDetail.class);
        if (log == null) {
            return;
        }
        tsProcessLogDetailMapper.insertSelective(log);
    }
}
