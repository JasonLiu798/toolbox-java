package com.atjl.jetty.util;

import com.atjl.jetty.classpath.ClassPathEntry;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ClasspathEntryDao {

    public static List<String> readAllPath(List<String> filenames,String[] whiteJars){
        List<String> jars = new LinkedList<>();
        try {
            Set<ClassPathEntry> entries = readAll(filenames,whiteJars);
            System.out.println("entries "+entries);
            for(ClassPathEntry entry:entries){
                jars.add(entry.getPath());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return jars;
    }

    public static Set<ClassPathEntry> readAll(List<String> filenames,String[] whiteJars) throws DocumentException {
        System.out.println("read files: "+filenames);
        Set<ClassPathEntry> res = new HashSet<>();
        for(String filename:filenames){
            Set<ClassPathEntry> entries = read(filename,whiteJars);
//            System.out.println("readed :"+entries);
            res.addAll(entries);
        }
        return res;
    }

    public static Set<ClassPathEntry> read(String filename,String[] whiteJars) throws DocumentException {
        SAXReader reader = new SAXReader();
        File file = new File(filename);
        Document document = reader.read(file);
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        Set<ClassPathEntry> set = new HashSet<>();
        for (Element child : childElements) {
            //所有 attributes
            /**
             * <classpathentry sourcepath="C:/Users/01174604/.gradle/caches/modules-2/files-2.1/commons-beanutils/commons-beanutils/1.9.2/53746de93a0b30f346c082074a40c01a6cebdfa7/commons-beanutils-1.9.2-sources.jar" kind="lib" path="C:/Users/01174604/.gradle/caches/modules-2/files-2.1/commons-beanutils/commons-beanutils/1.9.2/7a87d845ad3a155297e8f67d9008f4c1e5656b71/commons-beanutils-1.9.2.jar" exported="true"/>
             */
            if(!StringCheckUtil.equal( child.attributeValue("kind"),"lib"  )){
                continue;
            }

            String path = child.attributeValue("path");

            if( notInWhite(path,whiteJars)){
                if(StringCheckUtil.isEmpty(child.attributeValue("sourcepath")) ){
                    continue;
                }
            }


            ClassPathEntry entry = new ClassPathEntry();
            entry.setKind("lib");
            entry.setPath(child.attributeValue("path"));
            entry.setExported( Boolean.parseBoolean(child.attributeValue("exported")) );
            set.add(entry);
            //子元素 child.elements(); child.elementText("title")
        }
        //System.out.println(set);
        return set;
    }

    private static boolean notInWhite(String content,String[] whiteList){
        if(CollectionUtil.isNotEmpty(whiteList)){
            boolean exist = false;
            for(String whiteJar:whiteList) {
                if (content.indexOf(whiteJar)>=0){
                    exist = true;
                    break;
                }
            }
            if(exist){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

}
