package com.atjl.retry.api;


import com.atjl.retry.domain.RetryDataContextImpl;

public class DataContextFactory {

    public static <T> DataContext<T> build(T data) {
        DataContext<T> context = new RetryDataContextImpl<>(data);
        return context;
    }
}
