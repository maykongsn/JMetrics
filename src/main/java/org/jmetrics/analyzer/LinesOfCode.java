package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.metrics.MetricsCounter;

public class LinesOfCode implements MetricsCounter<Type> {
    @Override
    public Metric calculate(Type type) {
        ClassOrInterfaceDeclaration declaration = type.getDeclaration();

        int linesOfCode = declaration.getEnd().get().line - declaration.getBegin().get().line + 1;

        return new Metric("LOC", linesOfCode);
    }
}
