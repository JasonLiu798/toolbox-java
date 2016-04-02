package com.jason.xml;

import java.util.*;
import java.io.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * convenience class for parsing and/or validating xml documents.
 * The dtd's for validation of jBpm-xml-documents is are included?
 */
public class XmlParser {

    /**
     * creates an XmlParser for an inputStream (conaining an xml-document).
     *
     * @throws NullPointerException if inputStream is null.
     */
    public XmlParser(InputStream inputStream) {
        this.inputSource = new InputSource(inputStream);
        if (inputStream == null) {
            throw new NullPointerException("couldn't create an XmlParser with a null-value for inputStream");
        }
    }

    /**
     * creates an XmlParser for an inputStream (conaining an xml-document).
     *
     * @throws NullPointerException if reader is null.
     */
    public XmlParser(Reader reader) {
        this.inputSource = new InputSource(reader);
        if (reader == null) {
            throw new NullPointerException("couldn't create an XmlParser with a null-value for reader");
        }
    }

    /**
     * creates an XmlParser for an inputStream (conaining an xml-document).
     *
     * @throws NullPointerException if inputSource is null.
     */
    public XmlParser(InputSource inputSource) {
        if (inputSource == null) {
            throw new NullPointerException("couldn't create an XmlParser with a null-value for inputSource");
        }
        this.inputSource = inputSource;
    }

    public void setValidation(boolean validate) {
        this.validate = validate;
    }

    /**
     * validates an xml-document without building a XmlElement's for the parsed contents.
     */
    public void validate() throws XmlException {
        this.validate = false;
        this.parse = true;
        parse();
    }

    /**
     * parses and/or validates an xml document.
     *
     * @throws IllegalStateException if this parser has already been used before.  It can only be
     *                               used once because the inputSource can be read only once.
     */
    public XmlElement parse() throws XmlException {
        if (isConsumed) {
            throw new IllegalStateException("this XmlParser-instance has already been used, please create a new one " +
                    "for each usage");
        }
        XmlElement rootElement = null;
        ParserHandler parserHandler = new ParserHandler();

        //SAXParserFactory factory = SAXParserFactory.newInstance();
        if (validate) {
            //factory.setValidating( true );
        }
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
            reader.setContentHandler(parserHandler);
            if (parse) {
                reader.setContentHandler(parserHandler);
            }
            if (validate) {
                reader.setEntityResolver(parserHandler);
                reader.setErrorHandler(parserHandler);
            }
            isConsumed = true;
            reader.parse(inputSource);
            rootElement = parserHandler.getRootElement();
        } catch (Throwable t) {
            log.error("couldn't parse xml document", t);
            throw new XmlException("couldn't parse xml document : " + t.getMessage());
        }

        if (parserHandler.hasErrors()) {
            throw new XmlException(parserHandler.getErrors());
        }

        return rootElement;
    }


    public static void main(String[] argv) {
        XmlElement xmlElement = null;
        try {
            //InputStream processDefinitionInputStream = new FileInputStream( "E:/javaWorkSpace/test/bin/exampleB
            // .xml" );
            XmlParser xmlParser = new XmlParser(new FileReader("d:/ReqAudActivitys.xml"));
            xmlElement = xmlParser.parse();
            System.out.println("ss=" + xmlElement.getName());
            System.out.println("ss=" + xmlElement.getChildElements("activity-state").get(0));
            System.out.println("ss=" + xmlElement.getChildElements("activity-state").get(1));
        } catch (Exception e) {
            e.printStackTrace();
            Iterator iter = ((XmlException) e).getErrors().iterator();
            while (iter.hasNext()) {
                String error = (String) iter.next();
                System.out.println("xml parsing error in  : " + error);
            }

        }

    }


    private InputSource inputSource = null;
    private boolean isConsumed = false;
    private boolean validate = true;
    private boolean parse = true;
    //private static final Logger log = Logger.getLogger(XmlParser.class);
    private static Log log = LogFactory.getLog(XmlParser.class.getName());
    private static final String LINESEPARATOR = System.getProperty("line.separator");
}
