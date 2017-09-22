package com.atjl.kafka.consumer;


import com.atjl.kafka.api.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * MessageHandler
 *
 * @since 1.0
 */
@SuppressWarnings("unused")
public class MessageHandlerTest implements TopicHandler {

    private static Logger LOG = LoggerFactory.getLogger(MessageHandlerTest.class);
//    public static MultiThreadFileWriterHelper writer = new MultiThreadFileWriterHelper(true);
    private static String targetPath = "D:\\cdata.log";

    @Override
    public void execute(List<Event> da) {
        Thread t = Thread.currentThread();
        long id = t.getId();
        //String ws = json +","+System.currentTimeMillis();
        //writer.write(targetPath,ws);
        //FileHelper.writeOneLine2File(targetPath, s+","+System.currentTimeMillis());
        //LOG.debug( "thread id {},get list {}", id, ws);
        /*
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
