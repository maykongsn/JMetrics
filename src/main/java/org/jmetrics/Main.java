package org.jmetrics;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.jmetrics.analyzer.NumberOfMethods;
import org.jmetrics.analyzer.NumberOfParameters;
import org.jmetrics.elements.Method;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.analyzer.LinesOfCode;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            Path filePath = Paths.get("src/main/java/org/jmetrics/Arquivo.java");
            FileInputStream in = new FileInputStream(filePath.toFile());

            CompilationUnit compilationUnit = StaticJavaParser.parse(in);

            compilationUnit.findAll(MethodDeclaration.class).forEach(methodDeclaration -> {
                Method method = new Method(methodDeclaration);
                Metric numberOfParameters = new NumberOfParameters().calculate(method);
                System.out.println(numberOfParameters);
            });

            compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(classOrInterfaceDeclaration -> {
                Type type = new Type(classOrInterfaceDeclaration);
                Metric linesOfCode = new LinesOfCode().calculate(type);
                Metric numberOfMethods = new NumberOfMethods().calculate(type);

                System.out.println(linesOfCode);
                System.out.println(numberOfMethods);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}