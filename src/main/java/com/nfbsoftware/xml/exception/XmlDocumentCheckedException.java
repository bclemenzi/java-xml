package com.nfbsoftware.xml.exception;

import java.io.Serializable;

import com.nfbsoftware.exception.NfbCheckedException;
import com.nfbsoftware.util.Severity;

/**
 * The top level checked exception class for the XmlDocument project.
 * All checked exceptions encountered within the XmlDocument project must be
 * propagated as a <code>XmlDocumentCheckedException</code>, using one of the codes
 * defined in this class's <code>Codes</code> interface.
 *
 * @author Brendan Clemenzi 
 * @email brendan@clemenzi.com
 */
public class XmlDocumentCheckedException extends NfbCheckedException
{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Contains all of the message codes defined for this exception type as
     * well as any subclasses.
     *
     * @author Brendan Clemenzi
     */
    public static interface Codes
    {
                /** ERROR message with a code of XmlDocument:GeneralError: A general error has occurred: {0} */
        public static final Code GENERAL_ERROR = new Code("XmlDocument:GeneralError",
                                                          "A general error has occurred: {0}", Severity.ERROR);

        /** WARNING message with a code of XmlDocument:IncompatibleDocumentType: The XmlDocument implementation, {0} is incompatible with {1} */
        public static final Code INCOMPATIBLE_DOCUMENT_TYPE = new Code("XmlDocument:IncompatibleDocumentType",
                                                                       "The XmlDocument implementation, {0} is incompatible with {1}", Severity.WARNING);

        /** WARNING message with a code of XmlDocument:DocumentLoadFailed: Failed to load the document: {0} */
        public static final Code DOCUMENT_LOAD_FAILED = new Code("XmlDocument:DocumentLoadFailed",
                                                                 "Failed to load the document: {0}", Severity.WARNING);

        /** WARNING message with a code of XmlDocument:InvalidRootElement: The root element of the document is not appropriate for this adapter type. */
        public static final Code INVALID_ROOT_ELEMENT = new Code("XmlDocument:InvalidRootElement",
                                                                 "The root element of the document is not appropriate for this adapter type.", Severity.WARNING);

        /** WARNING message with a code of XmlDocument:NullRootElement: The root element of the document is null. */
        public static final Code NULL_ROOT_ELEMENT = new Code("XmlDocument:NullRootElement",
                                                              "The root element of the document is null.", Severity.WARNING);

        /** INFORMATION message with a code of XmlDocument:ChildNotFound: Child not found, path: {0} */
        public static final Code CHILD_NOT_FOUND = new Code("XmlDocument:ChildNotFound",
                                                            "Child not found, path: {0}", Severity.INFORMATIONAL);

        /** INFORMATION message with a code of XmlDocument:CloneFailed: Could not clone the child node: {0} */
        public static final Code CLONE_FAILED = new Code("XmlDocument:CloneFailed",
                                                         "Could not clone the child node: {0}", Severity.INFORMATIONAL);

        /** WARNING message with a code of XmlDocument:InvalidNodeName: An invalid node name was found: {0} */
        public static final Code INVALID_NODE_NAME = new Code("XmlDocument:InvalidNodeName",
                                                              "An invalid node name was found: {0}", Severity.WARNING);

        /** WARNING message with a code of XmlDocument:ErrorOnChildRemove: Error occured when trying to remove child: {0} */
        public static final Code ERROR_ON_CHILD_REMOVE = new Code("XmlDocument:ErrorOnChildRemove",
                                                                  "Error occured when trying to remove child: {0}", Severity.WARNING);

        /** WARNING message with a code of XmlDocument:ParentNotFound: Parent node not found: {0} */
        public static final Code PARENT_NOT_FOUND = new Code("XmlDocument:ParentNotFound",
                                                             "Parent node not found: {0}", Severity.WARNING);

        /** WARNING message with a code of XmlDocument:InvalidIndex: Invalid index. */
        public static final Code INVALID_INDEX = new Code("XmlDocument:InvalidIndex",
                                                          "Invalid index.", Severity.WARNING);
    }

