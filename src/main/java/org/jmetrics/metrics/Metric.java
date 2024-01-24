package org.jmetrics.metrics;

import java.util.Objects;

public class Metric {
    private String name;
    private int value;

    public Metric(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metric metric = (Metric) o;
        return value == metric.value && Objects.equals(name, metric.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return String.format("%-25s: %d", name, value);
    }
}
