package com.jason798.xml;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 * parset handler
 */
public class ParserHandler extends DefaultHandler {
	private static final Logger log = LoggerFactory.getLogger(ParserHandler.class);

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        XmlElement newElement = new XmlElement(qName);

        Map attributeMap = new HashMap();
        for (int i = 0; i < atts.getLength(); i++) {
            attributeMap.put(atts.getQName(i), atts.getValue(i));
        }

        newElement.setAttributes(attributeMap);

        int elementStackSize = elementStack.size();
        if (elementStackSize > 0) {
            XmlElement containingElement = (XmlElement) elementStack.getLast();
            containingElement.addChild(newElement);
        } else {
            rootElement = newElement;
        }

        elementStack.add(newElement);
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        elementStack.removeLast();
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length).trim();
        if (!"".equals(text)) {
            XmlElement element = (XmlElement) elementStack.getLast();
            element.addText(text);
        }
    }

    public XmlElement getRootElement() {
        return rootElement;
    }

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
        InputSource dtdInputSource = null;

        log.debug("resolving entity : " + publicId);

        try {
            if ("jbpm/processdefinition_1_0".equals(publicId)) {
                dtdInputSource = new InputSource(ParserHandler.class.getResourceAsStream("/processdefinition.dtd"));
            } else if ("jbpm/webinterface_1_0".equals(publicId)) {
                dtdInputSource = new InputSource(ParserHandler.class.getResourceAsStream("/webinterface.dtd"));
            }
        } catch (RuntimeException e) {
            log.error("cannot resolve entity ", e);
        }

        return dtdInputSource;
    }


    @Override
    public void error(SAXParseException e) {
        errors.add(e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) {
        errors.add(e.getMessage());
    }

    public boolean hasErrors() {
        return (errors.size() > 0);
    }

    public Collection getErrors() {
        return errors;
    }


    private Collection errors = new ArrayList();
    private LinkedList elementStack = new LinkedList();
    private XmlElement rootElement = null;
    //private static final Logger log = Logger.getLogger(IwapWorkFlowHandler.class);

}
