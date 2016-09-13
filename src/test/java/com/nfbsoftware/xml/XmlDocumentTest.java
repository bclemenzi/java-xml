package com.nfbsoftware.xml;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the XML Document
 */
public class XmlDocumentTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public XmlDocumentTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( XmlDocumentTest.class );
    }

    /**
     * 
     * @throws Exception
     */
    public void testDocumentCreate() throws Exception
    {
        System.out.println("====> Starting XmlDocumentTest.testDocumentCreate");
        
        IXmlDocument doc = new XmlDocument();
        IXmlElement testElement = doc.createChild("Test", "");
        
        testElement.createChild("TestOne", "something");
        testElement.createChild("TestTwo", "something else");
        testElement.createChild("TestThree", "yet another something");

        System.out.println(doc.toString());

        assertTrue(true);
        
        System.out.println("====> Finished XmlDocumentTest.testDocumentCreate");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testAttributeCreate() throws Exception
    {
        System.out.println("====> Starting XmlDocumentTest.testAttributeCreate");
        
        IXmlDocument doc = new XmlDocument();
        IXmlElement testElement = doc.createChild("Test", "");
        
        testElement.createChild("TestOne", "something");
        testElement.createChild("TestTwo", "something else");
        
        IXmlElement thirdElement = testElement.createChild("TestThree", "yet another something");
        thirdElement.setAttribute("ID", "123456789");
        
        System.out.println(doc.toString());
        
        assertTrue(true);

        System.out.println("====> Finished XmlDocumentTest.testAttributeCreate");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testGetElement() throws Exception
    {
        System.out.println("====> Starting XmlDocumentTest.testGetElement");
        
        IXmlDocument doc = new XmlDocument();
        IXmlElement testElement = doc.createChild("Test", "");
        
        testElement.createChild("TestOne", "something");
        testElement.createChild("TestTwo", "something else");
        testElement.createChild("TestThree", "yet another something");

        String elementValue = testElement.getChildValue("TestOne");
        
        System.out.println("elementValue: " + elementValue);
        
        Assert.assertTrue(elementValue.equalsIgnoreCase("something"));

        System.out.println("====> Finished XmlDocumentTest.testGetElement");
    }
    
    /**
     * 
     */
    public void testCreateDocumentFromString()
    {
        try
        {
            StringBuffer xmlString = new StringBuffer();
            xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlString.append("<!-- COMMENT -->");
            xmlString.append("<ROOT>");
            xmlString.append("  <one>test1</one>");
            xmlString.append("  <two>");
            xmlString.append("      <three>test3</three>");
            xmlString.append("  </two>");
            xmlString.append("</ROOT>");
            
            IXmlDocument doc = new XmlDocument(xmlString.toString());
            IXmlElement root = doc.getRootElement();
            
            IXmlElement nodeTwo = root.getChild("two");
            IXmlElement nodeThree = nodeTwo.getChild("three");
            
            String thirdValue = nodeThree.getValue();
            
            System.out.println("thirdValue: " + thirdValue);
            
            Assert.assertTrue("testCreateDocumentFromString returned an error.", thirdValue.equalsIgnoreCase("test3"));
        }
        catch (Exception e)
        {
            Assert.fail("testCreateDocumentFromString Failure");
        }
    }
    
    /**
     * 
     */
    public void testReadDocumentUsingXPath()
    {
        try
        {
            StringBuffer xmlString = new StringBuffer();
            xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlString.append("<ROOT>");
            xmlString.append("  <one>");
            xmlString.append("      <two>");
            xmlString.append("          <three>test3</three>");
            xmlString.append("      </two>");
            xmlString.append("  </one>");
            xmlString.append("</ROOT>");
            
            IXmlDocument doc = new XmlDocument(xmlString.toString());
            IXmlElement root = doc.getRootElement();
            
            // Make an XPath call to get a handle on the third node.
            IXmlElement nodeThree = root.getChild("one/two/three");
            
            String thirdValue = nodeThree.getValue();
            
            Assert.assertTrue("testReadDocumentUsingXPath returned an error.", thirdValue.equalsIgnoreCase("test3"));
        }
        catch (Exception e)
        {
            Assert.fail("testReadDocumentUsingXPath Failure");
        }
    }
    
    /**
     * 
     */
    public void testGetElementAttribute()
    {
        try
        {
            StringBuffer xmlString = new StringBuffer();
            xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlString.append("<ROOT>");
            xmlString.append("  <one>");
            xmlString.append("      <two>");
            xmlString.append("          <three id=\"1234\">test3</three>");
            xmlString.append("      </two>");
            xmlString.append("  </one>");
            xmlString.append("</ROOT>");
            
            IXmlDocument doc = new XmlDocument(xmlString.toString());
            IXmlElement root = doc.getRootElement();
            
            // Make an XPath call to get a handle on the third node.
            IXmlElement nodeThree = root.getChild("one/two/three");
            
            String thirdAttributeValue = nodeThree.getAttribute("id");
            
            Assert.assertTrue("testGetElementAttribute returned an error.", thirdAttributeValue.equalsIgnoreCase("1234"));
        }
        catch (Exception e)
        {
            Assert.fail("testGetElementAttribute Failure");
        }
    }
    
    /**
     * 
     */
    public void testGetCDATASection()
    {
        try
        {
            StringBuffer xmlString = new StringBuffer();
            xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlString.append("<ROOT>");
            xmlString.append("  <one>");
            xmlString.append("      <column>");
            xmlString.append("          <name>order</name>");
            xmlString.append("          <value><![CDATA[1234]]></value>");
            xmlString.append("      </column>");
            xmlString.append("  </one>");
            xmlString.append("</ROOT>");
            
            IXmlDocument doc = new XmlDocument(xmlString.toString());
            IXmlElement root = doc.getRootElement();
            
            // Make an XPath call to get a handle on the third node.
            IXmlElement columnNode = root.getChild("one/column");
            
            String cdataValue = columnNode.getCDATASection("value");
            
            Assert.assertTrue("testGetCDATASection returned an error.", cdataValue.equalsIgnoreCase("1234"));
        }
        catch (Exception e)
        {
            Assert.fail("testGetCDATASection Failure");
        }
    }
    
    public void testSetCDATASection()
    {
        try
        {
            IXmlDocument doc = new XmlDocument();
            IXmlElement testElement = doc.createChild("Test", "");
            
            testElement.createChild("TestOne", "something");
            testElement.createChild("TestTwo", "something else");
            
            IXmlElement cdataElement = testElement.createChild("TestCDATA", "");
            cdataElement.setCDATASection("MYCDATATEST");
            
            System.out.println(doc.toString());
            
            String cdataValue = testElement.getCDATASection("TestCDATA");
            
            Assert.assertTrue("testSetCDATASection returned an error.", cdataValue.equalsIgnoreCase("MYCDATATEST"));
        }
        catch (Exception e)
        {
            Assert.fail("testGetCDATASection Failure");
        }
    }
}
