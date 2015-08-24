package com.nfbsoftware.xml;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;

import com.nfbsoftware.xml.exception.XmlDocumentCheckedException;

/**
 *
 * @author Brendan Clemenzi 
 * @email brendan@clemenzi.com
 */
public interface IXslDocument extends Serializable
{
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
     * with that of the Reader.
     *
     * @param in A reader containing an XML document.
     *
     * @return boolean Indicates success or failure
     */
    void load(Reader reader) throws XmlDocumentCheckedException;

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
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlFile a file containing the XML document.
     *
     * @exception XmlDocumentCheckedException
     */
    String transform(File xmlFile) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlFile the file containing the XML document.
     * @param enc the encoding of the file
     *
     * @exception XmlDocumentCheckedException
     */
    String transform(File xmlFile, String enc) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param stream stream containing the XML document.
     *
     * @exception XmlDocumentCheckedException
     */
    String transform(InputStream stream) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param stream stream containing the XML document.
     * @param enc the encoding of the stream
     *
     * @exception XmlDocumentCheckedException
     */
    String transform(InputStream stream, String enc) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlElement a string containing the XML document
     *
     * @exception XmlDocumentCheckedException
     */
    String transform(IXmlElement xmlElement) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param reader a reader containing the XML document
     *
     * @exception XmlDocumentCheckedException
     */
    String transform(Reader reader) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xml a string containing the XML document
     *
     * @exception XmlDocumentCheckedException
     */
    String transform(String xml) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlFile a file containing the XML document.
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlDocument transformToXmlDocument(File xmlFile) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlFile a file containing the XML document.
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlDocument transformToXmlDocument(File xmlFile, String enc) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param stream stream containing the XML document.
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlDocument transformToXmlDocument(InputStream stream) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param stream stream containing the XML document.
     * @param enc the encoding of the stream
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlDocument transformToXmlDocument(InputStream stream, String enc) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlElement the XML document
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlDocument transformToXmlDocument(IXmlElement xmlElement) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param reader a reader containing the XML document
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlDocument transformToXmlDocument(Reader reader) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xml a string containing the XML document
     *
     * @exception XmlDocumentCheckedException
     */
    IXmlDocument transformToXmlDocument(String xml) throws XmlDocumentCheckedException;
}

