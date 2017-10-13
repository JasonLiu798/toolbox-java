package com.atjl.dbtiming.manager;

import com.atjl.dbtiming.domain.BaseTimingTask;
import com.atjl.dbtiming.api.req.TaskConf;
import com.atjl.dbtiming.domain.constant.TimingConstant;
import com.atjl.util.common.CovertUtil;
import com.atjl.util.reflect.ReflectMethodUtil;
import org.springframework.stereotype.Component;

@Component
public class TimingExecuteManager {

    public void execute() {
    }

    public boolean reAdd(BaseTimingTask timingTask) {
        TaskConf tc = timingTask.getTaskConf();
        //终止条件处理
        if (tc.isHasCond()) {
            Object resRaw = ReflectMethodUtil.invokeMethod(timingTask.getServiceBean(), TimingConstant.METHOD_COND, null, null);
            Boolean res = CovertUtil.covertObj(resRaw, Boolean.class);
            if (res == null) {
                //invoke fail
                return false;
            }
            if (!res) {
                return false;
            }
        }
        //最大次数处理
        if (tc.getMaxRunCnt() > 0) {
            if (tc.getHasRunCnt() >= tc.getMaxRunCnt()) {
                return false;
            }
        }
        return true;
    }
}
