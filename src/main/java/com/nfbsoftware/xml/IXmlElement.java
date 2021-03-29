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
     * @exception IconstructsException
     */
    void addChild(IXmlElement child) throws XmlDocumentCheckedException;

    /**
     * This method appends the input IXmlElement object to the element identified by
     * the path.
     *
     * @param child the child node to append.
     * @param parent a string contains the path to the element to be appended to.
     *
     * @exception XmlDocumentCheckedException
     */
    void addToChild(IXmlElement child, String parent) throws XmlDocumentCheckedException;

    /**
     * This method creates a copy of the existing IXmlElement object, including a
     * clone of every child if and only if the deep parameter is set to be true.
     *
     * @param deep Indicates whether it is a deep clone or just cloning the node
     * @return IXmlDocument the new document object
     *
     * @exception XmlDocumentCheckedException
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
     * @exception XmlDocumentCheckedException
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
     * @exception XmlDocumentCheckedException
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
     * @exception XmlDocumentCheckedException
     */
    IXmlElement createChildren(String fullChildPath) throws XmlDocumentCheckedException;

    /**
     * This method returns the attribute's value according to input attribute name.
     *
     * @param String sName Attribute name
     *
     * @return A string contains attribute value
     *
     * @exception XmlDocumentCheckedException
     */
    String getAttribute(String name) throws XmlDocumentCheckedException;

    /**
     * This method returns all of the attribute names of the current node
     *
     * @return a Collection of attribute names
     *
     * @exception XmlDocumentCheckedException
     */
    Collection<String> getAttributeNames() throws XmlDocumentCheckedException;

    /**
     * This method returns all of the attribute values of the current node.
     *
     * @return a Collection of attribute values
     *
     * @exception XmlDocumentCheckedException
     */
    Collection<String> getAttributeValues() throws XmlDocumentCheckedException;

    /**
     * This method returns an IXmlElement object that contains the child element
     * at the given index.
     *
     * @param int index
     *
     * @return An IXmlElement object representing the child element
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlElement getChild(int index) throws XmlDocumentCheckedException;

    /**
     * This method returns the first child element identified by the path. If
     * multiple children with the given name exist, the first child found is
     * returned. This method throws an exception if no child element matches
     * the specified name.
     *
     * @param childName a string containint the path to the child element
     *
     * @return XmlDocument The first child node identified by the path
     *
     * @exception XmlDocumentCheckedException If there is no child element matches
     * the specified name, an exception with <code>XmlDocumentCheckedException.Codes.CHILD_NOT_FOUND
     * </code> is thrown. Exception for other reasons will have exception code <code>
     * XmlDocumentCheckedException.Codes.GENERAL_ERROR</code>
     */
    IXmlElement getChild(String childName) throws XmlDocumentCheckedException;
    
    /**
     * 
     * @param xpathQuery
     * @return
     * @throws XmlDocumentCheckedException
     */
    List<IXmlElement> selectChildren(String xpathQuery) throws XmlDocumentCheckedException;
    
    /**
     * 
     * @param xpathQuery
     * @return
     * @throws XmlDocumentCheckedException
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
     * @exception XmlDocumentCheckedException
     */
    String getChildAttribute(String child, String attr) throws XmlDocumentCheckedException;

    /**
     * This method returns the number of children.
     *
     * @return int Number of children
     *
     * @exception XmlDocumentCheckedException
     */
    int getChildCount() throws XmlDocumentCheckedException;

    /**
     * This method returns all of this element's children.
     *
     * @return a collection of the element's children
     *
     * @exception XmlDocumentCheckedException
     */
    Collection<IXmlElement> getChildren() throws XmlDocumentCheckedException;

    /**
     * This method returns all of this element's children with the given name.
     *
     * @param name
     * @return a collection of the element's children
     *
     * @exception XmlDocumentCheckedException
     */
    Collection<IXmlElement> getChildrenByName(String name) throws XmlDocumentCheckedException;

    /**
     * This method returns the descendent's value.
     *
     * @param childName identifies the child element
     *
     * @return the value of the child element
     *
     * @exception XmlDocumentCheckedException
     */
    String getChildValue(String childName) throws XmlDocumentCheckedException;

    /**
     * This method returns the descendent's value.
     *
     * @param childName identifies the child element
     *
     * @return the value of the child element
     *
     * @exception XmlDocumentCheckedException
     */
    String getCDATASection(String childName) throws XmlDocumentCheckedException;

    /**
     * This method returns the name of this element
     *
     * @return the element name
     *
     * @exception XmlDocumentCheckedException
     */
    String getName() throws XmlDocumentCheckedException;

    /**
     * This method returns the element's value
     *
     * @return the value of the element
     *
     * @exception XmlDocumentCheckedException
     */
    String getValue() throws XmlDocumentCheckedException;

    /**
     *
     * @return
     * @throws XmlDocumentCheckedException
     */
    Document getDocument() throws XmlDocumentCheckedException;

    /**
     *
     * @return
     * @throws XmlDocumentCheckedException
     */
    Node getRootNode() throws XmlDocumentCheckedException;

    /**
     * This method removes an attribute from this element..
     *
     * @param attributeName the name of the attribute
     *
     * @exception XmlDocumentCheckedException
     */
    void removeAttribute(String attributeName) throws XmlDocumentCheckedException;

    /**
     * This method removes the index-th child element.
     *
     * @param index the index of the child to remove
     *
     * @return the removed child
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlElement removeChild(int index) throws XmlDocumentCheckedException;

    /**
     * This method removes a child element.
     *
     * @param child the name of the element to remove
     *
     * @return the removed child
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlElement removeChild(String childName) throws XmlDocumentCheckedException;
    
    /**
     * This method removes a single child element with the given xpath query.
     *
     * @param xPath query to identify element
     *
     * @return the removed child
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlElement removeChildWithXpath(String xpathQuery) throws XmlDocumentCheckedException;
    
    /**
     * This method removes all child elements with the given xpath query.
     *
     * @param xPath query to identify elements
     *
     * @return the removed child
     *
     * @exception XmlDocumentCheckedException
     */
    List<IXmlElement> removeChildrenWithXpath(String xpathQuery) throws XmlDocumentCheckedException;

    /**
     * This method removes an attribute from a child element
     *
     * @param childName the name of the child element
     * @param attributeName the name of the attribute to remove
     *
     * @return the value of the removed attribute
     *
     * @exception XmlDocumentCheckedException
     */
    void removeChildAttribute(String child, String attributeName) throws XmlDocumentCheckedException;

    /**
     * Removes all of the element's children and adds all of the children of
     * the provided parent element.
     *
     * @param parent the element whose children are added to this element
     *
     * @exception XmlDocumentCheckedException
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
     * @exception XmlDocumentCheckedException
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
     * @exception XmlDocumentCheckedException
     */
    IXmlElement replaceChild(String childName, IXmlElement newChild) throws XmlDocumentCheckedException;

    /**
     * Sets the value of an attribute of this element. If the attribute doesn't
     * exist, it is created.
     *
     * @param attributeName the attribute name
     *
     * @param value the attribute value
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlElement setAttribute(String attributeName, String value) throws XmlDocumentCheckedException;

    /**
     * Sets this element's value
     *
     * @param value the new element value
     *
     * @exception XmlDocumentCheckedException
     */
    void setValue(String value) throws XmlDocumentCheckedException;

    /**
     * Sets this element's CDATA value
     *
     * @param value the new element value
     *
     * @exception Exception
     */
    void setCDATASection(String value) throws Exception;

    /**
     * This method saves the stored XML document as a file.
     *
     * @param file File in which to store the XML document
     *
     * @exception XmlDocumentCheckedException
     */
    void write(File file) throws XmlDocumentCheckedException;

    /**
     * This methods writes the content of the current XML document to the
     * output stream.
     *
     * @param OutputStream output Contains the output stream to write to
     *
     * @exception XmlDocumentCheckedException
     */
    void write(OutputStream output) throws XmlDocumentCheckedException;

}
