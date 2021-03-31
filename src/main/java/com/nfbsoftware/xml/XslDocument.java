package com.nfbsoftware.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

import com.nfbsoftware.xml.exception.XmlDocumentCheckedException;


/**
 *
 *
 * @author Brendan Clemenzi 
* 
 */
public class XslDocument implements IXslDocument
{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     *
     */
    private transient DocumentBuilder mDocumentBuilder;

    /** */
    private IXmlDocument mXslDocument;

    /** */
    private IXmlElement mXslElement;

    /**
     *
     */
    private transient TransformerFactory mTransformerFactory;

    /**
     *
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    public XslDocument() throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + "<init>()";

        try
        {
            mXslDocument = new XmlDocument();
            mXslElement = mXslDocument.getRootElement();
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     *
     *
     * @param file File of xsl
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    public XslDocument(File file) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + "<init>(File)";

        try
        {
            mXslDocument = new XmlDocument(file);
            mXslElement = mXslDocument.getRootElement();
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     *
     *
     * @param file of XSL
     * @param enc The encoding.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    public XslDocument(File file, String enc) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + "<init>(File, String)";

        try
        {
            mXslDocument = new XmlDocument(file, enc);
            mXslElement = mXslDocument.getRootElement();
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     *
     *
     * @param in inputstream with XSL.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    public XslDocument(InputStream in) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + "<init>(InputStream)";

        try
        {
            mXslDocument = new XmlDocument(in);
            mXslElement = mXslDocument.getRootElement();
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     *
     *
     * @param in Inputstream with XSL
     * @param enc Encoding.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    public XslDocument(InputStream in, String enc) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + "<init>(InputStream, String)";

        try
        {
            mXslDocument = new XmlDocument(in, enc);
            mXslElement = mXslDocument.getRootElement();
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     *
     *
     * @param reader A reader with XSL.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    public XslDocument(Reader reader) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + "<init>(Reader)";

        try
        {
            mXslDocument = new XmlDocument(reader);
            mXslElement = mXslDocument.getRootElement();
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     *
     *
     * @param xml A string containing XSL.
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    public XslDocument(String xml) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + "<init>(String)";

        try
        {
            mXslDocument = new XmlDocument(xml);
            mXslElement = mXslDocument.getRootElement();
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(File file) throws XmlDocumentCheckedException
    {
        mXslDocument.load(file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(File file, String enc) throws XmlDocumentCheckedException
    {
        mXslDocument.load(file, enc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(InputStream in) throws XmlDocumentCheckedException
    {
        mXslDocument.load(in);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(InputStream in, String enc) throws XmlDocumentCheckedException
    {
        mXslDocument.load(in, enc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(Reader reader) throws XmlDocumentCheckedException
    {
        mXslDocument.load(reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(String xml) throws XmlDocumentCheckedException
    {
        mXslDocument.load(xml);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(File xmlFile) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transform(File)";

        String results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(xmlFile);
            results = transformNodeFromXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(File xmlFile, String enc) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transform(File, String)";

        String results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(xmlFile, enc);
            results = transformNodeFromXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(InputStream stream) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transform(InputStream)";

        String results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(stream);
            results = transformNodeFromXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(InputStream stream, String enc) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transform(InputStream, String)";

        String results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(stream, enc);
            results = transformNodeFromXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(IXmlElement xmlElement) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transform(IXmlElement)";

        String results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(xmlElement.getRootNode(), xmlElement.getDocument());
            results = transformNodeFromXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(Reader reader) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transform(Reader)";

        String results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(reader);
            results = transformNodeFromXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(String xml) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transform(String)";

        String results = null;

        try
        {
            results = transformNodeFromString(xml);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IXmlDocument transformToXmlDocument(File xmlFile) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transformToXmlDocument(File)";

        IXmlDocument results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(xmlFile);
            results = transformNodeFromXmlDataToXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IXmlDocument transformToXmlDocument(File xmlFile, String enc) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transformToXmlDocument(File, String)";

        IXmlDocument results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(xmlFile, enc);
            results = transformNodeFromXmlDataToXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IXmlDocument transformToXmlDocument(InputStream stream) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transformToXmlDocument(InputStream)";

        IXmlDocument results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(stream);
            results = transformNodeFromXmlDataToXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IXmlDocument transformToXmlDocument(InputStream stream, String enc) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transformToXmlDocument(InputStream, String)";

        IXmlDocument results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(stream, enc);
            results = transformNodeFromXmlDataToXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IXmlDocument transformToXmlDocument(IXmlElement xmlElement) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transformToXmlDocument(IXmlElement)";

        IXmlDocument results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(xmlElement.getRootNode(), xmlElement.getDocument());
            results = transformNodeFromXmlDataToXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IXmlDocument transformToXmlDocument(Reader reader) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transformToXmlDocument(Reader)";

        IXmlDocument results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(reader);
            results = transformNodeFromXmlDataToXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IXmlDocument transformToXmlDocument(String xml) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transformToXmlDocument(String)";

        IXmlDocument results = null;

        try
        {
            IXmlDocument doc = new XmlDocument(xml);
            results = transformNodeFromXmlDataToXmlData(doc);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public String toString()
    {
        if (mXslDocument != null)
        {
            return mXslDocument.toString();
        }
        else
        {
            return super.toString();
        }
    }

    /**
     *
     * @return
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    private DocumentBuilder createDocumentBuilder() throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".createDocumentBuilder()";
        try
        {
            DocumentBuilderFactory docBuidlerFactory = DocumentBuilderFactory.newInstance();
            docBuidlerFactory.setNamespaceAware(true);

            return docBuidlerFactory.newDocumentBuilder();
        }
        catch (Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     * @param xslStr
     * @return
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    private String transformNodeFromString(String xslStr) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".transformNodeFromString(String)";
        // parser input XSL file
        ByteArrayInputStream input = new ByteArrayInputStream(xslStr.getBytes());
        ByteArrayOutputStream out;

        try
        {
            out = new ByteArrayOutputStream();

            if (mTransformerFactory == null)
            {
                synchronized (this)
                {
                    mTransformerFactory = TransformerFactory.newInstance();
                }
            }

            // Use the TransformerFactory to instantiate a Transformer that will work with
            // the stylesheet you specify. This method call also processes the stylesheet
            // into a compiled Templates object.
            StringReader xslStream = new StringReader(mXslDocument.toString());
            Transformer transformer = mTransformerFactory.newTransformer(new StreamSource(xslStream));

            // Use the Transformer to apply the associated Templates object to an XML document
            // (foo.xml) and write the output to a file (foo.out).
            transformer.transform(new StreamSource(input), new StreamResult(out));

            return out.toString("UTF-8");
        }
        catch (Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     * @param xslNode
     * @return
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    private String transformNodeFromXmlData(IXmlDocument xmlNode) throws XmlDocumentCheckedException
    {
        synchronized (xmlNode)
        {
            ByteArrayOutputStream out;

            try
            {
                out = new ByteArrayOutputStream();

                if (mTransformerFactory == null)
                {
                    synchronized (this)
                    {
                        mTransformerFactory = TransformerFactory.newInstance();
                    }
                }

                // Use the TransformerFactory to instantiate a Transformer that will work with
                // the stylesheet you specify. This method call also processes the stylesheet
                // into a compiled Templates object.
                StringReader xslStream = new StringReader(mXslDocument.toString());
                Transformer transformer = mTransformerFactory.newTransformer(new StreamSource(xslStream));

                // Use the Transformer to apply the associated Templates object to an XML document
                // (foo.xml) and write the output to a file (foo.out).
                transformer.transform(new DOMSource(xmlNode.getDocument()), new StreamResult(out));

                return out.toString("UTF-8");
            }
            catch (Exception e)
            {
                throw new XmlDocumentCheckedException("XmlDocument.transfromNodeFromString", e);
            }
        }
    }

    /**
     *
     * @param xslNode
     * @return
     * @throws XmlDocumentCheckedException If the XML is invalid.
     */
    private XmlDocument transformNodeFromXmlDataToXmlData(IXmlDocument xmlNode) throws XmlDocumentCheckedException
    {
        synchronized (xmlNode)
        {
            try
            {
                if (mTransformerFactory == null)
                {
                    synchronized (this)
                    {
                        mTransformerFactory = TransformerFactory.newInstance();
                    }
                }

                // Use the TransformerFactory to instantiate a Transformer that will work with
                // the stylesheet you specify. This method call also processes the stylesheet
                // into a compiled Templates object.
                StringReader xslStream = new StringReader(mXslDocument.toString());
                Transformer transformer = mTransformerFactory.newTransformer(new StreamSource(xslStream));

                // Use the Transformer to apply the associated Templates object to an XML document
                // (foo.xml) and write the output to a file (foo.out).

                synchronized (this)
                {
                    if (mDocumentBuilder == null)
                    {
                        mDocumentBuilder = createDocumentBuilder();
                    }
                }

                Document doc = mDocumentBuilder.newDocument();
                transformer.transform(new DOMSource(xmlNode.getDocument()), new DOMResult(doc));

                return new XmlDocument(doc, doc);
            }
            catch (Exception e)
            {
                throw new XmlDocumentCheckedException("XmlDocument.transfromNodeFromXmlDocumentToXmlDocument", e);
            }
        }
    }
}

