package com.atjl.dbtiming.manager;

import com.atjl.common.api.resp.ResponseDataDto;
import com.atjl.dbtiming.api.req.DbTaskParam;
import com.atjl.dbtiming.api.req.DynamicTaskParam;
import com.atjl.dbtiming.api.req.TaskConf;
import com.atjl.dbtiming.core.WaitDispatchQueue;
import com.atjl.dbtiming.domain.BaseTimingTask;
import com.atjl.dbtiming.domain.biz.QueueWaitTask;
import com.atjl.dbtiming.domain.form.DbTaskForm;
import com.atjl.dbtiming.domain.form.NewTaskForm;
import com.atjl.dbtiming.domain.gen.GenTask;
import com.atjl.dbtiming.helper.TimingDbHelper;
import com.atjl.dbtiming.util.TaskTransferUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * task 入 dispatch队列
 */
@Component
public class TimingAddManager {

    @Resource
    private TimingDbHelper timingDbHelper;

    /**
     * 动态新增
     */
    public ResponseDataDto add(DynamicTaskParam p) {
        //参数检查
        ValidateForm vf = ValidateFormFactory.build(NewTaskForm.class);
        vf.setValue(p);
        if (!vf.validate()) {
            return ResponseDataDto.buildFail(1000, vf.getOneLineError());
        }

        //转换
        BaseTimingTask t = TaskTransferUtil.param2task(p);
        if (t == null) {
            return ResponseDataDto.buildFail(1002, "转换失败");
        }

        //入库
        Long tid = timingDbHelper.addTask(p);
        if (tid == null) {
            return ResponseDataDto.buildFail(1001, "写库失败");
        }
        t.setTid(tid);

        add(t);
        return ResponseDataDto.buildOk();
    }

    /**
     * db获取后新增
     */
    public ResponseDataDto add(Long tid) {
        GenTask dbt = timingDbHelper.getTask(tid);
        if (dbt == null) {
            return ResponseDataDto.buildFail(1005, "DB任务不存在");
        }
        //gen 2 param
        DbTaskParam p = new DbTaskParam();
        try {
            p.setTaskConf(JSONFastJsonUtil.jsonToObject(dbt.getDatas(), TaskConf.class));
        } catch (Exception e) {
            return ResponseDataDto.buildFail(1006, "data参数转json失败", e);
        }
        p.setTkey(dbt.getTkey());
        p.setTid(dbt.getTid());

        ValidateForm vf = ValidateFormFactory.build(DbTaskForm.class);
        vf.setValue(p);
        if (!vf.validate()) {
            return ResponseDataDto.buildFail(1000, vf.getOneLineError());
        }

        BaseTimingTask t = TaskTransferUtil.param2task(p);
        if (t == null) {
            return ResponseDataDto.buildFail(1007, "db任务转换失败");
        }
        t.setTid(tid);
        add(t);
        return ResponseDataDto.buildOk();
    }

    /**
     * 添加到调度队列
     */
    public ResponseDataDto add(BaseTimingTask t) {
        long nextExecuteTm = t.getNextExecuteTime();
        //入调度队列
        QueueWaitTask task = new QueueWaitTask(nextExecuteTm, t.getTid(), t);
        WaitDispatchQueue.getInstence().put(task);
        return ResponseDataDto.buildOk();
    }


//    private void add(BaseTimingTask t){
//        long nextExecuteTm = t.getNextExecuteTime();
    //入调度队列
//        QueueWaitTask task = new QueueWaitTask(nextExecuteTm, t.getTid(), t);
//    }
}
