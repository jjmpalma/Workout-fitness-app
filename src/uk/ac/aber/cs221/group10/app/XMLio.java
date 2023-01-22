/*
 * @(#) XMLio.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

/**
 * Class Description
 * <p>
 * A class designed to be inherited by all .XML input/output classes.
 * Includes a function to query a document that is read in on construction.
 *
 * @author Alex Clive
 * @version 1.1 Initial Development
 */

public abstract class XMLio {

   protected Document doc;
   private XPath xpath;

   /**
    * Constructor for XMLio.
    *
    * @param FileName The file to read from.
    */
   protected XMLio(String FileName) {
      try {

         // generate doc
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();
         doc = db.parse(FileName);

         //generate xpath
         XPathFactory xpf = XPathFactory.newInstance();
         xpath = xpf.newXPath();
      } catch (ParserConfigurationException | IOException | SAXException e) {
         e.printStackTrace();
      }
   }

   /**
    * Queries the DOM for all nodes that match an xpath query.
    *
    * @param path The path to query.
    * @return A NodeList of Nodes that match the given path, or null if path is invalid.
    */
   public NodeList XpathQuery(String path) {
      NodeList returning = null;
      try {
         returning = (NodeList) xpath.evaluate(path, doc, XPathConstants.NODESET);
      } catch (XPathExpressionException e) {
         e.printStackTrace();
         System.out.println("invalid xpath expression, returning NULL");
      }
      return returning;
   }
}
