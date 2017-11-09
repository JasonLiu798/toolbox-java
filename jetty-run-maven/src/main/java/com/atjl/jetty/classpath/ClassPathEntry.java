package com.atjl.jetty.classpath;

public class ClassPathEntry {
    private String sourcepath;//
    private String kind;//lib
    private String path;
    private boolean exported;

    public ClassPathEntry() {
        super();
    }

    public String getSourcepath() {
        return sourcepath;
    }

    public void setSourcepath(String sourcepath) {
        this.sourcepath = sourcepath;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isExported() {
        return exported;
    }

    public void setExported(boolean exported) {
        this.exported = exported;
    }
}
