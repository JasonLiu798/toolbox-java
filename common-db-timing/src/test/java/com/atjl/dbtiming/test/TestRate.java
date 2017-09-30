package com.atjl.dbtiming.test;

import com.atjl.dbtiming.api.ICond;
import com.atjl.dbtiming.api.ITimingTaskParam;
import org.springframework.stereotype.Component;
//import sf.aos.constant.TaskConstant;


@Component("fixratecond")
public class TestRate implements ITimingTaskParam,ICond {

    private int i=0;
    private boolean cond = false;

    @Override
    public void execute(String param) {
        System.out.println("fixrate test task running "+i+",param "+param);
        i++;
    }

    @Override
    public boolean cond(String param) {
        if(i>10){
            return false;
        }
        return cond;
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



}
