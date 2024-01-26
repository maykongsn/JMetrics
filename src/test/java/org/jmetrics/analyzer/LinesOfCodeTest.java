package org.jmetrics.analyzer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.utils.LoadClass;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinesOfCodeTest {
    @Test
    public void testCalculateLinesOfCode() {
        List<ClassOrInterfaceDeclaration> declaration = LoadClass.loadClassDeclaration("org/jmetrics/SampleClass.java");

        declaration.forEach(classDeclaration -> {
            Type type = new Type(classDeclaration);
            Metric linesOfCode = new LinesOfCode().calculate(type);

            assertEquals(17, linesOfCode.getValue());
        });
    }

    private ClassOrInterfaceDeclaration loadClassDeclaration(String source) {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(source);

            if (in == null) {
                throw new FileNotFoundException("Resource not found");
            }

            CompilationUnit compilationUnit = StaticJavaParser.parse(in);

            return compilationUnit.getClassByName("SampleClass").orElse(null);
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("File not found at " + source, exception);
        }
    }
}
