package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.MethodDeclaration;
import org.jmetrics.elements.Method;
import org.jmetrics.metrics.Metric;
import org.jmetrics.utils.LoadElements;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfParametersTest {
    @Test
    public void testCalculatorNumberOfParameters() {
        List<MethodDeclaration> declarations = LoadElements.loadMethodsDeclaration("org/jmetrics/SampleClass.java");

        declarations.forEach(methodDeclaration -> {
            String className = methodDeclaration != null ? methodDeclaration.getNameAsString() : null;

            Method method = new Method(className, methodDeclaration);

            Metric metric = new NumberOfParameters().calculate(method);

            assertEquals(1, metric.getValue());
        });
    }

    @Test
    public void testMethodDeclarationWithoutMethods() {
        List<MethodDeclaration> declarations = LoadElements.loadMethodsDeclaration("org/jmetrics/SampleClassWithoutMethods.java");

        assertEquals(0, declarations.size());
    }
}
