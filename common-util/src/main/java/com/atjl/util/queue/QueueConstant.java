package com.atjl.util.queue;


public class QueueConstant {

    public static final int CONF_DFT_QUEUE_SIZE = 100000;
    public static final String DFT_QUEUE_CONFIG_FILE = "sysconfig.properties";
    public static final String CONF_QUEUE_KEY = "queue";
    public static final String QUEUE_SEP_KEY = "queuesep";
    public static final String QUEUE_SEP_DFT = ",";
    public static final String QUEUE_SIZE_SEP_KEY = "queuesizesep";
    public static final String QUEUE_SIZE_SEP_DFT = ":";



    private QueueConstant(){
        throw new UnsupportedOperationException();
    }
}
