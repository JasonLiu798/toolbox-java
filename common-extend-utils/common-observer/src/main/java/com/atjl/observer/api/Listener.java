package com.atjl.observer.api;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.LongAdder;

/**
 * Listener class. When a <tt>Listener</tt> is registered, it will have its
 * event exeMethod invoked when an event of the corresponding
 * type is pushed through
 *
 * @param <T> The type of event to listen
 */
public abstract class Listener<T> {
    /**
     * A Class&lt;T&gt; representing the type of event to listen for.
     */
    private Class<T> type;

    /**
     * fire count
     */
    private static LongAdder fireCount = new LongAdder();

    private static Long lastCost;

    /**
     * Default constructor. Sets up type.
     */
    public Listener() {
        Type typep = getClass().getGenericSuperclass();
        if (typep instanceof ParameterizedType) {
            this.type = (Class<T>) (((ParameterizedType) typep).getActualTypeArguments()[0]);
        }
    }

    public void after(long cost) {
        fireCount.increment();
        lastCost = cost;
    }

    public Long getFireCount() {
        return fireCount.longValue();
    }


    public Long getLastCost() {
        return lastCost;
    }
//
//    public static void setLastCost(Long lastCost) {
//        Listener.lastCost = lastCost;
//    }

    /**
     * Invoked when an event of the corresponding type is pushed through
     *
     * @param event The event to be "acted" on.
     */
    public abstract void event(T event);

    /**
     * The type of the event. Publicly accessible.
     *
     * @return The type of the event.
     */
    public final Class<T> getType() {
        return type;
    }
}
