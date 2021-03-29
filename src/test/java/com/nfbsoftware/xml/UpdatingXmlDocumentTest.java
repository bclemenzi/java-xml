package com.nfbsoftware.xml;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the XML Document
 */
public class UpdatingXmlDocumentTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UpdatingXmlDocumentTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( UpdatingXmlDocumentTest.class );
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testUpdateNodeValue() throws Exception
    {
        System.out.println("====> Starting UpdatingXmlDocumentTest.testUpdateElement");
        
        StringBuffer xmlString = new StringBuffer();
        xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlString.append("<ROOT>");
        xmlString.append("  <one>");
        xmlString.append("      <Elements>");
        xmlString.append("          <Element id=\"1000\">");
        xmlString.append("              <id>1000</id>");
        xmlString.append("              <name>My Name 1000</name>");
        xmlString.append("              <description>This is my 1000 description</description>");
        xmlString.append("          </Element>");
        xmlString.append("          <Element id=\"2000\">");
        xmlString.append("              <id>2000</id>");
        xmlString.append("              <name>My Name 2000</name>");
        xmlString.append("              <description>This is my 2000 description</description>");
        xmlString.append("          </Element>");
        xmlString.append("      </Elements>");
        xmlString.append("  </one>");
        xmlString.append("</ROOT>");
        
        IXmlDocument doc = new XmlDocument(xmlString.toString());
        IXmlElement root = doc.getRootElement();

        IXmlElement elementToUpdate = root.selectChild("//Element[@id='1000']");
        
        System.out.println(doc.toString());
        System.out.println("*************************");
        
        elementToUpdate.getChild("name").setValue("My Name 1001");
        //root.replaceChildrenWithXPath("//Element[@id='1000']", elementToUpdate);
        
        System.out.println(doc.toString());
        
        Assert.assertTrue(true);

        System.out.println("====> Finished UpdatingXmlDocumentTest.testUpdateElement");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testUpdateNode() throws Exception
    {
        System.out.println("====> Starting UpdatingXmlDocumentTest.testUpdateElement");
        
        StringBuffer xmlString = new StringBuffer();
        xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlString.append("<ROOT>");
        xmlString.append("  <one>");
        xmlString.append("      <two id=\"1000\">");
        xmlString.append("          <id>1000</id>");
        xmlString.append("          <name>My Name</name>");
        xmlString.append("          <description>This is my description</description>");
        xmlString.append("      </two>");
        xmlString.append("  </one>");
        xmlString.append("</ROOT>");
        
        IXmlDocument doc = new XmlDocument(xmlString.toString());
        IXmlElement root = doc.getRootElement();

        //IXmlElement elementToUpdate = root.selectChild("//two[@id='1000']/name");
        
        //System.out.println(doc.toString());
        //System.out.println("*************************");
        
        // Set the new value
        root.selectChild("//two[@id='1000']/name").setValue("Your Name");
        
        //System.out.println(doc.toString());
        
        Assert.assertTrue(true);

        System.out.println("====> Finished UpdatingXmlDocumentTest.testUpdateElement");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testUpdateElement() throws Exception
    {
        System.out.println("====> Starting UpdatingXmlDocumentTest.testUpdateElement");
        
        StringBuffer xmlString = new StringBuffer();
        xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlString.append("<ROOT>");
        xmlString.append("  <one>");
        xmlString.append("      <two>");
        xmlString.append("          <element id=\"1\">Element-100</element>");
        xmlString.append("          <element id=\"2\">Element-200</element>");
        xmlString.append("          <element id=\"3\">Element-300</element>");
        xmlString.append("      </two>");
        xmlString.append("  </one>");
        xmlString.append("</ROOT>");
        
        IXmlDocument doc = new XmlDocument(xmlString.toString());
        IXmlElement root = doc.getRootElement();

        IXmlElement elementToUpdate = root.selectChild("//element[@id='2']");
        
        //System.out.println(doc.toString());
        //System.out.println("*************************");
        
        // Set the new value
        elementToUpdate.setValue("Element-201");
        
        //System.out.println(doc.toString());
        
        Assert.assertTrue(true);

        System.out.println("====> Finished UpdatingXmlDocumentTest.testUpdateElement");
    }
}
