package com.atjl.observer.core;

import com.atjl.common.api.LifeCycle;
import com.atjl.observer.api.EventManager;
import com.atjl.observer.api.Listener;
import com.atjl.observer.api.ListenerStatus;
import com.atjl.util.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * 事件管理，观察者模式实现
 */
@Component
public class EventManagerImpl implements EventManager, LifeCycle {
    private static final Logger logger = LoggerFactory.getLogger(EventManagerImpl.class);

    /**
     * Whether pushing events is allowed. If this is <tt>false</tt>, then no
     * events can be pushed through the system, and the event currently being
     * fired will be immediately terminated.
     */
    private static volatile AtomicBoolean allowPush = new AtomicBoolean(true);
//    private static final Queue<Listener<?>> listeners = new ConcurrentLinkedQueue<>();

//    /**
//     * Stores times for event firing. Used for profiling.
//     */
//    private static final Map<Class<?>, Long> eventFireTimes = new ConcurrentHashMap<>();

    /**
     * listener map
     */
    private static final Map<String, Listener> listenerMap = new ConcurrentHashMap<>();

    private static final Map<Class, Set<Listener>> eventListenerMap = new ConcurrentHashMap<>();


    @Override
    public void init() {
        //nothing init
    }

    @Override
    public void destroy() {
        listenerMap.clear();
    }

    public List<ListenerStatus> getListenerStatus() {
        if (CollectionUtil.isEmpty(listenerMap)) {
            return new ArrayList<>();
        }
        List<ListenerStatus> res = new ArrayList<>();
        for (Map.Entry<String, Listener> entry : listenerMap.entrySet()) {
            ListenerStatus listenerStatus = new ListenerStatus();
            Listener l = entry.getValue();
            listenerStatus.setClassName(l.getClass().getName());
            listenerStatus.setEvent(l.getType().getName());
            listenerStatus.setFireCount(l.getFireCount());
            listenerStatus.setLastCost(l.getLastCost());
            res.add(listenerStatus);
        }
        return res;
    }

    /**
     * Registers an arbitrary number of Listeners.
     *
     * @param listenerArg A vararg of <tt>Listeners</tt> to be registered.
     */
    public synchronized void register(Listener<?>... listenerArg) {
        for (Listener<?> listener : listenerArg) {
            if (listenerMap.containsKey(String.valueOf(listener.getClass()))) {
                continue;
            } else {
                listenerMap.put(String.valueOf(listener.getClass()), listener);
                Set<Listener> listenerSet = eventListenerMap.get(listener.getType());
                if (CollectionUtil.isEmpty(listenerSet)) {
                    listenerSet = new HashSet<>();
                }
                listenerSet.add(listener);
                eventListenerMap.put(listener.getType(), listenerSet);
            }
        }
    }

    /**
     * Unregisters an arbitrary number of Listeners.
     *
     * @param listenerArg A vararg of <tt>Listeners</tt> to be unregistered.
     */
    public synchronized void unregister(Listener<?>... listenerArg) {
        for (Listener<?> listener : listenerArg) {
            listenerMap.remove(listener.getClass().getName());
            Set<Listener> listenerSet = eventListenerMap.get(listener.getType());
            if (!CollectionUtil.isEmpty(listenerSet)) {
                Iterator<Listener> it = listenerSet.iterator();
                while (it.hasNext()) {
                    Listener l = it.next();
                    if (l.getClass() == listener.getClass()) {
                        it.remove();
                    }
                }
            }
        }
    }

    /**
     * event occure
     *
     * @param object The event to fire.
     * @param <T>    The type of the event. Used for casting internally.
     * @return The event that was pushed.
     */
    public synchronized <T> void fire(T object) {
        if (!allowPush.get()) {
            logger.info("event fire,not allow {}", object.getClass());
            return;
        }

        Set<Listener> listenerSet = eventListenerMap.get(object.getClass());
        if (!CollectionUtil.isEmpty(listenerSet)) {
            for (Listener e : listenerSet) {
                long start = System.currentTimeMillis();
                logger.debug("fire event {}", object.getClass().getSimpleName());
                e.event(object);
                long end = System.currentTimeMillis();
                e.after(end - start);
            }
        }
    }

    /**
     * Unregisters all registered listeners. Note that this exeMethod will also
     * cause events to no longer be pushed until changed with
     * {@link #setPushState(boolean)}.
     */
    public static synchronized void unregisterAll() {
        listenerMap.clear();
        eventListenerMap.clear();
    }

    /**
     * Sets whether or not events can be pushed.
     *
     * @param b The new state to set
     * @return The newly set state
     */
    public static synchronized boolean setPushState(boolean b) {
        allowPush.set(b);
        return allowPush.get();
    }

    /**
     * Returns the current event fire state
     *
     * @return The current event fire state
     */
    public static synchronized boolean getPushState() {
        return allowPush.get();
    }

//    public static synchronized Map<Class<?>, Long> getEventFireTimes() {
//        return eventFireTimes;
//    }

}
