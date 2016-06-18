package com.jason798.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * for multi thread write same file
 *
 * PS:
 * must new Thread and start
 *
 * Created by JasonLiu798 on 15/6/2.
 */
public class MultiThreadFileWriter implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(MultiThreadFileWriter.class);
    /**
     * for multi thread
     */
    private static ConcurrentLinkedQueue<FileDto> writebuffer = new ConcurrentLinkedQueue<>();
    private static final String DFT_PATH= "/opt/logs/perf/fav.log";
    private static boolean start = false;

    public static boolean isStart() {
        return start;
    }

    public void write(String content){
        write(DFT_PATH,content);
    }

    public void write(List<String> content){
        write(DFT_PATH,content);
    }

    public void write(String path,List<String> content){
        FileDto fd = new FileDto(path,content);
        writebuffer.offer(fd);
    }

    public void write(String path,String content){
        FileDto fd = new FileDto(path,content);
        writebuffer.offer(fd);
    }

    public static void close(){
        start = false;
    }

    @Override
    public void run() {
        start = true;
        log.debug("multi thread file writer started!");
        while(start){
            if(!writebuffer.isEmpty()){
                FileDto fd = writebuffer.poll();
                FileHelper.write2File( fd.getPath(), fd.getContents());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.debug("multi thread file writer stopped!");
    }

//    public static void main(String[] args) {
//        String s = "gZmeDQBoSelihGLmL4TDcJO5F9RPm_g8Xi_iE2lIDvWKrTDEAPdi2w";
//        String s2 = "zJHdhrmHnqj3-ihT6IpRxaOym-gNNwc1z4I7JQmyBOpJDAFDkIupGPhaICUjdoHTuwbWz095AiY2UudYmxDZa8PTAyLwtkkwDyW_CkTU4BFXrPjWCGwzeg";
//        System.out.println(s.length()+","+s2.length());
//    }
}
