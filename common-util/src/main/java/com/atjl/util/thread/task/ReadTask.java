package com.atjl.util.thread.task;

import com.atjl.util.queue.IQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * read data task and write
 */
public class ReadTask extends BaseTask implements Runnable {
    private static Logger LOG = LoggerFactory.getLogger(ReadTask.class);

    private static IQueue queue;


    private static Long srvIp;

    public ReadTask(int id, IQueue queue,Long srvIp){
        super(id);
        this.queue = queue;
        this.srvIp = srvIp;
    }

    @Override
    public void run() {
        while (start) {
            /*
            try {

            } catch (InterruptedException e) {
                LOG.debug("log queue read time out");
            } catch (Exception e){
                e.printStackTrace();//TODO: del
                LOG.error("insert log exception,msg: {}",e.getMessage());
            }*/
        }
    }


}
