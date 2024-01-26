package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.utils.LoadClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfMethodsTest {
    @Test
    public void testCalculatorNumberOfMethods() {
        List<ClassOrInterfaceDeclaration> declaration = LoadClass.loadClassDeclaration("org/jmetrics/SampleClass.java");

        declaration.forEach(classDeclaration -> {
            Type type = new Type(classDeclaration);

            Metric numberOfMethods = new NumberOfMethods().calculate(type);

            assertEquals(2, numberOfMethods.getValue());
        });
    }
}
