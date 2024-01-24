package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.metrics.MetricsCounter;

public class NumberOfMethods implements MetricsCounter<Type> {
    @Override
    public Metric calculate(Type type) {
        ClassOrInterfaceDeclaration declaration = type.getDeclaration();

        int methodsCount = declaration.getMethods().size();
        return new Metric("Number of Methods", methodsCount);
    }
}
