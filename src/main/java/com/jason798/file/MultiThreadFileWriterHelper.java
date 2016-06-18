package com.jason798.file;

import java.util.List;

/**
 * Created by JasonLiu798 on 16/6/2.
 */
public class MultiThreadFileWriterHelper {

    private static MultiThreadFileWriter multiThreadFileWriter;

    public static void startThread(){
        multiThreadFileWriter = new MultiThreadFileWriter();
        if( !multiThreadFileWriter.isStart()){
            Thread thread = new Thread(multiThreadFileWriter);
            thread.start();
        }
    }

    public static void write(String contnet){
        multiThreadFileWriter.write(contnet);
    }

    public static void write(List<String> contnet){
        multiThreadFileWriter.write(contnet);
    }

    public static void write(String path,String contnet){
        multiThreadFileWriter.write(path,contnet);
    }

    public static void stopThread(){
        MultiThreadFileWriter.close();
    }
}
