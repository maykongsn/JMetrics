package org.jmetrics.elements;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

public class Type {
    private ClassOrInterfaceDeclaration declaration;

    public Type(ClassOrInterfaceDeclaration declaration) {
        this.declaration = declaration;
    }

    public ClassOrInterfaceDeclaration getDeclaration() {
        return this.declaration;
    }
}
