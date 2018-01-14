package listener;


import com.atjl.observer.api.Listener;
import com.atjl.util.json.JSONFastJsonUtil;
import event.EventA;
import org.springframework.stereotype.Component;

@Component
public class TestListner extends Listener<EventA> {

    @Override
    public void event(EventA event) {
        System.out.println("TestListner recv " + JSONFastJsonUtil.objectToJson(event));
    }
}
