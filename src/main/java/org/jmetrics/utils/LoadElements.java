package org.jmetrics.utils;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class LoadElements {
    public static List<ClassOrInterfaceDeclaration> loadClassDeclaration(String source) {
        try {
            CompilationUnit compilationUnit = parseCompilationUnit(source);
            
            return compilationUnit.findAll(ClassOrInterfaceDeclaration.class);
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("File not found at " + source, exception);
        }
    }

    public static List<MethodDeclaration> loadMethodsDeclaration(String source) {
        try {
            CompilationUnit compilationUnit = parseCompilationUnit(source);

            return compilationUnit.findAll(MethodDeclaration.class);
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("File not found at " + source, exception);
        }
    }

    private static CompilationUnit parseCompilationUnit(String source) throws FileNotFoundException {
        InputStream in = LoadElements.class.getClassLoader().getResourceAsStream(source);

        if (in == null) {
            throw new FileNotFoundException("Resource not found");
        }

        return  StaticJavaParser.parse(in);
    }
}
