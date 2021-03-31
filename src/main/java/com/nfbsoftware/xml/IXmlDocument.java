package com.nfbsoftware.xml;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

import com.nfbsoftware.xml.exception.XmlDocumentCheckedException;

/**
 * 
 * @author Brendan Clemenzi 
* 
 */
public interface IXmlDocument extends IXmlElement
{
    /**
     * This method returns the namespace of the current node.
     *
     * @return The namespace of the current node.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String getNameSpace() throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the input file.
     *
     * @param file points to the XML file
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(File file) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the input file.
     *
     * @param file points to the XML file
     * @param encoding The name of the supported character encoding
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(File file, String encoding) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the Reader.
     *
     * @param reader A reader containing an XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(Reader reader) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the InputStream.
     *
     * @param in A stream containing an XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(InputStream in) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the InputStream.
     *
     * @param in A stream containing an XML document.
     * @param encoding The name of the supported character encoding
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(InputStream in, String encoding) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the input string.
     *
     * @param xml A string contains an XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(String xml) throws XmlDocumentCheckedException;

    /**
     * Method getRootElement returns the root element for the xml document.
     * @return IXmlElement root xml element
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlElement getRootElement()  throws XmlDocumentCheckedException;

    /**
     * Determine if the xml document in question
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void isValid() throws XmlDocumentCheckedException;
}
