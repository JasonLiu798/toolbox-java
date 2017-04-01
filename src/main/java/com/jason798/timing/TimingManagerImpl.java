package com.jason798.timing;

import com.jason798.timing.api.TimingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Resource;

/**
 * timing mansger
 * <p>
 * delay(IService,tgtTime);
 * 1.指定时间运行 业务
 * 2.提交延迟执行业务
 * 3.提交 指定间隔 循环运行业务
 */
@Component
public class TimingManagerImpl implements TimingManager {

    private static Logger LOG = LoggerFactory.getLogger(TimingManagerImpl.class);

    @Resource
    private TimingCommonHelper timingCommonHelper;
    @Resource
    private TimingTaskHelper timingTaskHelper;

    /**
     * 唯一编号
     */
    private String managerId;

    public TimingManagerImpl(){}

    public void init() {
        String managerId = timingCommonHelper.registe();
        this.managerId = managerId;
    }

    /**
     * 测试用 静态方法 方便调用
     *
    public static ScheduledFuture fixRate4test(FixRateTask task, long dealy, long interval) {
        TimingManager timingManager = null;
        try {
            timingManager = ApplicationContextHepler.getBean("timingManager", TimingManager.class);
            return timingManager.fixRate(task, dealy, interval);
        } catch (Exception e) {
            LogClient.writeError(TimingManager.class, "schedule fix rate static error ", e);
            return null;
        }
    }
    */

    /**
     * 测试用 静态方法 方便调用
     *
    public static void delay4test(DelayTask runnable, long ms) {
        TimingManager timingManager = null;
        try {
            timingManager = ApplicationContextHepler.getBean("timingManager", TimingManager.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        timingManager.delay(runnable, ms);
    }
    */


    /**
     * 测试用 生成 实例
     *
     * @return
     *
    public static TimingManager build4test() {
        TimingManager tm = new TimingManager();
        tm.init();
        return tm;
    }*/

}
