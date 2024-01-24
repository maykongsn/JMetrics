package org.jmetrics.elements;

import com.github.javaparser.ast.body.MethodDeclaration;

public class Method {
    private final MethodDeclaration declaration;

    public Method(MethodDeclaration declaration) {
        this.declaration = declaration;
    }

    public MethodDeclaration getDeclaration() {
        return this.declaration;
    }
}
