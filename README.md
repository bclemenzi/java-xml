# java-xml
Java-XML is Java library designed to help simplify the building and parsing of XML documents

Features
--------
  * Support for creating new XML document objects
  * Support for importing existing XML documents from your file system
  * Support for importing existing XML documents from a String
  * Support for adding child elements and their attributes
  * Support for handling CDATA sections; including the creation and reading of those elements
  * Support for getting a handle to an element via XPath

Getting started
---------------
### Including the JAR in your project

The easiest way to incorporate the JAR into your Java project is to use Maven. Simply add a new dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>com.nfbsoftware</groupId>
  <artifactId>java-xml</artifactId>
  <version>1.0.9</version>
</dependency>
```

Usage
-----
The easiest way to get started is to just use the XmlDocument class, like this:
					
```java	
IXmlDocument doc = new XmlDocument();
IXmlElement testElement = doc.createChild("Test", "");
        
testElement.createChild("TestOne", "something");
testElement.createChild("TestTwo", "something else");
testElement.createChild("TestThree", "yet another something");

System.out.println(doc.toString());
```

Getting a handle to an existing element

```java					
IXmlDocument doc = new XmlDocument();
IXmlElement testElement = doc.createChild("Test", "");
        
testElement.createChild("TestOne", "something");
testElement.createChild("TestTwo", "something else");
testElement.createChild("TestThree", "yet another something");

String elementValue = testElement.getChildValue("TestOne");
        
System.out.println("elementValue: " + elementValue);
```				
					
Creating an attribute on an element

```java					
IXmlDocument doc = new XmlDocument();
IXmlElement testElement = doc.createChild("Test", "");
        
testElement.createChild("TestOne", "something");
testElement.createChild("TestTwo", "something else");
        
IXmlElement thirdElement = testElement.createChild("TestThree", "yet another something");
thirdElement.setAttribute("ID", "123456789");
        
System.out.println(doc.toString());
```				
					
Get an element using xPath

```java						
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
            
System.out.println("thirdValue: " + nodeThree.getValue());
```		

Select/search for elements using xPath

```java						
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
            
// Make an XPath call to get a handle on children with a matching xpath
List<IXmlElement> elements = root.selectChildren("//element");
            
List<String> elementValues = new ArrayList<String>();
for(IXmlElement tmpElement : elements)
{
	String tmpValue = tmpElement.getValue();
                
    System.out.println("tmpValue: " + tmpValue);
}
```			

Select/search for a single element using xPath.  Exception is throw if multiple are found.

```java						
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
            
// Make an XPath call to get a handle to the child using an xpath query.  An exception will be thrown if multiple matches are found.
IXmlElement tmpElement = root.selectChild("//element[@id='2']");
                       
System.out.println("tmpValue: " + tmpElement.getValue());
```	
					
Get a CDATA Section

```java						
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
            
// Make an XPath call to get a handle on the column element
IXmlElement columnNode = root.getChild("one/column");
            
String cdataValue = columnNode.getCDATASection("value");
```					
					
Set a CDATA Section

```java					
IXmlDocument doc = new XmlDocument();
IXmlElement testElement = doc.createChild("Test", "");
            
testElement.createChild("TestOne", "something");
testElement.createChild("TestTwo", "something else");
            
IXmlElement cdataElement = testElement.createChild("TestCDATA", "");
cdataElement.setCDATASection("MYCDATATEST");
            
System.out.println(doc.toString());
```				
					
Creating an XmlDocument from a string

```java							
StringBuffer xmlString = new StringBuffer();
xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
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
```					
					
Creating an XmlDocument from a file

```java					
File myXmlFile = new File("my-xml-file.xml");
IXmlDocument doc = new XmlDocument(myXmlFile);
IXmlElement testElement = doc.createChild("Test", "");
        
testElement.createChild("TestOne", "something");
testElement.createChild("TestTwo", "something else");
testElement.createChild("TestThree", "yet another something");

String elementValue = testElement.getChildValue("TestOne");
        
System.out.println("elementValue: " + elementValue);
```