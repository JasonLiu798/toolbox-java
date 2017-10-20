package com.atjl.util.xml;


import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Stack;

public class XmlSAXParser extends DefaultHandler {

    Stack tags = new Stack();

    public static void main(String[] args) {
        long lasting = System.currentTimeMillis();
        try {
            SAXParserFactory sf = SAXParserFactory.newInstance();
            SAXParser sp = sf.newSAXParser();
            XmlSAXParser reader = new XmlSAXParser();
            sp.parse(new InputSource("D:\\project\\java\\sfopen\\project\\e2agile-core\\.classpath"), reader);
            System.out.println("运行时间：" + (System.currentTimeMillis() - lasting) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {
        String tag = (String) tags.peek();
        if (tag.equals("classpathentry")) {
            System.out.print("entry：" + new String(ch, start, length));
        }
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attrs) {
        tags.push(qName);
    }

}
