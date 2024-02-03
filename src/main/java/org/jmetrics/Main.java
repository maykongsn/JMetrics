package org.jmetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.jmetrics.analyzer.*;
import org.jmetrics.elements.Method;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.utils.LoadElements;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MethodDeclaration> methodDeclarations = LoadElements.loadMethodsDeclaration("SampleClass.java");
        List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations = LoadElements.loadClassDeclaration("SampleClass.java");

        methodDeclarations.forEach(methodDeclaration -> {
            @SuppressWarnings("unchecked")
            ClassOrInterfaceDeclaration classDeclaration = methodDeclaration.findAncestor(ClassOrInterfaceDeclaration.class).orElse(null);
            String className = classDeclaration != null ? classDeclaration.getNameAsString() : null;

            Method method = new Method(className, methodDeclaration);
            Metric numberOfParameters = new NumberOfParameters().calculate(method);

            System.out.println(numberOfParameters);
        });

        classOrInterfaceDeclarations.forEach(classOrInterfaceDeclaration -> {
            Type type = new Type(classOrInterfaceDeclaration);

            Metric linesOfCode = new LinesOfCode().calculate(type);
            Metric numberOfMethods = new NumberOfMethods().calculate(type);
            Metric numberOfComments = new NumberOfComments().calculate(type);
            Metric linesOfComments = new LinesOfComments().calculate(type);

            System.out.println(linesOfCode);
            System.out.println(numberOfMethods);
            System.out.println(numberOfComments);
            System.out.println(linesOfComments);
        });
    }
}