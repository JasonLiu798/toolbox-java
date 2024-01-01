package com.atjl.util.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atjl.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * use json print log
 */
@Slf4j
public class LogUtil {

    public static final String EX ="Exception|ERROR|error|exception";
    public static final String EX_REPLACE = "excep";

    /**
     * print json log,catch json exception
     */
    public static String toJsonStringNoExcep(Object obj){
        if(obj==null){
            return null;
        }
        try{
            return JSON.toJSONString(obj);
        } catch (Exception e){
            String objStr = obj.toString();
            log.error("json log fail {}",objStr,e);
            return objStr;
        }
    }

    /**
     * print json log,filter exception in obj field
     */
    public static String toJsonStringFilterExcep(Object obj){
        String logStr = toJsonStringNoExcep(obj);
        if(logStr==null){
            return null;
        }
        return logStr.replaceAll(EX,EX_REPLACE);
    }

    /**
     * formt json
     */
    public static String toJsonStringFmt(Object obj){
        if(obj==null){
            return null;
        }
        try{
            return JSON.toJSONString(obj, SerializerFeature.PrettyFormat);
        } catch (Exception e){
            String objStr = obj.toString();
            log.error("json fmt fail {}",objStr,e);
            return objStr;
        }
    }


    public static <T> void printList(Collection<T> c) {
        printList(c, 0);
    }

    public static <T> void printList(Collection<T> c, String name) {
        printList(c, name, 0);
    }

    public static <T> void printList(Collection<T> c, int level) {
        printList(c, null, level);
    }

    /**
     * for debug
     *
     * @param
     * @param <T>
     */
    public static <T> void printList(Collection<T> c, String name, int level) {
        if (name == null) {
            name = "default";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Collection:{[");
        sb.append(name);
        sb.append("]\n");
        Iterator<T> it = c.iterator();
        int i = 0;
        while (it.hasNext()) {
            T item = it.next();
            sb.append(i + "," + item + "\n");
            i++;
        }
        sb.append("}\n");
        switch (level) {
            case 0:
                System.out.println(sb.toString());
                break;
            case 1:
                log.debug(sb.toString());
                break;
            case 2:
                log.info(sb.toString());
                break;
            case 3:
                log.warn(sb.toString());
                break;
            case 4:
                log.error(sb.toString());
                break;
            default:
                break;
        }
    }


    public static String fmtTs(Long ts){
        if(ts==null){
            return null;
        }
        return DateUtil.format(new Date(ts),DateUtil.DEFAULT_FORMAT);
    }

    private LogUtil(){
        throw new UnsupportedOperationException();
    }
}
