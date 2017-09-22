package com.atjl.kafka.core.thread;

import com.atjl.util.queue.IQueue;
import com.atjl.kafka.api.event.BatchEvent;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.core.KafkaConsumeContext;

/**
 * MockSender
 * @since 1.0
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class MockSender  implements Runnable{
    
	private IQueue queue;
    @SuppressWarnings("unused")
	private KafkaConsumeContext context;

    public MockSender(IQueue queue, KafkaConsumeContext context){
        this.queue = queue;
        this.context = context;
    }

    public MockSender(IQueue queue){
        this.queue = queue;
        this.context = null;
    }

	@Override
    public void run() {
        int i = 0;
        while (true) {
            BatchEvent brd = new BatchEvent();
            for (int j = i; j < i + 10; j++) {
                Event rd = new Event();
                String value = String.valueOf(j);
                rd.setValue(value);
                rd.setOffset(j);
                brd.addEvent2Partition(1, rd);
            }
            i += 10;
            try {
                queue.sendMessage(brd);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
