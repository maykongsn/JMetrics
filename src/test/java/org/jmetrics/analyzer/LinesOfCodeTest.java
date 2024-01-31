package org.jmetrics.analyzer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.utils.LoadElements;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinesOfCodeTest {
    @Test
    public void testCalculateLinesOfCode() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClass.java");

        declaration.forEach(classDeclaration -> {
            Type type = new Type(classDeclaration);
            Metric linesOfCode = new LinesOfCode().calculate(type);

            assertEquals(17, linesOfCode.getValue());
        });
    }

    @Test
    public void testClassOrInterfaceWithoutLinesOfCode() {
        List<ClassOrInterfaceDeclaration> declaration = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithoutLinesOfCode.java");

        assertEquals(0, declaration.size());
    }
}
