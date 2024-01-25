package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.jmetrics.collectors.ClassOrInterfaceVisitor;
import org.jmetrics.elements.Method;
import org.jmetrics.metrics.Metric;
import org.jmetrics.metrics.MetricsCounter;

public class NumberOfParameters implements MetricsCounter<Method> {
    @Override
    public Metric calculate(Method method) {
        MethodDeclaration declaration = method.getDeclaration();

        String className = method.getClassName();
        String methodName = declaration.getNameAsString();

        int countParameters = declaration.getParameters().size();
        return new Metric(className, methodName, "Number of Parameters", countParameters);
    }
}
