package org.jmetrics.elements;

import com.github.javaparser.ast.body.MethodDeclaration;

public class Method {
    private final String className;
    private final MethodDeclaration declaration;

    public Method(String className, MethodDeclaration declaration) {
        this.className = className;
        this.declaration = declaration;
    }

    public MethodDeclaration getDeclaration() {
        return this.declaration;
    }

    public String getClassName() {
        return this.className;
    }
}
