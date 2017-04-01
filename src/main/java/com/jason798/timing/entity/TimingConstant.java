package com.jason798.timing.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * timing manager constant
 *
 * @author JasonLiu
 */
public class TimingConstant {

    public static final String MONITOR_THREAD_ID = "MONITOR";
    /**
     * thread pool dft size
     */
    public static final int DFT_POOL_SIZE = 10;
    public static final String POOL_SIZE_PK = "BaseSetting.performance.schedualpoolsize";

    /**
     * monitor interval (ms)
     */
    public static final long DFT_MONITOR_RATE = 1000;
    public static final long DFT_MONITOR_DELAY = 1000;

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
     */
    public static final String TP_TM_INTERVAL = "TV";

    /**
     * specified time and specified interval,execute until condition reach or reach the target num
     * in use
     * delay (interval) -> do -> check yes -> check reach max time no ->delay(interval) ...
     *                                     -> check reach max time yes -> stop
     *                        -> check no  -> stop
     */
    public static final String TP_COND_INTERVAL = "CV";

    /**
     *
     */
    public static final Set<String> TP_SET =new HashSet<>();




    /**
     * task execute max time,1 hour
     * if over 1 hour,task will be terminate
     */
    public static final Long MAX_EXE_TM = 60*60*2L;

    /**
     * init task type
     */
    static {
        TP_SET.add(TP_TM_INTERVAL);
        TP_SET.add(TP_COND_INTERVAL);

    }


    /**
     * specified time run once
     */
    public static final String TP_TM = "T";


    public static boolean validTp(String tp){
        return TP_SET.contains(tp);
    }


    /**
     * fix rate
     */
    public static final String TP_INTERVAL= "V";
    /**
     */
    public static final String TP_DELAY_INTERVAL = "DV";
    /**
     */
    public static final String TP_DELAY_INTERVAL_CNT = "DVN";




}
