package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.utils.LoadElements;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinesOfCodeTest {
    @Test
    public void testCalculateLinesOfCode() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClass.java");

        declaration.forEach(classDeclaration -> {
            Type type = new Type(classDeclaration);
            Metric linesOfCode = new LinesOfCode().calculate(type);

            assertEquals(15, linesOfCode.getValue());
        });
    }

    @Test
    public void testClassOrInterfaceWithoutLinesOfCode() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithoutLinesOfCode.java");

        assertEquals(0, declaration.size());
    }

    @Test
    public void testClassOrInterfaceDeclarationWithOneLineMethod() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithOneLineMethod.java");

        declaration.forEach(classDeclaration -> {
            Type type = new Type(classDeclaration);

            Metric linesOfCode = new LinesOfCode().calculate(type);

            assertEquals(4, linesOfCode.getValue());
        });
    }

    @Test
    public void testClassOrInterfaceWithComments() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithComments.java");

        declaration.forEach(classDeclaration -> {
            Type type = new Type(classDeclaration);

            Metric linesOfCode = new LinesOfCode().calculate(type);

            assertEquals(2, linesOfCode.getValue());
        });
    }
}
