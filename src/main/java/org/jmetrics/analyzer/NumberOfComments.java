package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.metrics.MetricsCounter;

public class NumberOfComments implements MetricsCounter<Type> {
    @Override
    public Metric calculate(Type type) {
        ClassOrInterfaceDeclaration declaration = type.getDeclaration();
        String className = declaration.getNameAsString();

        int numberOfComments = declaration.getAllContainedComments().size();

        return new Metric(className, "", "Number of Comments", numberOfComments);
    }
}
