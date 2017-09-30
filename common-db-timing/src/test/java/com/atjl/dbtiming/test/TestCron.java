package com.atjl.dbtiming.test;

import com.atjl.dbtiming.api.ITimingTask;
import org.springframework.stereotype.Component;

@Component("testCron")
public class TestCron implements ITimingTask {

    private int i=0;

    @Override
    public void execute() {
        System.out.println("test task running "+i);
        i++;
    }

}
