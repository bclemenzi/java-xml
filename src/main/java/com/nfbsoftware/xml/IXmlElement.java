package com.nfbsoftware.xml;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.nfbsoftware.xml.exception.XmlDocumentCheckedException;

/**
 * 
 * @author Brendan Clemenzi 
* 
 */
public interface IXmlElement extends Serializable
{
    /**
     * This appends an IXmlElement object to the this element.
     *
     * @param child the child node to append
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void addChild(IXmlElement child) throws XmlDocumentCheckedException;

    /**
     * This method appends the input IXmlElement object to the element identified by
     * the path.
     *
     * @param child the child node to append.
     * @param parent a string contains the path to the element to be appended to.
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void addToChild(IXmlElement child, String parent) throws XmlDocumentCheckedException;

    /**
     * This method creates a copy of the existing IXmlElement object, including a
     * clone of every child if and only if the deep parameter is set to be true.
     *
     * @param deep Indicates whether it is a deep clone or just cloning the node
     * @return IXmlDocument the new document object
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlDocument clone(boolean deep) throws XmlDocumentCheckedException;
    
    /**
     * This method creates a new child under this element with an element name
     * equal to the childName parameter.
     * The returned element is referenced directly by this element's document
     * after the operation.
     *
     * @param childName the child name
     *
     * @return XmlDocument Contains the newly created child node
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement createChild(String childName) throws XmlDocumentCheckedException;

    /**
     * This method creates a new child under this element with an element name
     * equal to the childName parameter and a value equal to the value parameter.
     * The returned element is referenced directly by this element's document
     * after the operation.
     *
     * @param childName the child name
     * @param value the child value
     *
     * @return XmlDocument Contains the newly created child node
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement createChild(String childName, String value) throws XmlDocumentCheckedException;

    /**
     * This method creates aall of the child elements referenced by the child
     * path. The returned element is referenced directly by this element's document
     * after the operation succeeds.
     *
     * @param fullChildPath the full list of elements to creat in XPath format (e.g., REQUEST/HEADER/INFO)
     *
     * @return IXmlElement the first element in the child path
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement createChildren(String fullChildPath) throws XmlDocumentCheckedException;

    /**
     * This method returns the attribute's value according to input attribute name.
     *
     * @param name Attribute name
     * @return A string contains attribute value
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String getAttribute(String name) throws XmlDocumentCheckedException;

    /**
     * This method returns all of the attribute names of the current node
     *
     * @return a Collection of attribute names
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    Collection<String> getAttributeNames() throws XmlDocumentCheckedException;

    /**
     * This method returns all of the attribute values of the current node.
     *
     * @return a Collection of attribute values
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    Collection<String> getAttributeValues() throws XmlDocumentCheckedException;

    /**
     * This method returns an IXmlElement object that contains the child element
     * at the given index.
     *
     * @param index The array number of the child.
     *
     * @return An IXmlElement object representing the child element
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement getChild(int index) throws XmlDocumentCheckedException;

    /**
     * This method returns the first child element identified by the path. If
     * multiple children with the given name exist, the first child found is
     * returned. This method throws an exception if no child element matches
     * the specified name.
     *
     * @param childName a string containing the path to the child element
     *
     * @return XmlDocument The first child node identified by the path
     *
     * @throws XmlDocumentCheckedException If the XML is invalid. If there is no child element matches
     * the specified name, an exception with <code>XmlDocumentCheckedException.Codes.CHILD_NOT_FOUND
     * </code> is thrown. Exception for other reasons will have exception code <code>
     * XmlDocumentCheckedException.Codes.GENERAL_ERROR</code>
     */
    IXmlElement getChild(String childName) throws XmlDocumentCheckedException;
    
