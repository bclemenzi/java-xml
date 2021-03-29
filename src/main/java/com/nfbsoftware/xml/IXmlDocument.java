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
     */
    String getNameSpace() throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the input file.
     *
     * @param file points to the XML file
     *
     * @return boolean Indicates success or failure
     */
    void load(File file) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the input file.
     *
     * @param file points to the XML file
     * @param The name of the supported character encoding
     *
     * @return boolean Indicates success or failure
     */
    void load(File file, String enc) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the Reader.
     *
     * @param in A reader containing an XML document.
     *
     * @return boolean Indicates success or failure
     */
    void load(Reader reader) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the InputStream.
     *
     * @param in A stream containing an XML document.
     *
     * @return boolean Indicates success or failure
     */
    void load(InputStream in) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the InputStream.
     *
     * @param in A stream containing an XML document.
     * @param The name of the supported character encoding
     *
     * @return boolean Indicates success or failure
     */
    void load(InputStream in, String enc) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the input string.
     *
     * @param xml A string contains an XML document.
     *
     * @return boolean Indicates success or failure
     */
    void load(String xml) throws XmlDocumentCheckedException;

    /**
     * Method getRootElement returns the root element for the xml document.
     * @return IXmlElement root xml element
     * @throws XmlDocumentCheckedException
     */
    IXmlElement getRootElement()  throws XmlDocumentCheckedException;

    /**
     * Determine if the xml document in question
     * @throws XmlDocumentCheckedException
     */
    void isValid() throws XmlDocumentCheckedException;
}
