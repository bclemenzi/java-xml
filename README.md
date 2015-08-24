# java-xml
Java-XML is Java library designed to help simplify the building and parsing of XML documents

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