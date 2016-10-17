package com.jason798.build;

import java.util.LinkedList;
import java.util.List;

public class ClassFileReader {
    public enum LineType {
        PACKAGE, IMPORT, CLASS_START, INNER_CLASS_START, INNER_CLASS_END, CLASS_END
    }

    public void getClassInner(List<String> contents) {
        List<String> innerContents = new LinkedList<>();
        List<String> imports = new LinkedList<>();

        for (String c : contents) {
            if (c.indexOf("import ") >= 0) {

            }
        }
    }
}
