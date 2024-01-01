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
