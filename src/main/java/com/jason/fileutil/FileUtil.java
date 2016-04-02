package com.jason.fileutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JianLong
 * @date 2012/11/27 14:56
 */
public class FileUtil {

    /**
     * read file content to List<String>
     * @param path
     * @return
     */
    public static List<String> readFile(String path) {
        List<String> res = new ArrayList<>();
        String line;
        try
        {
            BufferedReader in=new BufferedReader(new FileReader(path));
            line=in.readLine();
            while (line!=null)
            {
                if(line.indexOf("--")!=0){
                    res.add(line);
                }
                line=in.readLine();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }



    /**
     * write contents to file
     * @param filepath file path
     * @param contents content
     */
    public static void write2File(String filepath,List<String> contents) {
        FileWriter writer = null;
        try
        {
            writer=new FileWriter(filepath);//if file exist ,it will delete the file then create
            for(String str:contents){
                writer.write(str+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
