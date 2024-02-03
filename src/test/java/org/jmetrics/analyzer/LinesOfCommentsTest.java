package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.utils.LoadElements;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinesOfCommentsTest {
    @Test
    public void testCalculateLinesOfComments() {
        List<ClassOrInterfaceDeclaration> declarations = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithComments.java");

        declarations.forEach(classDeclaration -> {
            Metric linesOfComments = new LinesOfComments().calculate(new Type(classDeclaration));

            assertEquals(7, linesOfComments.getValue());
        });
    }

    @Test
    public void testClassOrInterfaceWithoutLinesOfComments() {
        List<ClassOrInterfaceDeclaration> declarations = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithoutLinesOfCode.java");

        declarations.forEach(classDeclaration -> {
            Metric linesOfComments = new LinesOfComments().calculate(new Type(classDeclaration));

            assertEquals(0, linesOfComments.getValue());
        });
    }
}
