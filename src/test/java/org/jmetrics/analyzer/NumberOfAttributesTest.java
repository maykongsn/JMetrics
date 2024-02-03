package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.utils.LoadElements;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfAttributesTest {
    @Test
    public void testCalculateNumberOfAttributes() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClass.java");

        declaration.forEach(classDeclaration -> {
            Metric numberOfAttributes = new NumberOfAttributes().calculate(new Type(classDeclaration));

            assertEquals(2, numberOfAttributes.getValue());
        });
    }

    @Test
    public void testClassWithoutAttributes() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithOneLineMethod.java");

        declaration.forEach(classDeclaration -> {
            Metric numberOfAttributes = new NumberOfAttributes().calculate(new Type(classDeclaration));

            assertEquals(0, numberOfAttributes.getValue());
        });
    }
}
