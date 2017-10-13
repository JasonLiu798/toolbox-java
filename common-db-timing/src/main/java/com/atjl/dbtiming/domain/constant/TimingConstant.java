package com.atjl.dbtiming.domain.constant;

import com.atjl.dbtiming.api.domain.RetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * timing manager constant
 *
 * @author JasonLiu
 */
public class TimingConstant {

    /**
     * 执行线程池
     */
    public static final String POOL_TIMING_EXECUTE = "EXECUTE";

    public static final String TASK_TABLE = "ts_task_v1";//任务表
    public static final String TASK_TABLE_COL_KEY = "TKEY";//任务唯一编号





    // for log
    public static final String MODULE_TIMING = "timing";

    public static final String N = "N";
    public static final String Y = "Y";
    public static final String VALID = "Y";

    public static final Integer PAGE_MAX = Integer.MAX_VALUE;

    public static final String MANAGER_STATUS_STOP = "P";
    public static final String MANAGER_STATUS_STARTING = "I";
    public static final String MANAGER_STATUS_STARTED = "S";

    /**
     * ##################### default value ########################
     */
    /**
     * default task max run time
     */
    public static final Long DFT_MAX_RUN_TIME = 10L;

    public static final long DFT_DELAY = 1000;

    /**
     * monitor
     */
    public static final Long MONITOR_THREAD_ID = -1L;
    public static final String MONITOR_THREAD_KEY = "MONITOR";
    public static final Long HEALTH_THREAD_ID = -2L;
    public static final String HEALTH_THREAD_KEY = "HEALTH";
    public static final Long CLEAR_THREAD_ID = -3L;
    public static final String CLEAR_THREAD_KEY = "CLEAR";
    public static final Long RESET_CNT_THREAD_ID = -4L;
    public static final String RESET_CNT_THREAD_KEY = "RESCNT";

    public static final int SLEEP_RAND_MAX_TM = 3000;
    public static final int SLEEP_DFT_TM = 3000;

    /**
     * clear parameter
     * clear 7day ago
     */
    public static final long CLEAR_DATA_LESS = 7 * 24 * 3600L;

    // thread pool dft size ,three for sys,
    public static final int DFT_POOL_SIZE = 15;
    public static final String POOL_SIZE_PK = "BaseSetting.performance.schedualpoolsize";

    // unique task check time interval default value
    public static final Long UNIQUE_CHECK_INTERVAL = 10L;

    /**
     * monitor task parameters
     */
    public static final long DFT_MONITOR_RATE = 1000;
    public static final long DFT_MONITOR_DELAY = 1000;

    /**
     * health task parameters,3min
     */
    //default heart beat interval
    public static final long DFT_HEART_BEAT = 3 * 60L;


    public static Long NOT_ALIVE_INTERVAL = DFT_HEART_BEAT;
    //according to this parameter,the task will affirm to dead task
    //allow server's time diff 60s,
    public static Long JITTER = 60L;
    public static Long NOT_ALIVE_REF = NOT_ALIVE_INTERVAL + JITTER;

    //dft heart beat 60s
    public static final String DFT_HEART_BEAT_PK = "SysArguSetting.timing.heartbeat";
    //max heart beat interval
    public static final Long MAX_HEART_BEAT = 30 * 60L;
    //min heart beat interval
    public static final Long MIN_HEART_BEAT = 10L;


    /**
     * clear task parameters
     */
    public static final String DFT_CLEAR_INTERVAL_PK = "SysArguSetting.timing.clearinterval";

    // dft clear rate 1day
    public static long DFT_CLEAR_RATE = 3600 * 24;

    /**
     * reset counter parameters
     */
    public static final String DFT_RESET_COUNT_INTERVAL_PK = "SysArguSetting.timing.resetinterval";
    //default reset counter rate 3600s
    public static long DFT_RESET_CNT_RATE = 3600;

    /**
     * reflect methods
     */
    public static final String METHOD_EXECUTE = "execute";
    public static final String METHOD_COND = "cond";

    /**
     * #################### time associate #####################
     */

    /**
     * mutex interval
     */
    public static final Long MUTEX_INTERVAL = 10L;

    public static final Long MIN_INTERVAL = 1000L;

