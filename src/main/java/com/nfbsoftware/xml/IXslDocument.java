package com.nfbsoftware.xml;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;

import com.nfbsoftware.xml.exception.XmlDocumentCheckedException;

/**
 *
 * @author Brendan Clemenzi 
* 
 */
public interface IXslDocument extends Serializable
{
    /**
     * This method replaces the content of the current root node
     * with that of the input file.
     *
     * @param file points to the XML file
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(File file) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the input file.
     *
     * @param file points to the XML file
     * @param encoding The name of the supported character encoding
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(File file, String encoding) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the InputStream.
     *
     * @param in A stream containing an XML document.
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(InputStream in) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the InputStream.
     *
     * @param in A stream containing an XML document.
     * @param encoding The name of the supported character encoding
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(InputStream in, String encoding) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the Reader.
     *
     * @param reader A reader containing an XML document.
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(Reader reader) throws XmlDocumentCheckedException;

    /**
     * This method replaces the content of the current root node
     * with that of the input string.
     *
     * @param xml A string contains an XML document.
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    void load(String xml) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlFile a file containing the XML document.
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String transform(File xmlFile) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlFile the file containing the XML document.
     * @param enc the encoding of the file
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String transform(File xmlFile, String enc) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param stream stream containing the XML document.
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String transform(InputStream stream) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param stream stream containing the XML document.
     * @param encoding the encoding of the stream
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String transform(InputStream stream, String encoding) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlElement a string containing the XML document
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String transform(IXmlElement xmlElement) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param reader a reader containing the XML document
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String transform(Reader reader) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xml a string containing the XML document
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    String transform(String xml) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlFile a file containing the XML document.
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlDocument transformToXmlDocument(File xmlFile) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlFile a file containing the XML document.
     * @param encoding the encoding of the stream
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlDocument transformToXmlDocument(File xmlFile, String encoding) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param stream stream containing the XML document.
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlDocument transformToXmlDocument(InputStream stream) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param stream stream containing the XML document.
     * @param encoding the encoding of the stream
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlDocument transformToXmlDocument(InputStream stream, String encoding) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xmlElement the XML document
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlDocument transformToXmlDocument(IXmlElement xmlElement) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param reader a reader containing the XML document
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlDocument transformToXmlDocument(Reader reader) throws XmlDocumentCheckedException;

    /**
     * This method transforms the xml document using this style sheet.
     *
     * @param xml a string containing the XML document
     * @return The XML document.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    IXmlDocument transformToXmlDocument(String xml) throws XmlDocumentCheckedException;
}

