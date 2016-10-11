package com.jason798.file;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;

public class PDFUtil {
    public static String readText(String filepath) throws Exception {

        File f = new File(filepath);
        RandomAccessFile rf = new RandomAccessFile(f, "rw");
        PDFParser p = new PDFParser(rf);
        p.parse();
        PDFTextStripper ts = new PDFTextStripper();
        String s = ts.getText(p.getPDDocument());
        System.out.println(s);
        rf.close();
        return s;
    }
}