    /**
     * task status
     * Free,Iinitial,Waiting,Executing,enD
     * F->I->W->E->D/W
     */
    public static final String STATUS_FREE = "F";//free,no processor
    public static final String STATUS_INITIAL = "I";//starting
    public static final String STATUS_WAITING = "W";//waiting
    public static final String STATUS_EXECUTING = "E";//executing
    public static final String STATUS_END = "D";//end
    static final Set<String> STATUS_SET = new HashSet<>();
    static final Set<String> CAN_EXE_STATUS_SET = new HashSet<>();

    /**
     * starting
     */
    public static final String COL_MUTEX = "TMUTEX";
    public static final String COL_MUTEX_TM = "MUTEX_TM";

    public static final String COL_STATUS = "TSTATUS";
    public static final String COL_ALIVE_TM = "ALIVE_TM";


    /**
     * #################### task type ########################
     */
    /**
     * cron expression task
     */
    public static final String TP_CRON = "CRON";
    //String.valueOf(TaskEnum.CRON);

    /**
     * specified time and specified interval,execute until condition reach or reach the target num
     * in use
     * delay (interval) -> do -> check yes -> check reach max time no ->delay(interval) ...
     * -> check reach max time yes -> stop
     * -> check no  -> stop
     */
//    public static final String TP_DYN_FIXRATE_CNT_COND_PARAM =
//            "FIXRATECNTCONDPARAM";//String.valueOf(TaskEnum.FIXRATECONDPARAM);

    //public static final String TP_DYN_FIXRATE_CNT_PARAM =
//            "FIXRATECNTPARAM";

    public static final String TP_DYN_FIXRATE_CNT =
            "FIXRATECNT";
    public static final String TP_DYN_FIXRATE_CNT_COND =
            "FIXRATECNTCOND";


    /**
     * now support all type set
     */
    private static final Set<String> TP_SET = new HashSet<>();
    private static final Set<String> TP_DYN_SET = new HashSet<>();

    /**
     * task execute max time,1 hour
     * if over 1 hour,task will be terminate
     */
    public static final Long MAX_EXE_TM = 60 * 60 * 2L;

    private static Set<RetCode> NO_NEED_RETRY_ERROR = new HashSet<>();

    /**
     * init task type
     */
    static {
        TP_SET.add(TP_CRON);
        TP_SET.add(TP_DYN_FIXRATE_CNT);
        TP_SET.add(TP_DYN_FIXRATE_CNT_COND);

        TP_DYN_SET.add(TP_DYN_FIXRATE_CNT_COND);
        TP_DYN_SET.add(TP_DYN_FIXRATE_CNT);

        STATUS_SET.add(STATUS_FREE);
        STATUS_SET.add(STATUS_INITIAL);
        STATUS_SET.add(STATUS_WAITING);
        STATUS_SET.add(STATUS_EXECUTING);
        STATUS_SET.add(STATUS_END);

        CAN_EXE_STATUS_SET.add(STATUS_FREE);
        CAN_EXE_STATUS_SET.add(STATUS_INITIAL);
        CAN_EXE_STATUS_SET.add(STATUS_WAITING);
        CAN_EXE_STATUS_SET.add(STATUS_EXECUTING);


        NO_NEED_RETRY_ERROR.add(RetCode.RUN_CNT_REACH_MAX_CNT);
    }

    /**
     * is valid task type
     *
     * @param tp
     * @return
     */
    public static boolean validTp(String tp) {
        return TP_SET.contains(tp);
    }

    public static boolean validDynTp(String tp) {
        return TP_DYN_SET.contains(tp);
    }

    /**
     * is valid status
     *
     * @param st
     * @return
     */
    public static boolean validStatus(String st) {
        return STATUS_SET.contains(st);
    }

    public static boolean validCanExeStatus(String st) {
        return CAN_EXE_STATUS_SET.contains(st);
    }


    /**
     * specified time and specified interval
     * in use
     *
     * 1.unit is year,month
     * use scheduleWithFixedDelay,calc next exe time ,reput to pool
     *
     * 2.unit is day,week,minute,second
     * use scheduleAtFixedRate,
     * delay (tgtTm - now)-> do (check 次数->stop) -> delay(tgtTm - now) -> do ....
     *
     public static final String TP_TM_INTERVAL = "TV";*/

    /**
     * specified time run once
     *
     public static final String TP_TM = "T";*/

    /**
     * fix rate
     *
     public static final String TP_INTERVAL= "V";*/
//    public static final String TP_DELAY_INTERVAL = "DV";
//    public static final String TP_DELAY_INTERVAL_CNT = "DVN";

}
