package com.atjl.observer.api;

import java.util.List;

public interface EventManager {
    List<ListenerStatus> getListenerStatus();

    void register(Listener<?>... listenerArg);

    void unregister(Listener<?>... listenerArg);

    <T> void fire(T object);

}
