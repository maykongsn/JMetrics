package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.utils.LoadElements;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfMethodsTest {
    @Test
    public void testCalculatorNumberOfMethods() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClass.java");

        declaration.forEach(classDeclaration -> {
            Type type = new Type(classDeclaration);

            Metric numberOfMethods = new NumberOfMethods().calculate(type);

            assertEquals(2, numberOfMethods.getValue());
        });
    }

    @Test
    public void testClassOrInterfaceDeclarationWithoutMethods() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithoutMethods.java");

        declaration.forEach(classDeclaration -> {
            Type type = new Type(classDeclaration);

            Metric numberOfMethods = new NumberOfMethods().calculate(type);

            assertEquals(0, numberOfMethods.getValue());
        });
    }
}
