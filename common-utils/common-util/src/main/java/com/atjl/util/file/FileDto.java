package com.atjl.util.file;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JasonLiu798 on 16/6/2.
 */
public class FileDto implements Serializable{
//    private int a;
    private static final long serialVersionUID = -1558745353709563962L;
    private String path;
    private List<String> contents = new LinkedList<>();

    //    public FileDto(String content) {
//        path = DFT_PATH;
//        contents.add(content);
//    }
    public FileDto(String path,String content){
        this.path = path;
        contents.add(content);
    }

    public FileDto(String path,List<String> content){
        this.path = path;
        contents.addAll(content);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public String getPath() {
        return path;
    }

    public List<String> getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "path='" + path + '\'' +
                ", contents=" + contents +
//                ", a=" + a +
                '}';
    }
}
