package com.atjl.util.xml;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.FileReader;
import java.util.Iterator;


public class XmlParserTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: setValidation(boolean validate)
     */
    @Test
    public void testAll() throws Exception {
        XmlElement xmlElement = null;
        try {
            //InputStream processDefinitionInputStream = new FileInputStream( "E:/javaWorkSpace/simpledemo/bin/exampleB
            // .xml" );
            XmlParser xmlParser = new XmlParser(new FileReader("D:\\project\\java\\toolbox-java\\pom.xml"));
            xmlElement = xmlParser.parse();
            System.out.println("ss=" + xmlElement.getName());
            System.out.println("ss=" + xmlElement.getChildElements("properties").get(0));
            System.out.println("ss=" + xmlElement.getChildElements("dependencies").get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Iterator iter = ((XmlException) e).getErrors().iterator();
            while (iter.hasNext()) {
                String error = (String) iter.next();
                System.out.println("xml parsing error in  : " + error);
            }

        }

    }

    /**
     * Method: validate()
     */
    @Test
    public void testValidate() throws Exception {

    }

    /**
     * Method: parseInner()
     */
    @Test
    public void testParse() throws Exception {

    }

    /**
     * Method: main(String[] argv)
     */
    @Test
    public void testMain() throws Exception {

    }


}
