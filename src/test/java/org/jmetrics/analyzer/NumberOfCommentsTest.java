package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.utils.LoadElements;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfCommentsTest {
    @Test
    public void testCalculatorNumberOfComments() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClass.java");

        declaration.forEach(classDeclaration -> {
            Type type = new Type(classDeclaration);

            Metric numberOfComments = new NumberOfMethods().calculate(type);

            assertEquals(2, numberOfComments.getValue());
        });
    }

    @Test
    public void testClassOrInterfaceWithCommentsOnly() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithComments.java");

        declaration.forEach(classDeclaration -> {
            Metric numberOfComments = new NumberOfComments().calculate(new Type(classDeclaration));

            assertEquals(3, numberOfComments.getValue());
        });
    }

    @Test
    public void testClassOrInterfaceWithoutComments() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithoutMethods.java");

        declaration.forEach(classDeclaration -> {
            Metric numberOfComments = new NumberOfComments().calculate(new Type(classDeclaration));

            assertEquals(0, numberOfComments.getValue() );
        });
    }
}
