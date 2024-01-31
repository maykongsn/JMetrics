package org.jmetrics.utils;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoadElementsTest {
    @Test
    public void testLoadElementsDeclaration() {
        List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations = LoadElements.loadClassDeclaration("org/jmetrics/SampleClass.java");
        List<MethodDeclaration> methodsDeclarations = LoadElements.loadMethodsDeclaration("org/jmetrics/SampleClass.java");

        assertEquals(1, classOrInterfaceDeclarations.size(), methodsDeclarations.size());
    }

    @Test
    public void testLoadElementsException() {
        assertThrows(RuntimeException.class, () -> {
            LoadElements.loadClassDeclaration("org/jmetrics/Class.java");
            LoadElements.loadMethodsDeclaration("org/jmetrics/Class.java");
        });
    }

    @Test
    public void testLoadDeclarationWithoutLinesOfCode() {
        List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations = LoadElements.loadClassDeclaration("org/jmetrics/SampleClassWithoutLinesOfCode.java");
        List<MethodDeclaration> methodDeclarations = LoadElements.loadMethodsDeclaration("org/jmetrics/SampleClassWithoutLinesOfCode.java");

        assertEquals(0, classOrInterfaceDeclarations.size(), methodDeclarations.size());
    }

    @Test
    public void testLoadDeclarationInvalidSource() {
        assertThrows(RuntimeException.class, () -> {
            LoadElements.loadClassDeclaration("org/jmetrics/SampleClass.txt");
            LoadElements.loadClassDeclaration("org/jmetrics/sample/SampleClass.java");
        });
    }
}
