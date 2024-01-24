package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.MethodDeclaration;
import org.jmetrics.elements.Method;
import org.jmetrics.metrics.Metric;
import org.jmetrics.metrics.MetricsCounter;

public class NumberOfParameters implements MetricsCounter<Method> {
    @Override
    public Metric calculate(Method method) {
        MethodDeclaration declaration = method.getDeclaration();
        int countParameters = declaration.getParameters().size();
        System.out.println(declaration.getName());
        return new Metric("Number of Parameters", countParameters);
    }
}
