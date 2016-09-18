package com.jason798.number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * series helper
 */
public class RangeHelper {

    private static final Logger LOG = LoggerFactory.getLogger(RangeHelper.class);

    private static final int DFT_DIFF = 1;
    /**
     * T must be integer,long,
     * @param list increase
     * @return
     */
    public static List<Long> getDiff(List<Long> list){
        //if(T instanceof )

        return null;
    }





    public static List<Integer> range(int end){
        return range(0,end,1);
    }

    /**
     * python range
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> range(int start,int end){
        return range(start,end,1);
    }

    /**
     * python range
     * @param start
     * @param end
     * @param interval
     * @return
     */
    public static List<Integer> range(int start,int end,int interval){
        if(end<start ){
            return null;
        }
        if(interval<=0){
            interval = 1;
        }
        int len = end-start;
        int add = Math.min(Math.max(len%interval,0),1);
        int size = len/interval+add;

        if(LOG.isDebugEnabled())
            LOG.debug("range size :{}, / {}, % {}",size,len/interval,add);

        List<Integer> list = new ArrayList<>(size);
        for(int i = start;i<end;i+=interval){
            list.add(i);
        }
        return list;
    }
}
