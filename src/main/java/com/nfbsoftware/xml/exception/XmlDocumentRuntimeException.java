package com.nfbsoftware.xml.exception;

import java.io.Serializable;

import com.nfbsoftware.exception.NfbRuntimeException;

/**
 * The top level NFB Software runtime exception class for the XmlDocument project.
 * All runtime exceptions encountered within the XmlDocument project must be
 * propagated as a <code>XmlDocumentRuntimeException</code>, using one of the codes
 * defined in this class's <code>Codes</code> interface.
 *
 * @author Brendan Clemenzi 
* 
 */
public class XmlDocumentRuntimeException extends NfbRuntimeException
{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Contains all ofthe message codes defined for this exception type as
     * well as any subclasses.
     *
     * @author Brendan Clemenzi
     */
    public static interface Codes
    {
        /** FATAL message with a code of XmlDocument:GeneralFatalError: A general fatal error has occurrred: {0} */
        public static final Code GENERAL_FATAL_ERROR = new Code("XmlDocument:GeneralFatalError",
                                                                "A general fatal error has occurrred: {0}");
    }

    /**
     * Represents a <code>XmlDocumentRuntimeException</code> code. This class is used to
     * enforce the practice of using a code defined for this exception class.
     * Instances of the exception cannot be constructed without providing a valid
     * code from this exception's internal <code>Codes</code> interface.
     *
     * @author Brendan Clemenzi
     */
    public static class Code extends NfbRuntimeException.Code
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
         */
        private Code(String code, String message)
        {
            super(code, null, message);
        }

        /**
         * The protected constructor is provided for subclasses to extend the base
         * code class. This constructor ensures that any subclass codes provide a
         * valid mapped message.
         *
         * @param code the code
         * @param mappedCode the mapped Code
         * @param message the default message
         */
        protected Code(String code, Code mappedCode, String message)
        {
            super(code, mappedCode, message);
        }
    }

    {
        registerCode(Codes.GENERAL_FATAL_ERROR);
    }

    /**
     * Constructs a <code>XmlDocumentRuntimeException</code> instance with the
     * specified code, message argument, and debug information. If a log has
     * been configured for this exception type, the constructor logs the exception.
     * <p>
     * This is a convenience method that internally invokes
     * <code>XmlDocumentRuntimeException(String, Object[], String)</code>.
     *
     * @param code              the exception code
     * @param debugInformation  debug information
     *
     */
    public XmlDocumentRuntimeException(Code code, String debugInformation)
    {
        super(code, new Object[] {}, debugInformation, null);
    }

    /**
     * Constructs a <code>XmlDocumentRuntimeException</code> instance with the
     * specified code, message argument, and debug information. If a log has
     * been configured for this exception type, the constructor logs the exception.
     * <p>
     * This is a convenience method that is a convenience method that
     * internally invokes <code>XmlDocumentRuntimeException(String, Object[], String)</code>.
     *
     * @param code              the exception code
     * @param arg               an argument
     * @param debugInformation  debug information
     *
     */
    public XmlDocumentRuntimeException(Code code, Object arg, String debugInformation)
    {
        super(code, new Object[] { arg }, debugInformation, null);
    }

    /**
     * Constructs a <code>XmlDocumentRuntimeException</code> instance with the
     * specified code, message arguments, amd debug information. If a log has
     * been configured for this exception type, the constructor logs the exception.
     *
     * @param code              the exception code
     * @param args              an argument array
     * @param debugInformation  debug information
     */
    public XmlDocumentRuntimeException(Code code, Object[] args, String debugInformation)
    {
        super(code, args, debugInformation, null);
    }

    /**
     * Constructs a <code>XmlDocumentRuntimeException</code> instance with the
     * specified code, message argument, debug information, and root cause. If a log
     * has been configured for this exception type, the constructor logs the exception.
     * <p>
     * This is a convenience method that is a convenience method that
     * internally invokes <code>XmlDocumentRuntimeException(String, Object[], String,
     * Throwable)</code>.
     *
     * @param code              the exception code
     * @param arg               an argument
     * @param debugInformation  debug information
     * @param rootCause         the root cause
     *
     */
    public XmlDocumentRuntimeException(Code code, Object arg, String debugInformation, Throwable rootCause)
    {
        super(code, new Object[] { arg }, debugInformation, rootCause);
    }

    /**
     * Constructs a <code>XmlDocumentRuntimeException</code> instance with the
     * specified code, message arguments, debug information, and root cause.
     * If a log has been configured for this exception type, the constructor
     * logs the exception.
     *
     * @param code              the exception code
     * @param args              an argument array
     * @param debugInformation  debug information
     * @param rootCause         the root cause
     */
    public XmlDocumentRuntimeException(Code code, Object[] args, String debugInformation, Throwable rootCause)
    {
        super(code, args, debugInformation, rootCause);
    }

    /**
     * Constructs a <code>XmlDocumentRuntimeException</code> instance. If a
     * log has been configured for this exception type, the constructor logs
     * the exception.
     *
     * @param debugInformation  debug information
     * @param rootCause ex the exception to wrap
     *
     */
    public XmlDocumentRuntimeException(String debugInformation, Throwable rootCause)
    {
        super();

        if (rootCause instanceof RuntimeException)
        {
            NfbRuntimeException ex = (NfbRuntimeException)rootCause;

            // Look for a mapping
            NfbRuntimeException.Code code = ex.getCode();
            if (code instanceof Code)
            {
                NfbRuntimeException.Code mappedCode = code.getMappedCode();
                if (mappedCode instanceof Code)
                {
                    mCode = mappedCode;
                    mArgs = ex.getArgs();
                }
            }
        }

        if (mCode == null)
        {
            mCode = Codes.GENERAL_FATAL_ERROR;
            mArgs = new Serializable[] {rootCause};
        }

        mDebugInformation = debugInformation;
        mRootCause = rootCause;
    }

    /**
     * Constructs a <code>XmlDocumentRuntimeException</code> instance. If a
     * log has been configured for this exception type, the constructor logs
     * the exception.
     *
     * @param debugInformation  debug information
     * @param rootCause ex the exception to wrap
     *
     */
    public XmlDocumentRuntimeException(Object arg, String debugInformation, Throwable rootCause)
    {
        super();

        if (rootCause instanceof NfbRuntimeException)
        {
            NfbRuntimeException ex = (NfbRuntimeException)rootCause;

            // Look for a mapping
            NfbRuntimeException.Code code = ex.getCode();
            if (code instanceof Code)
            {
                NfbRuntimeException.Code mappedCode = code.getMappedCode();
                if (mappedCode instanceof NfbRuntimeException.Code)
                {
                    mCode = mappedCode;
                }
            }
        }

        if (mCode == null)
        {
            mCode = Codes.GENERAL_FATAL_ERROR;
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
    }

    /**
     * Gets the default <code>Code</code>.
     *
     * @return Codes.GENERAL_FATAL_ERROR
     */
    @Override
	protected NfbRuntimeException.Code getDefaultCode()
    {
        return Codes.GENERAL_FATAL_ERROR;
    }
}
