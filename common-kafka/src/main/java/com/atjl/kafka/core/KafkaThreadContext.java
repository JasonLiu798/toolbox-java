package com.atjl.kafka.core;

import com.atjl.kafka.core.thread.BaseThread;
import com.atjl.util.common.CheckUtil;
import com.atjl.kafka.core.thread.FetchDataThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 存储 线程 上下文
 * @author jasonliu
 * @since 1.0
 */
public class KafkaThreadContext {
    private static Logger LOG = LoggerFactory.getLogger(KafkaThreadContext.class);

    private static final Map<String,List<BaseThread>> threadContex= new HashMap<>();

    public static final String FETCH_THREAD = "FETCH";
    public static final  String PROCESS_THREAD = "PROCESS";
    public static final String TIMING_THREAD = "TIMING";

    /**
     * 仅用于初始化
     * @param runnable
     */
    public static void addThread(String type,BaseThread runnable){
        List<BaseThread> list = threadContex.get(type);
        if(list==null){
            list = new LinkedList<>();
            threadContex.put(type,list);
        }
        list.add(runnable);
        if(LOG.isDebugEnabled()){
        	LOG.debug("thread {} added ",runnable);
        }
    }

    public static List<BaseThread> getThreadList(String type){
        return threadContex.get(type);
    }

    public static List<FetchDataThread> getFetchDataThreadList(){
        List<BaseThread> baseList = threadContex.get(FETCH_THREAD);
        CheckUtil.checkExistNull( baseList);
        List<FetchDataThread> res = new LinkedList<>();
        for(BaseThread bt:baseList){
            res.add((FetchDataThread)bt);
        }
        return res;
    }

}
