package com.nfbsoftware.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.nfbsoftware.util.StringUtil;
import com.nfbsoftware.xml.exception.XmlDocumentCheckedException;

/**
 * 
 * @author Brendan Clemenzi 
 * @email brendan@clemenzi.com
 */
@SuppressWarnings("restriction")
public class XmlDocument implements IXmlDocument
{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     *<B>mDocument</B> stores the Document Node of the XML document.
     *In W3C's DOM model, each XML document must have a Document node
     *and a root Element node.
     */
    protected org.w3c.dom.Document mDocument;

    /**
     *<B>mRootNode</B> stores the root Element node of the XML document.
     */
    protected org.w3c.dom.Node mRootNode;

    /**
     *
     */
    private transient DocumentBuilder mDocumentBuilder;

    /**
     *<B>sSeparator</B> contains the separator symbol used in child path.
     */
    protected final static String SEPARATOR = "/";

    private static class ErrorHandler extends DefaultHandler
    {
        @Override
		public void warning(final SAXParseException e) throws SAXException
        {
            //Do nothing.
        }

        @Override
		public void error(final SAXParseException e) throws SAXException
        {
            throw new SAXException(e);
        }

        @Override
		public void fatalError(final SAXParseException e) throws SAXException
        {
            throw new SAXException(e);
        }
    }

    /**
     *
     *
     *
     * @throws XmlDocumentCheckedException
     */
    public XmlDocument() throws XmlDocumentCheckedException
    {
        create();
    }

