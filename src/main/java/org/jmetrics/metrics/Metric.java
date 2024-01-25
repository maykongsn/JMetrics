package org.jmetrics.metrics;

import java.nio.file.Path;
import java.util.Objects;

public class Metric {
    private final String name;
    private final int value;
    private final String methodName;
    private final String className;


    public Metric(String className, String methodName, String name, int value) {
        this.methodName = methodName;
        this.className = className;
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
        String base = "Class: %-25s |";
        String methodPart = this.methodName != null && !this.methodName.isEmpty() ?
                String.format(" Method: %-25s |", this.methodName) : "";
        return String.format(base + methodPart + " Metric: %-25s | Value: %d",
                this.className, this.name, this.value);
    }
}
