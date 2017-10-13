package com.atjl.dbtiming.test;

import com.atjl.dbtiming.api.ICond;
import com.atjl.dbtiming.api.ITaskExecute;
import org.springframework.stereotype.Component;


@Component("fixratecond")
public class TestRate implements ITaskExecute, ICond {

    private int i = 0;
    private boolean cond = false;

    public void execute() {
        System.out.println("fixrate test task running " + i + ",param ");//+ param);
        i++;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public boolean isCond() {
        return cond;
    }

    public void setCond(boolean cond) {
        this.cond = cond;
    }

    @Override
    public boolean cond() {
        if (i > 10) {
            return false;
        }
        return cond;
    }
}
