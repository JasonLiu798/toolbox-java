package event;


import com.atjl.observer.api.Event;

public class EventA extends Event {
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