    /**
     * 
     * @param xpathQuery The XPath string designated the desired children. 
     * @return A list of the matching children. If there are no matching children 
     * it will return an empty list.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    List<IXmlElement> selectChildren(String xpathQuery) throws XmlDocumentCheckedException;
    
    /**
     * 
     * @param xpathQuery The XPath string designated the desired child.
     * @return The element with the matching child. Returns null if there is no match.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement selectChild(String xpathQuery) throws XmlDocumentCheckedException;

    /**
     * This method returns the attribute value of a child node.
     *
     * @param child Identifies the child
     * @param attr Contains the attribute name
     *
     * @return A string contains the attribute value
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String getChildAttribute(String child, String attr) throws XmlDocumentCheckedException;

    /**
     * This method returns the number of children.
     *
     * @return Number of children
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    int getChildCount() throws XmlDocumentCheckedException;

    /**
     * This method returns all of this element's children.
     *
     * @return a collection of the element's children
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    Collection<IXmlElement> getChildren() throws XmlDocumentCheckedException;

    /**
     * This method returns all of this element's children with the given name.
     *
     * @param name of the desired children.
     * @return a collection of the element's children
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    Collection<IXmlElement> getChildrenByName(String name) throws XmlDocumentCheckedException;

    /**
     * This method returns the descendent's value.
     *
     * @param childName identifies the child element
     *
     * @return the value of the child element
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String getChildValue(String childName) throws XmlDocumentCheckedException;

    /**
     * This method returns the descendent's value.
     *
     * @param childName identifies the child element
     *
     * @return the value of the child element
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String getCDATASection(String childName) throws XmlDocumentCheckedException;

    /**
     * This method returns the name of this element
     *
     * @return the element name
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String getName() throws XmlDocumentCheckedException;

    /**
     * This method returns the element's value
     *
     * @return the value of the element
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String getValue() throws XmlDocumentCheckedException;

    /**
     *
     * @return The current document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    Document getDocument() throws XmlDocumentCheckedException;

    /**
     *
     * @return The root node.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    Node getRootNode() throws XmlDocumentCheckedException;

    /**
     * This method removes an attribute from this element..
     *
     * @param attributeName the name of the attribute
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void removeAttribute(String attributeName) throws XmlDocumentCheckedException;

    /**
     * This method removes the index-th child element.
     *
     * @param index the index of the child to remove
     *
     * @return the removed child
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement removeChild(int index) throws XmlDocumentCheckedException;

    /**
     * This method removes a child element.
     *
     * @param childName the name of the element to remove
     *
     * @return the removed child
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement removeChild(String childName) throws XmlDocumentCheckedException;
    
    /**
     * This method removes a single child element with the given xpath query.
     *
     * @param xpathQuery query to identify element
     *
     * @return the removed child
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement removeChildWithXpath(String xpathQuery) throws XmlDocumentCheckedException;
    
    /**
     * This method removes all child elements with the given xpath query.
     *
     * @param xpathQuery query to identify elements
     *
     * @return the removed child
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    List<IXmlElement> removeChildrenWithXpath(String xpathQuery) throws XmlDocumentCheckedException;

    /**
     * This method removes an attribute from a child element
     *
     * @param child the name of the child element
     * @param attributeName the name of the attribute to remove
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void removeChildAttribute(String child, String attributeName) throws XmlDocumentCheckedException;

    /**
     * Removes all of the element's children and adds all of the children of
     * the provided parent element.
     *
     * @param parent the element whose children are added to this element
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void replaceAllChildren(IXmlElement parent) throws XmlDocumentCheckedException;

    /**
     * This method replaces the index-th element with the new element.
     *
     * @param index the index of the child to replace
     *
     * @param newChild the child to add to this element
     *
     * @return the replaced element
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement replaceChild(int index, IXmlElement newChild) throws XmlDocumentCheckedException;

    /**
     * This method replaces the child element with the new element.
     *
     * @param childName the name of the child to replace
     *
     * @param newChild the child to add to this element
     *
     * @return the replaced element
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement replaceChild(String childName, IXmlElement newChild) throws XmlDocumentCheckedException;

    /**
     * Sets the value of an attribute of this element. If the attribute doesn't
     * exist, it is created.
     *
     * @param attributeName the attribute name
     *
     * @param value the attribute value
     * @return The element.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement setAttribute(String attributeName, String value) throws XmlDocumentCheckedException;

    /**
     * Sets this element's value
     *
     * @param value the new element value
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void setValue(String value) throws XmlDocumentCheckedException;

    /**
     * Sets this element's CDATA value
     *
     * @param value the new element value
     *
     * @throws Exception If anything bad happens.
     */
    void setCDATASection(String value) throws Exception;

    /**
     * This method saves the stored XML document as a file.
     *
     * @param file File in which to store the XML document
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void write(File file) throws XmlDocumentCheckedException;

    /**
     * This methods writes the content of the current XML document to the
     * output stream.
     *
     * @param output Contains the output stream to write to
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void write(OutputStream output) throws XmlDocumentCheckedException;

}