    /**
     * Represents a <code>XmlDocumentCheckedException</code> code. This class is used to
     * enforce the practice of using a code defined for this exception class.
     * Instances of the exception cannot be constructed without providing a valid
     * code from this exception's internal <code>Codes</code> interface.
     *
     * @author Brendan Clemenzi
     */
    public static class Code extends NfbCheckedException.Code
    {
        /**
		 *
		 */
		private static final long serialVersionUID = 1L;

		/**
         * The private constructor ensures that no instances of this class are
         * constructed outside of the exception class.
         *
         * @param code the code
         * @param message the default message
         * @param severity the severity
         */
        private Code(String code, String message, int severity)
        {
            super(code, null, message, severity);
        }

        /**
         * The protected constructor is provided for subclasses to extend the base
         * code class. This constructor ensures that any subclass codes provide a
         * valid mapped message.
         *
         * @param code the code
         * @param mappedCode the mapped Code
         * @param message the default message
         * @param severity the severity
         */
        protected Code(String code, Code mappedCode, String message, int severity)
        {
            super(code, mappedCode, message, severity);
        }
    }

    {
        registerCode(Codes.GENERAL_ERROR);
        registerCode(Codes.INCOMPATIBLE_DOCUMENT_TYPE);
        registerCode(Codes.DOCUMENT_LOAD_FAILED);
        registerCode(Codes.INVALID_ROOT_ELEMENT);
        registerCode(Codes.NULL_ROOT_ELEMENT);
        registerCode(Codes.CHILD_NOT_FOUND);
        registerCode(Codes.CLONE_FAILED);
        registerCode(Codes.INVALID_NODE_NAME);
        registerCode(Codes.ERROR_ON_CHILD_REMOVE);
        registerCode(Codes.PARENT_NOT_FOUND);
        registerCode(Codes.INVALID_INDEX);
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance with the
     * specified code, message argument, debug information and a severity equal
     * to the severity of the code.
     * <p>
     * This is a convenience method that is a convenience method that
     * internally invokes <code>XmlDocumentCheckedException(String, Object[], String)</code>.
     *
     * @param code              the exception code
     * @param debugInformation  debug information
     *
     * @see XmlDocumentCheckedException#XmlDocumentCheckedException(XmlDocumentCheckedException.Code, Object[], String)
     */
    public XmlDocumentCheckedException(Code code, String debugInformation)
    {
        this(code, new Object[] {}, debugInformation);
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance with the
     * specified code, message arguments, debug information and severity.
     *
     * @param code              the exception code
     * @param debugInformation  debug information
     *
     * @param severity          the severity (based upon the constants defined in the SeveritySeverity class)
     */
    public XmlDocumentCheckedException(Code code, String debugInformation, int severity)
    {
        this(code, new Object[] {}, debugInformation, null, severity);
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance with the
     * specified code, message argument, debug information and a severity equal
     * to the severity of the code.
     * <p>
     * This is a convenience method that is a convenience method that
     * internally invokes <code>XmlDocumentCheckedException(String, Object[], String)</code>.
     *
     * @param code              the exception code
     * @param arg               an argument
     * @param debugInformation  debug information
     *
     * @see XmlDocumentCheckedException#XmlDocumentCheckedException(XmlDocumentCheckedException.Code, Object[], String)
     */
    public XmlDocumentCheckedException(Code code, Object arg, String debugInformation)
    {
        this(code, new Object[] { arg }, debugInformation);
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance with the
     * specified code, message arguments, debug information, and a severity equal
     * to the severity of the code.
     *
     * @param code              the exception code
     * @param args              an argument array
     * @param debugInformation  debug information
     */
    public XmlDocumentCheckedException(Code code, Object[] args, String debugInformation)
    {
        this(code, args, debugInformation, null, code.getSeverity());
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance with the
     * specified code, message argument, debug information, and severity.
     * <p>
     * This is a convenience method that is a convenience method that
     * internally invokes <code>XmlDocumentCheckedException(String, Object[], String,
     * int)</code>.
     *
     * @param code              the exception code
     * @param arg               an argument
     * @param debugInformation  debug information
     * @param severity          the severity (based upon the constants defined
     *                           in the <code>Severity</code> class)
     *
     * @see XmlDocumentCheckedException#XmlDocumentCheckedException(XmlDocumentCheckedException.Code, Object[], String, int)
     */
    public XmlDocumentCheckedException(Code code, Object arg, String debugInformation, int severity)
    {
        this(code, new Object[] { arg }, debugInformation, severity);
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance with the
     * specified code, message arguments, debug information and severity.
     *
     * @param code              the exception code
     * @param args              an argument array
     * @param debugInformation  debug information
     * @param severity          the severity (based upon the constants defined in the Severity class)
     */
    public XmlDocumentCheckedException(Code code, Object[] args, String debugInformation, int severity)
    {
        this(code, args, debugInformation, null, severity);
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance with the
     * specified code, message argument, debug information, root cause, and a
     * severity equal to the severity of the code.
     * <p>
     * This is a convenience method that is a convenience method that
     * internally invokes <code>XmlDocumentCheckedException(String, Object[], String,
     * Throwable)</code>.
     *
     * @param code              the exception code
     * @param arg               an argument
     * @param debugInformation  debug information
     * @param rootCause         the root cause
     *
     * @see XmlDocumentCheckedException#XmlDocumentCheckedException(XmlDocumentCheckedException.Code, Object[], String, Throwable)
     */
    public XmlDocumentCheckedException(Code code, Object arg, String debugInformation, Throwable rootCause)
    {
        this(code, new Object[] { arg }, debugInformation, rootCause);
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance with the
     * specified code, message arguments, debug information, root cause, and a
     * severity equal to the severity of the code.
     *
     * @param code              the exception code
     * @param args              an argument array
     * @param debugInformation  debug information
     * @param rootCause         the root cause
     */
    public XmlDocumentCheckedException(Code code, Object[] args, String debugInformation, Throwable rootCause)
    {
        this(code, args, debugInformation, rootCause, code.getSeverity());
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance with the
     * specified code, message argument, debug information, root cause, and
     * severity.
     * <p>
     * This is a convenience method that is a convenience method that
     * internally invokes <code>XmlDocumentCheckedException(String, Object[], String,
     * Throwable, int)</code>.
     *
     * @param code              the exception code
     * @param arg               an argument
     * @param debugInformation  debug information
     * @param rootCause         the root cause
     * @param severity          the severity (based upon the constants defined
     *                           in the <code>Severity</code> class)
     *
     * @see XmlDocumentCheckedException#XmlDocumentCheckedException(XmlDocumentCheckedException.Code, Object[], String, Throwable, int)
     */
    public XmlDocumentCheckedException(Code code, Object arg, String debugInformation, Throwable rootCause, int severity)
    {
        this(code, new Object[] { arg }, debugInformation, rootCause, severity);
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance with the
     * specified code, message arguments, debug information, root cause, and
     * severity.
     *
     * @param code              the exception code
     * @param args              an argument array
     * @param debugInformation  debug information
     * @param rootCause         the root cause
     * @param severity          the severity (based upon the constants defined
     *                           in the <code>Severity</code> class)
     *
     * @see NfbCheckedException.exception.NfbCheckedException#NfbCheckedException(NfbCheckedException.Code, Object[], String, Throwable, int)
     */
    public XmlDocumentCheckedException(Code code, Object[] args, String debugInformation, Throwable rootCause, int severity)
    {
        super(code, args, debugInformation, rootCause, severity);
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance.
     *
     * @param debugInformation  debug information
     * @param ex the exception to wrap
     *
     * @see NfbCheckedException.exception.NfbCheckedException#NfbCheckedException(NfbCheckedException)
     */
    public XmlDocumentCheckedException(String debugInformation, Throwable rootCause)
    {
        super();

        if (rootCause instanceof NfbCheckedException)
        {
            NfbCheckedException ex = (NfbCheckedException)rootCause;

            // Look for a mapping
            NfbCheckedException.Code code = ex.getCode();
            if (code instanceof Code)
            {
                NfbCheckedException.Code mappedCode = code.getMappedCode();
                if (mappedCode instanceof Code)
                {
                    mCode = mappedCode;
                    mArgs = ex.getArgs();
                }
            }
        }

        if (mCode == null)
        {
            mCode = Codes.GENERAL_ERROR;
            mArgs = new Serializable[] {rootCause};
        }

        mDebugInformation = debugInformation;
        mRootCause = rootCause;
        mSeverity = mCode.getSeverity();
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance.
     *
     * @param debugInformation  debug information
     * @param ex the exception to wrap
     *
     * @see NfbCheckedException.exception.NfbCheckedException#NfbCheckedException(NfbCheckedException)
     */
    public XmlDocumentCheckedException(String debugInformation, Throwable rootCause, int severity)
    {
        super();

        if (rootCause instanceof NfbCheckedException)
        {
            NfbCheckedException ex = (NfbCheckedException)rootCause;

            // Look for a mapping
            NfbCheckedException.Code code = ex.getCode();
            if (code instanceof Code)
            {
                NfbCheckedException.Code mappedCode = code.getMappedCode();
                if (mappedCode instanceof NfbCheckedException.Code)
                {
                    mCode = mappedCode;
                    mArgs = ex.getArgs();
                }
            }
        }

        if (mCode == null)
        {
            mCode = Codes.GENERAL_ERROR;
            mArgs = new Serializable[] {rootCause};
        }

        mDebugInformation = debugInformation;
        mRootCause = rootCause;
        mSeverity = severity;
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance.
     *
     * @param debugInformation  debug information
     * @param ex the exception to wrap
     *
     * @see NfbCheckedException.exception.NfbCheckedException#NfbCheckedException(NfbCheckedException)
     */
    public XmlDocumentCheckedException(Object arg, String debugInformation, Throwable rootCause)
    {
        super();

        if (rootCause instanceof NfbCheckedException)
        {
            NfbCheckedException ex = (NfbCheckedException)rootCause;

            // Look for a mapping
            NfbCheckedException.Code code = ex.getCode();
            if (code instanceof Code)
            {
                NfbCheckedException.Code mappedCode = code.getMappedCode();
                if (mappedCode instanceof NfbCheckedException.Code)
                {
                    mCode = mappedCode;
                }
            }
        }

        if (mCode == null)
        {
            mCode = Codes.GENERAL_ERROR;
        }

        if (arg != null)
        {
            if (arg instanceof Serializable)
            {
                mArgs = new Serializable[] {(Serializable)arg};
            }
            else
            {
                mArgs = new Serializable[] {arg.toString()};
            }
        }
        else
        {
            mArgs = new Serializable[] {};
        }

        mDebugInformation = debugInformation;
        mRootCause = rootCause;
        mSeverity = mCode.getSeverity();
    }

    /**
     * Constructs a <code>XmlDocumentCheckedException</code> instance.
     *
     * @param debugInformation  debug information
     * @param ex the exception to wrap
     *
     * @see NfbCheckedException.exception.NfbCheckedException#NfbCheckedException(NfbCheckedException)
     */
    public XmlDocumentCheckedException(Object[] args, String debugInformation, Throwable rootCause, int severity)
    {
        super();

        if (rootCause instanceof NfbCheckedException)
        {
            NfbCheckedException ex = (NfbCheckedException)rootCause;

            // Look for a mapping
            NfbCheckedException.Code code = ex.getCode();
            if (code instanceof Code)
            {
                NfbCheckedException.Code mappedCode = code.getMappedCode();
                if (mappedCode instanceof NfbCheckedException.Code)
                {
                    mCode = mappedCode;
                }
            }
        }

        if (mCode == null)
        {
            mCode = Codes.GENERAL_ERROR;
        }

        if (args != null)
        {
            int length = args.length;

            mArgs = new Serializable[length];

            for (int i = 0; i < length; i++)
            {
                Object arg = args[i];
                if (arg != null)
                {
                    if (arg instanceof Serializable)
                    {
                        mArgs[i] = (Serializable)arg;
                    }
                    else
                    {
                        mArgs[i] = arg.toString();
                    }
                }
            }
        }
        else
        {
            mArgs = new Serializable[] {};
        }

        mDebugInformation = debugInformation;
        mRootCause = rootCause;
        mSeverity = severity;
    }

    /**
     * Gets the default <code>Code</code>.
     *
     * @return Codes.GENERAL_ERROR
     */
    @Override
	protected NfbCheckedException.Code getDefaultCode()
    {
        return Codes.GENERAL_ERROR;
    }
}
