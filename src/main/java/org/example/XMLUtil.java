package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class XMLUtil {
    private XPath xPath;
    private Document xmlDoc;
    private String xmlName;

    public XMLUtil(String xmlName) {
        this.xmlName = xmlName;
        File srcXMLFile = new File(xmlName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            xmlDoc = factory.newDocumentBuilder().parse(srcXMLFile);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        xPath = XPathFactory.newInstance().newXPath();
    }

    public void changeXMLNodeValue(String xpath, String query) {
        try {

            Node node = (Node) xPath.compile(xpath).evaluate(xmlDoc, XPathConstants.NODE);
            node.setTextContent(query);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws TransformerException {
        XMLUtil xmlUtil = new XMLUtil("tc01.xml");
        xmlUtil.changeXMLNodeValue("//Customer/State/City/value","Hyderabad");

        Transformer serializer = TransformerFactory.newInstance().newTransformer();
        serializer.transform(new DOMSource(xmlUtil.xmlDoc), new StreamResult(System.out));
    }
}