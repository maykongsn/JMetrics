package org.jmetrics.analyzer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.comments.Comment;
import org.jmetrics.elements.Type;
import org.jmetrics.metrics.Metric;
import org.jmetrics.metrics.MetricsCounter;

import java.util.List;

public class LinesOfCode implements MetricsCounter<Type> {
    @Override
    public Metric calculate(Type type) {
        ClassOrInterfaceDeclaration declaration = type.getDeclaration();
        String className = declaration.getNameAsString();
        int classLines = declaration.getEnd().get().line - declaration.getBegin().get().line + 1;

        List<Comment> comments = declaration.getAllContainedComments();

        int commentLines = comments.stream()
                .map(comment -> comment.getContent().split("\r\n|\r|\n").length)
                .reduce(Integer::sum)
                .orElse(0);

        return new Metric(className, "", "LOC", classLines - commentLines);
    }
}
