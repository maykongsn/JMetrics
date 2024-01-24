package org.jmetrics.metrics;

public interface MetricsCounter<T> {
    Metric calculate(T t);
}