    /**
     *
     *
     *
     * @param file
     * @throws XmlDocumentCheckedException
     */
    public XmlDocument(File file) throws XmlDocumentCheckedException
    {
        this();

        final String debugString = getClass().getName() + ".XmlDocument(File)";

        try
        {
            if (!replaceFromStream(new FileInputStream(file)))
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
            }
        }
        catch (IOException ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     *
     *
     * @param file
     * @param enc
     * @throws XmlDocumentCheckedException
     */
    public XmlDocument(File file, String enc) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".XmlDocument(File, String)";

        try
        {
            if (!replaceFromStream(new FileInputStream(file), enc))
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
            }
        }
        catch (IOException ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     *
     *
     * @param in
     * @throws XmlDocumentCheckedException
     */
    public XmlDocument(InputStream in) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".XmlDocument(InputStream)";

        if (!replaceFromStream(in))
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
        }
    }

    /**
     *
     *
     *
     * @param in
     * @param enc
     * @throws XmlDocumentCheckedException
     */
    public XmlDocument(InputStream in, String enc) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".XmlDocument(InputStream, String)";

        try
        {
            if (!replaceFromStream(in, enc))
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
            }
        }
        catch (UnsupportedEncodingException ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     *
     *
     * @param reader
     * @throws XmlDocumentCheckedException
     */
    public XmlDocument(Reader reader) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".XmlDocument(Reader)";

        if (!replaceFromReader(reader))
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
        }
    }

    public XmlDocument(Node node, Document doc)
    {
        mRootNode = node;
        mDocument = doc;
    }

    public XmlDocument(IXmlDocument xmlDocument) throws XmlDocumentCheckedException
    {
        //final String debugString = getClass().getName() + ".XmlDocument(IXmlDocument)";

        mRootNode = xmlDocument.getRootNode();
        mDocument = xmlDocument.getDocument();

    }

    /**
     *
     *
     *
     * @param xml
     * @throws XmlDocumentCheckedException
     */
    public XmlDocument(final String xml) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".XmlDocument(String)";

        if (!replaceFromString(xml))
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
        }
    }

    /**
     *
     * @throws XmlDocumentCheckedException
     */
    private void create() throws XmlDocumentCheckedException
    {
        mDocumentBuilder = createDocumentBuilder();
        mDocument = mDocumentBuilder.newDocument();
        mRootNode = mDocument;
    }

    /**
     *
     * @param node
     * @param doc
     * @return
     */
    private XmlDocument create(org.w3c.dom.Node node, org.w3c.dom.Document doc)
    {
        return new XmlDocument(node, doc);
    }

    /**
     *
     * @param node
     * @param doc
     * @return
     */
    private IXmlElement create(org.w3c.dom.Node node) throws XmlDocumentCheckedException
    {
		Document doc = new DocumentImpl();
        Node rootNode = doc;

        Node child = cloneChildren(doc, node);
        rootNode.appendChild(child);

        return new XmlDocument(rootNode, doc).getRootElement();
    }

    /**
     *
     * @param child
     * @param value
     * @return
     * @throws XmlDocumentCheckedException
     */
    private XmlDocument createNewChild(String child, String value) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".createNewChild(String, String)";
        try
        {
            Node e = mDocument.createElement(child);
            mRootNode.appendChild(e);

            if (!StringUtil.isNullOrEmpty(value))
            {
                Node text = mDocument.createTextNode(value);
                e.appendChild(text);
            }
            return create(e, mDocument);
        }
        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }



    /**
     *
     *
     * @param child
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#addChild(xml.IXmlDocument)
     */
    public void addChild(IXmlElement child) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".addChild(IXmlElement)";

        try
        {
            Node root = child.getRootNode();

            if (mRootNode == null)
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.NULL_ROOT_ELEMENT, debugString);
            }
            mRootNode.appendChild(examineChild(child.getDocument(), root));
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     *
     * @param child
     * @param parent
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#addToChild(xml.IXmlDocument, java.lang.String)
     */
    public void addToChild(IXmlElement child, String parent) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".addToChild(IXmlElement, String)";

        try
        {
            if (child instanceof IXmlDocument)
            {
                child = ((IXmlDocument) child).getRootElement();
            }

            appendChildByName(parent, new XmlDocument(create(child.getRootNode(), child.getDocument())));
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     * @param parent
     * @param child
     * @throws XmlDocumentCheckedException
     */
    public void appendChildByName(String parent, XmlDocument child) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".addToChild(IXmlElement, String)";

        try
        {
            if (mRootNode == null)
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.NULL_ROOT_ELEMENT, debugString);
            }

            Node parentNode = prvGetChildByName(parent);

            if (parentNode == null)
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.PARENT_NOT_FOUND, debugString);
            }

            Node childRoot = child.getRootNode();

            parentNode.appendChild(examineChild(child.getDocument(),childRoot));
        }
        catch (XmlDocumentCheckedException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new XmlDocumentCheckedException(debugString, e);
        }
    }

    /**
     *
     *
     * @param deep
     * @return
     * @throws XmlDocumentCheckedException
     */
    public IXmlDocument clone(boolean deep) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".clone(boolean)";
        try
        {
            Node clonedNode = mRootNode.cloneNode(deep);
            return create(clonedNode, mDocument);
        }
        catch (Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param childName
     * @param value
     * @return
     * @throws XmlDocumentCheckedException
     */
    public IXmlElement createChild(String childName, String value) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".createChild(String, String)";

        IXmlElement results;

        try
        {
            results = createNewChild(childName, value);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     *
     *
     * @param fullChildPath
     * @return
     * @throws XmlDocumentCheckedException
     */
    public IXmlElement createChildren(String fullChildPath) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".createChildren(String)";

        IXmlElement results;

        try
        {
            results = this.createChildByName(fullChildPath);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     *This method creates a standing alone XmlData object with sName and sValue.
     *Note: NameSpace is not supported for all the implementations.
     *@param child Child name in XPath format, like REQUEST/HEADER/INFO
     */
    public XmlDocument createChildByName(String child) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".createChildByName(String)";
        try
        {
            Node childNode = prgCreateChildByName(child);
            return create(childNode, mDocument);
        }
        catch(XmlDocumentCheckedException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *This method returns the root node of the internal XML document.
     *@return root node
     */
    public Node getRootNode() throws XmlDocumentCheckedException
    {
        return mRootNode;
    }

    /**
     *This method returns the document node of the internal XML document.
     *@return document node
     */
    public Document getDocument() throws XmlDocumentCheckedException
    {
        return mDocument;
    }
    /**
     *This method returns the root node of the internal XML document.
     *@return root node
     */
    public  Node getPrivateElementNode()
    {
        return mRootNode;
    }

    /**
     *This method returns the document node of the internal XML document.
     *@return document node
     */
    public  Document getPrivateDocument()
    {
        return mDocument;
    }

    /**
     *
     *
     * @param name
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getAttribute(java.lang.String)
     */
    public String getAttribute(String name) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getAttribute(String)";

        String results;

        try
        {
            results = getAttributeByName(name);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     *
     * @param sName
     * @return
     * @throws XmlDocumentCheckedException
     */
    public final String getAttributeByName(String sName) throws XmlDocumentCheckedException
    {
        Node attr = mRootNode.getAttributes().getNamedItem(sName);

        if (attr == null)
        {
            return StringUtil.EMPTY_STRING;
        }
        else
        {
            return attr.getNodeValue();
        }
    }

    /**
     *
     *
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getAttributeNames()
     */
    public Collection<String> getAttributeNames() throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getAttributeNames()";

        Collection<String> results;

        try
        {
            int attributeCount = getAttributeCount();

            results = new ArrayList<String>(attributeCount);

            for (int i = 0; i < attributeCount; i++)
            {
                results.add(getAttributeNameByIndex(i));
            }
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     *
     *
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getAttributeValues()
     */
    public Collection<String> getAttributeValues() throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getAttributeValues()";

        Collection<String> results;

        try
        {
            Collection<String> attributeNames = getAttributeNames();

            int attributeCount = attributeNames.size();

            results = new ArrayList<String>(attributeCount);

            for (Iterator<String> it = attributeNames.iterator(); it.hasNext();)
            {
                results.add(getAttributeByName(it.next()));
            }
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     *
     *
     * @param index
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getChild(int)
     */
    public IXmlElement getChild(int index) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getChild(int)";
        try
        {
            Node child = getElementChildByIndex(mRootNode, index);

            if (child == null)
            {
                return null;
            }
            else
            {
                return create(child);
            }
        }
        catch(XmlDocumentCheckedException e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param childName
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getChild(java.lang.String)
     */
    public IXmlElement getChild(String childName) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getChild(String)";
        Node childNode;

        try
        {
            if (mRootNode == null)
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.NULL_ROOT_ELEMENT, debugString);
            }

            childNode = prvGetChildByName(childName);

            if (childNode == null)
            {
                return null;
            }
        }
        catch(Exception ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }

        return create(childNode);
    }

    /**
     *
     *
     * @param child
     * @param attr
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getChildAttribute(java.lang.String, java.lang.String)
     */
    public String getChildAttribute(String child, String attr) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getChildAttribute(String, String)";
        try
        {
            Node childNode = prvGetChildByName(child);

            if (childNode == null)
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.CHILD_NOT_FOUND, debugString);
            }
            else
            {
                Node attrNode = childNode.getAttributes().getNamedItem(attr);
                if (attr == null)
                {
                    return StringUtil.EMPTY_STRING;
                }
                else
                {
                    return attrNode.getNodeValue();
                }
            }
        }
        catch(XmlDocumentCheckedException xdce)
        {
            throw xdce;
        }
        catch(Exception ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     * @return
     * @throws XmlDocumentCheckedException
     */
    public final int getChildrenCount() throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getChildrenCount()";
        try
        {
            return getElementChildNodesLength(mRootNode.getChildNodes());
        }
        catch(XmlDocumentCheckedException xdce)
        {
            throw xdce;
        }
        catch(Exception ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     * @param childNodes
     * @return
     * @throws XmlDocumentCheckedException
     */
    private final int getElementChildNodesLength(NodeList childNodes) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getElementChildNodesLength(NodeList)";
        try
        {
            int length = childNodes.getLength();
            int size = 0;

            for (int i = 0; i < length; i++)
            {
                if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE)
                {
                    size++;
                }
            }
            return size;
        }
        catch(Exception ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     *
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getChildCount()
     */
    public int getChildCount() throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getChildCount()";

        int results = 0;

        try
        {
            results = getChildrenCount();
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
     *
     *
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getChildren()
     */
    public Collection<IXmlElement> getChildren() throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getChildren()";
        Collection<IXmlElement> results;

        try
        {
            int length = getElementChildNodesLength(mRootNode.getChildNodes());
            results = new ArrayList<IXmlElement>(length);

            for (int i = 0; i < length; i++)
            {
                Node child = getElementChildByIndex(mRootNode, i);
                results.add(create(child));
            }

            return results;
        }
        catch(XmlDocumentCheckedException xdce)
        {
            throw xdce;
        }
        catch(Exception ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     * @param name
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getChildren()
     */
    public Collection<IXmlElement> getChildrenByName(String name) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getChildrenByName";
        Collection<IXmlElement> results;

        try
        {
            int length = getElementChildNodesLength(mRootNode.getChildNodes());
            results = new ArrayList<IXmlElement>(length);

            for (int i = 0; i < length; i++)
            {
                Node child = getElementChildByIndex(mRootNode, i);

                if(child.getNodeName().equals(name))
                {
                    results.add(create(child));
                }
            }

            return results;
        }
        catch(XmlDocumentCheckedException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param childName
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getChildValue(java.lang.String)
     */
    public String getChildValue(String childName) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getChildValue(String)";

        String results;

        try
        {
            results = getNodeValue(childName);
        }
        catch (XmlDocumentCheckedException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }

        return results;
    }

    /**
    *
    *
    * @param childName
    * @return
    * @throws XmlDocumentCheckedException
    *
    * @see xml.IXmlDocument#getChildValue(java.lang.String)
    */
   public String getCDATASection(String childName) throws XmlDocumentCheckedException
   {
       final String debugString = getClass().getName() + ".getCDATASection(String)";

       String results;

       try
       {
           results = getCDATAValue(childName);
       }
       catch (XmlDocumentCheckedException ex)
       {
           throw new XmlDocumentCheckedException(debugString, ex);
       }

       return results;
   }

    /**
     *
     * @param sChild
     * @return
     * @throws XmlDocumentCheckedException
     */
    public final String getNodeValue(String sChild) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getNodeValue(String)";
        try
        {
            Node childNode = prvGetChildByName(sChild);

            if (childNode == null)
            {
                return StringUtil.EMPTY_STRING;
            }
            else
            {
                return getNodeTextValue(childNode);
            }
        }
        catch(XmlDocumentCheckedException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     * @param node
     * @return
     * @throws XmlDocumentCheckedException
     */
    protected final String getNodeTextValue(Node node) throws XmlDocumentCheckedException
    {
        NodeList children = node.getChildNodes();
        if (children != null)
        {
            int cnt = children.getLength();

            for ( int i = 0 ; i < cnt; i ++)
            {
                if (children.item(i).getNodeType() == Node.TEXT_NODE)
                {
                    return children.item(i).getNodeValue();
                }
            }
        }
        return StringUtil.EMPTY_STRING;
    }

    /**
    *
    * @param sChild
    * @return
    * @throws XmlDocumentCheckedException
    */
   public final String getCDATAValue(String sChild) throws XmlDocumentCheckedException
   {
       final String debugString = getClass().getName() + ".getCDATAValue(String)";
       try
       {
           Node childNode = prvGetChildByName(sChild);

           if (childNode == null)
           {
               return StringUtil.EMPTY_STRING;
           }
           else
           {
               return getCDATATextValue(childNode);
           }
       }
       catch(XmlDocumentCheckedException e)
       {
           throw e;
       }
       catch(Exception e)
       {
           throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
       }
   }

    /**
    *
    * @param node
    * @return
    * @throws XmlDocumentCheckedException
    */
   protected final String getCDATATextValue(Node node) throws XmlDocumentCheckedException
   {
       NodeList children = node.getChildNodes();
       if (children != null)
       {
           int cnt = children.getLength();

           for ( int i = 0 ; i < cnt; i ++)
           {
               if (children.item(i).getNodeType() == Node.CDATA_SECTION_NODE)
               {
                   return children.item(i).getNodeValue();
               }
           }
       }
       return StringUtil.EMPTY_STRING;
   }

    /**
     *
     *
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getName()
     */
    public String getName() throws XmlDocumentCheckedException
    {
        return mRootNode.getNodeName();
    }

    /**
     *
     *
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getNameSpace()
     */
    public String getNameSpace() throws XmlDocumentCheckedException
    {
        return (mRootNode).getNodeName();
    }

    /**
     *
     *
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#getValue()
     */
    public String getValue() throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getValue()";
        try
        {
            return getNodeTextValue(mRootNode);
        }
        catch(XmlDocumentCheckedException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param file
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#load(java.io.File)
     */
    public void load(File file) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".load(File)";

        try
        {
            if (!replaceFromStream(new FileInputStream(file)))
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
            }
        }
        catch (IOException ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     *
     * @param file
     * @param enc
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#load(java.io.File, java.lang.String)
     */
    public void load(File file, String enc) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".load(File, String)";

        try
        {
            if (!replaceFromStream(new FileInputStream(file), enc))
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
            }
        }
        catch (IOException ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     *
     * @param in
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#load(java.io.InputStream)
     */
    public void load(InputStream in) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".load(InputStream)";

        if (!replaceFromStream(in))
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
        }
    }

    /**
     *
     *
     * @param in
     * @param enc
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#load(java.io.InputStream, java.lang.String)
     */
    public void load(InputStream in, String enc) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".load(InputStream, String)";

        try
        {
            if (!replaceFromStream(in, enc))
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
            }
        }
        catch (UnsupportedEncodingException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     *
     * @param reader
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#load(java.io.Reader)
     */
    public void load(Reader reader) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".load(Reader)";

        if (!replaceFromReader(reader))
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
        }
    }

    /**
     *
     *
     * @param xml
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#load(java.lang.String)
     */
    public void load(String xml) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".load(String)";

        if (!replaceFromString(xml))
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.DOCUMENT_LOAD_FAILED, debugString);
        }
    }

    /**
     *
     *
     * @param attrName
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#removeAttribute(java.lang.String)
     */
    public void removeAttribute(String attrName) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".removeAttribute(String)";
        try
        {
            mRootNode.getAttributes().removeNamedItem(attrName);
        }
        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param index
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#removeChild(int)
     */
    public IXmlElement removeChild(int index) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".removeChild(int)";
        try
        {
            Node child = getElementChildByIndex(mRootNode, index);

            if (child == null)
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.INVALID_INDEX, debugString);
            }
            child.getParentNode().removeChild(child);

            return create( child, mDocument);
        }
        catch(XmlDocumentCheckedException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param childName
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#removeChild(java.lang.String)
     */
    public IXmlElement removeChild(String childName) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".removeChild(String)";
        try
        {
            Node xmlNode = prvGetChildByName(childName);

            if (xmlNode == null)
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.CHILD_NOT_FOUND, debugString);
            }
            xmlNode.getParentNode().removeChild(xmlNode);

            return create(xmlNode, mDocument);
        }
        catch(Exception ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     *
     * @param childName
     * @param attrName
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#removeChildAttribute(java.lang.String, java.lang.String)
     */
    public void removeChildAttribute(String childName, String attrName) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".removeChildAttribute(String, String)";
        try
        {
            Node xmlNode = prvGetChildByName(childName);

            if (xmlNode == null)
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.CHILD_NOT_FOUND, debugString);
            }
            xmlNode.getAttributes().removeNamedItem(attrName);
        }

        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param parent
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#replaceAllChildren(xml.IXmlDocument)
     */
    public void replaceAllChildren(IXmlElement parent) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".replaceAllChildren(IXmlElement)";
        try
        {
            NodeList children = mRootNode.getChildNodes();

            int length = children.getLength();

            Vector<Node> textNodes = new Vector<Node>(length);
            int nTextNodes = 0;

            while (children.getLength() != 0)
            {
                Node child = children.item(0);

                child = mRootNode.removeChild(child);

                if (child.getNodeType() == Node.TEXT_NODE)
                {
                    textNodes.addElement(child);
                    nTextNodes++;
                }

                children = mRootNode.getChildNodes();
            }
            if (nTextNodes > 0)
            {
                for (int i = 0; i < nTextNodes; i++)
                {
                    mRootNode.appendChild(textNodes.elementAt(i));
                }
            }
            Node parentNode = parent.getRootNode();

            if (parentNode == null)
            {
                return;
            }
            children = parentNode.getChildNodes();
            int cnt = 0;
            while (cnt < children.getLength())
            {
                Node child = children.item(cnt++);

                Node newChild = examineChild(parent.getDocument(), child);

                if (newChild != null)
                {
                    mRootNode.appendChild(newChild);
                }
            }
        }
        catch (XmlDocumentCheckedException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param index
     * @param newChild
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#replaceChild(int, xml.IXmlDocument)
     */
    public IXmlElement replaceChild(int index, IXmlElement newChild) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".replaceChild(int, IXmlElement)";
        try
        {
            Node child = getElementChildByIndex(mRootNode, index);

            if (child == null)
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.INVALID_INDEX, debugString);
            }
            Node newNode = (newChild).getRootNode();
            mRootNode.replaceChild(examineChild(newChild.getDocument(),
                                                newNode), child);
            return create( child, mDocument);
        }
        catch(XmlDocumentCheckedException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param childName
     * @param newChild
     * @return
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#replaceChild(java.lang.String, xml.IXmlDocument)
     */
    public IXmlElement replaceChild(String childName, IXmlElement newChild) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".replaceChild(String, IXmlElement)";
        try
        {
            Node xmlNode = prvGetChildByName(childName);

            if (xmlNode == null)
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.CHILD_NOT_FOUND, debugString);
            }
            Node newNode = newChild.getRootNode();

            xmlNode.getParentNode().replaceChild(examineChild(newChild.getDocument(),
                                                              newNode), xmlNode);

            return create (xmlNode, mDocument);
        }
        catch(XmlDocumentCheckedException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param attrName
     * @param value
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#setAttribute(java.lang.String, java.lang.String)
     */
    public void setAttribute(String attrName, String value) throws XmlDocumentCheckedException
    {
        Attr attr = (Attr) mRootNode.getAttributes().getNamedItem(attrName);

        if (attr == null)
        {
            Attr attrNew = mDocument.createAttribute(attrName);
            attrNew.setValue(value);
            // mRootNode.appendChild((Node) attrNew);

            NamedNodeMap attributes = mRootNode.getAttributes();
            attributes.setNamedItem(attrNew);

        }
        else
        {
            attr.setNodeValue(value);
        }
    }

    /**
     *
     * @param node
     * @param sValue
     * @throws XmlDocumentCheckedException
     */
    protected final void setTextValue(Node node, String sValue) throws XmlDocumentCheckedException
    {
        String value = sValue;

        if (value == null)
        {
            value = "";
        }

        NodeList children = node.getChildNodes();
        if (children != null)
        {
            int cnt = children.getLength();

            for (int i = 0 ; i < cnt; i ++)
            {
                if (children.item(i).getNodeType() == Node.TEXT_NODE)
                {
                    children.item(i).setNodeValue(value);
                    return;
                }
            }
        }

        if (value.length() > 0)
        {
            Text text = mDocument.createTextNode(value);
            node.appendChild(text);
        }
    }

    /**
    *
    * @param node
    * @param sValue
    * @throws Exception
    */
    protected final void setCDATASection(Node node, String sValue) throws Exception
    {
       String value = sValue;

       if (value == null)
       {
           value = "";
       }

       if (value.length() > 0)
       {
           Text text = mDocument.createCDATASection(value);
           node.appendChild(text);
       }
    }

    /**
    *
    *
    * @param value
    * @throws Exception
    *
    * @see xml.IXmlDocument#setValue(java.lang.String)
    */
    public void setCDATASection(String value) throws Exception
    {
        setCDATASection(mRootNode, value);
    }

    /**
     *
     *
     * @param value
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#setValue(java.lang.String)
     */
    public void setValue(String value) throws XmlDocumentCheckedException
    {
        setTextValue(mRootNode, value);
    }

    /**
     *
     *
     * @param file
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#write(java.io.File)
     */
    public void write(File file) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".write(File)";

        try
        {
            java.io.FileOutputStream output =
                new java.io.FileOutputStream(file);

            writeToStream(output);

            output.close();
        }
        catch (Exception ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     * @param output
     * @throws XmlDocumentCheckedException
     */
	private void writeToStream(OutputStream output) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".writeToStream(OutputStream)";
        try
        {
            OutputFormat format = new OutputFormat(mDocument);
            format.setIndenting(false);
            //format.setPreserveEmptyAttributes(true);
            format.setPreserveSpace(true);
            //Serialize DOM

            XMLSerializer serial = new XMLSerializer(output, format);
            serial.asDOMSerializer();
            // As a DOM Serializer

            if (mRootNode == mDocument)
            {
                serial.serialize(mDocument);
            }
            else
            {
                serial.serialize((Element) mRootNode);
            }
        }
        catch (IOException e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
        catch (Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     *
     * @param output
     * @throws XmlDocumentCheckedException
     *
     * @see xml.IXmlDocument#write(java.io.OutputStream)
     */
    public void write(OutputStream output) throws XmlDocumentCheckedException
    {
        writeToStream(output);
    }

    /**
     * Method getRootElement returns the root element for the xml document.
     * @return IXmlElement root xml element
     * @throws XmlDocumentCheckedException
     */
    public IXmlElement getRootElement() throws XmlDocumentCheckedException
    {
        //final String debugString = getClass().getName() + ".getRootElement()";

        return new XmlDocument(create(mDocument.getDocumentElement(), mDocument));
    }

    /**
     * Returns the document as a string.
     *
     *
     * @return
     *
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString()
    {
        StringWriter stringwriter = new StringWriter();
        try
        {
            OutputFormat outputformat = new OutputFormat();
            outputformat.setIndenting(true);
            outputformat.setLineWidth(0);
            XMLSerializer xmlserializer = new XMLSerializer(stringwriter, outputformat);
            xmlserializer.serialize(mDocument);
            stringwriter.close();
        }
        catch(Exception ex)
        {
            // Do nothing
        }

        return stringwriter.toString();
    }

    /**
     *
     * @return
     * @throws XmlDocumentCheckedException
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
     *This method replaces the content of the current root node
     *with that of the InputStream.
     *@param in A stream containing an XML document.
     *@return boolean Indicates success or failure
     */
    public boolean replaceFromStream(InputStream in)
    {
        return replaceFromReader(new InputStreamReader(in));
    }

    /**
     *This method replaces the content of the current root node
     *with that of the InputStream.
     *@param in A stream containing an XML document.
     *@param The name of the supported character encoding
     *@return boolean Indicates success or failure
     */
    public boolean replaceFromStream(InputStream in, String enc) throws java.io.UnsupportedEncodingException
    {
        return replaceFromReader(new InputStreamReader(in, enc));
    }

    /**
     * Description of the Method
     *
     * @author        Brendan Clemezi
     * @param reader  Description of Parameter
     * @return        Description of the Returned Value
     */
    public boolean replaceFromReader(Reader reader)
    {
        try
        {
            if (mDocumentBuilder == null)
            {
                mDocumentBuilder = createDocumentBuilder();
                mDocument = mDocumentBuilder.newDocument();
                mRootNode = mDocument;
            }
            //mDOMParser.parse(new InputSource(reader));
            mDocument = mDocumentBuilder.parse(new InputSource(reader));
            mRootNode = mDocument;
        }
        catch (Exception e)
        {
            // e.printStackTrace();
            mDocument = null;
            mRootNode = null;

            return false;
        }
        return true;
    }

    /**
     *This method replaces the content of the current root node
     *with that of the input string.
     *@param xml A string contains an XML document.
     *@return boolean Indicates success or failure
     */
    public final boolean replaceFromString(String xml)
    {
        return replaceFromReader(new StringReader(xml));
    }

    /**
     * Description of the Method
     *
     * @author            Brendan Clemezi
     * @param sChildName  Description of Parameter
     * @return            Description of the Returned Value
     */
    protected Node prvGetChildByName(String sChildName)
    {
        return prvGetChildByName(sChildName, mRootNode);
    }

    /**
     * Description of the Method
     *
     * @author            Brendan Clemezi
     * @param sChildName  Description of Parameter
     * @param rootNode    Description of Parameter
     * @return            Description of the Returned Value
     */
    protected Node prvGetChildByName(String sChildName, Node rootNode)
    {
        Node childNode = null;

        // Descend through path of tags until first matching child found
        String[] tagList = StringUtil.split(sChildName, SEPARATOR);
        synchronized (mDocument)
        {
            childNode = getChildNodeByTag(rootNode, tagList, 0);
        }

        return childNode;
    }

    /**
     * NOTE: Before calling this method, you must first wrap it in
     * synchronized(MDocument)
     *
     * @author          Brendan Clemezi
     * @param rootNode  Description of Parameter
     * @param tagList   Description of Parameter
     * @param index     Description of Parameter
     * @return          The ChildNodeByTag value
     */
    private Node getChildNodeByTag(Node rootNode, String[] tagList, int index)
    {
        if (index >= tagList.length)
        {
            return rootNode;
        }

        Node node = rootNode.getFirstChild();
        String tagName = tagList[index];
        while (node != null)
        {
            if (node.getNodeName().equals(tagName))
            {
                Node child = getChildNodeByTag(node, tagList, index + 1);
                if (child != null)
                {
                    return child;
                }
            }
            node = node.getNextSibling();
        }

        return null;
    }

    /**
     * Description of the Method
     *
     * @author                 Brendan Clemezi
     * @param rootDoc          Description of Parameter
     * @param child            Description of Parameter
     * @return                 Description of the Returned Value
     * @exception XmlDocumentCheckedException  Description of Exception
     */
    protected Node examineChild(Document rootDoc, Node child) throws XmlDocumentCheckedException
    {
        if (!mDocument.equals(rootDoc))
        {
            return cloneChildren(mDocument, child);
        }
        else
        {
            return child;
        }
    }

    /**
     * Clone a child node using the input document node
     *
     * @author                 Brendan Clemenzi
     * @param doc              Input document node
     * @param child            Child node to clone
     * @return                 Cloned child node
     * @exception XmlDocumentCheckedException  When fails to clone
     */
    public static Node cloneChildren(Document doc, Node child) throws XmlDocumentCheckedException
    {
        final String debugString = ".getElementChildByIndex(Node, int)";
        Node node = null;
        if (child.getNodeType() == Node.TEXT_NODE)
        {
            if (child.getNodeValue() == null)
            {
                node = null;
            }
            else
            {
                node = doc.createTextNode(child.getNodeValue());
            }
        }
        else if (child.getNodeType() == Node.ELEMENT_NODE)
        {
            node = doc.createElement(child.getNodeName());

            NamedNodeMap attributes = child.getAttributes();

            int length = attributes.getLength();

            for (int i = 0; i < length; i++)
            {
                Node attr = attributes.item(i);

                Node newAttr = doc.createAttribute(attr.getNodeName());
                newAttr.setNodeValue(attr.getNodeValue());

                node.getAttributes().setNamedItem(newAttr);
            }

            NodeList children = child.getChildNodes();

            int cnt = children.getLength();

            for (int i = 0; i < cnt; i++)
            {
                Node newChild = cloneChildren(doc, children.item(i));

                if (newChild != null)
                {
                    node.appendChild(newChild);
                }
                newChild = null;
            }
        }
        else if (child.getNodeType() == Node.COMMENT_NODE)
        {
            node = doc.createComment(child.getNodeValue());
        }
        else if (child.getNodeType() == Node.CDATA_SECTION_NODE)
        {
            node = doc.createCDATASection(child.getNodeValue());
        }
        else
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.CLONE_FAILED, debugString);
        }
        return node;
    }


    /**
     *
     * @param sChild
     * @return
     * @throws XmlDocumentCheckedException
     */
    private final Node prgCreateChildByName(String sChild) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".prgCreateChildByName(String)";
        try
        {
            Node childNode = prvGetChildByName(sChild);

            if (childNode == null)
            {
                // Now traverse the path from top to bottom, creating descedent
                // nodes along the way

                StringTokenizer childTokens  = new StringTokenizer(sChild, SEPARATOR);

                int size = childTokens.countTokens();

                Node rootNode = mRootNode;

                for (int i = 0; i < size; i++)
                {
                    String nextChildName = childTokens.nextToken();
                    childNode = prvGetChildByName(nextChildName, rootNode);

                    if (childNode == null)
                    {
                        Node newChild = mDocument.createElement(nextChildName);

                        rootNode.appendChild(newChild);

                        rootNode = newChild;

                        while(childTokens.hasMoreTokens())
                        {
                            nextChildName = childTokens.nextToken();

                            newChild = mDocument.createElement(nextChildName);

                            rootNode.appendChild(newChild);

                            rootNode = newChild;
                        }

                        return rootNode;
                    }
                    rootNode = childNode;
                }
            }

            return childNode;
        }
        catch(Exception e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
    }

    /**
     *
     * @return
     * @throws XmlDocumentCheckedException
     */
    public final int getAttributeCount() throws XmlDocumentCheckedException
    {
        return mRootNode.getAttributes().getLength();
    }

    /**
     *
     * @throws XmlDocumentCheckedException
     */
    public void isValid() throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".isValid()";

        String parserClass = "com.sun.org.apache.xerces.internal.parsers.SAXParser";
        String validationFeature = "http://xml.org/sax/features/validation";
        String schemaFeature = "http://apache.org/xml/features/validation/schema";

        try
        {
            XMLReader r = XMLReaderFactory.createXMLReader(parserClass);
            r.setFeature(validationFeature,true);
            r.setFeature(schemaFeature,true);
            r.setErrorHandler(new ErrorHandler());

            InputSource inputSource = new InputSource(new StringReader(this.toString()));

            r.parse(inputSource);
        }
        catch (SAXException e)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, e, debugString, e);
        }
        catch (IOException ex)
        {
            throw new XmlDocumentCheckedException(debugString, ex);
        }
    }

    /**
     *
     * @param index
     * @return
     * @throws XmlDocumentCheckedException
     */
    public final String getAttributeNameByIndex(int index) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getAttributeNameByIndex(int)";
        try
        {
            if ((index < 0) || (index >= mRootNode.getAttributes().getLength()))
            {
                throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.INVALID_INDEX, debugString);
            }

            return mRootNode.getAttributes().item(index).getNodeName();
        }
        catch(XmlDocumentCheckedException xdce)
        {
            throw xdce;
        }
        catch(Exception ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }

    /**
     *
     * @param parent
     * @param index
     * @return
     * @throws XmlDocumentCheckedException
     */
    private final Node getElementChildByIndex(Node parent, int index) throws XmlDocumentCheckedException
    {
        final String debugString = getClass().getName() + ".getElementChildByIndex(Node, int)";
        try
        {
            int cnt = 0;
            NodeList children = parent.getChildNodes();
            int len = children.getLength();
            for (int i = 0; i < len; i++)
            {
                if (children.item(i).getNodeType() != Node.ELEMENT_NODE)
                {
                    continue;
                }

                if (cnt == index)
                {
                    return children.item(i);
                }
                else
                {
                    cnt++;
                }
            }
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.INVALID_INDEX, debugString);
        }
        catch(XmlDocumentCheckedException xdce)
        {
            throw xdce;
        }
        catch(Exception ex)
        {
            throw new XmlDocumentCheckedException(XmlDocumentCheckedException.Codes.GENERAL_ERROR, ex, debugString, ex);
        }
    }
}


