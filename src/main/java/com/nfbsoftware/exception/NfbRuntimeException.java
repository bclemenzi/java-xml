package com.nfbsoftware.exception;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.nfbsoftware.util.Severity;

/**
 * The single runtime exception class. All checked exceptions encountered within the NFB Software Core must be propogated
 * as an runtimeException.
 *
 * @author Brendan Clemenzi 
 * @email brendan@clemenzi.com
 */
public abstract class NfbRuntimeException extends RuntimeException
{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The MessageFormat used by the toString method.
     */
    protected static final String TO_STRING_MESSAGE_FORMAT = "[{0} - {1} - {2} - {3}]";

    /**
     * Contains all of the codes defined by class in the runtimeException class hierarchy
     */
    private static final Map<String, Code> CODE_MAP = new HashMap<String, Code>(31);

    /**
     * The error code of this exception class.
     */
    protected Code mCode;

    /**
     * The argument array that holds each of the arguments associated with this exception.
     */
    protected Serializable[] mArgs;

    /**
     * Debug information related to this exception class. This is typically set to the class and method name (e.g. Foo.bar).
     */
    protected String mDebugInformation;

    /**
     * The root cause of this exception class.
     */
    protected Throwable mRootCause;

    /**
     * The severity of this exception class.
     */
    protected int mSeverity;

    /**
     * The stack trace of this exception class as a String.
     */
    protected String mStackTraceString;

    /**
     * Represents a <code>runtimeException</code> code. This class is used to
     * enforce the practice of using a code defined for this exception class.
     * Instances of the exception cannot be constructed without providing a valid
     * code. Subclasses of this class must implement a subclass of this protected
     * class. Typically, subclass constructors will take this <code>Code</code>
     * subclass in an internal <code>Codes</code> interface.
     *
     * @author Brendan Clemenzi
     */
    protected static class Code implements Serializable
    {
        static final long serialVersionUID = 0;

        /** The mapped code value */
        private Code mMappedCode;

        /** The code value */
        private String mCode;

        /** The default message */
        private String mMessage;

        /**
         * The protected constructor ensures that no instances of this class are
         * constructed outside of the exception class or its children.
         *
         * @param code the code
         * @param mappedCode the top-level code that this code should map to
         * @param message the default message
         */
        protected Code(String code, Code mappedCode, String message)
        {
            mCode = code;
            mMappedCode = mappedCode;
            mMessage = message;
        }

        /**
         * Returns the code's value
         *
         * @return the code's value
         */
        public String getCode()
        {
            return mCode;
        }

        /**
         * Returns the code's mapped code
         *
         * @return the code's mapped code value
         */
        public Code getMappedCode()
        {
            return mMappedCode;
        }

        /**
         * Returns the code's default message.
         *
         * @return the code's default message
         */
        public String getMessage()
        {
            return mMessage;
        }

        /**
         * Returns the Code's value as a String.
         *
         * @return the code's value
         *
         * @see java.lang.Object#toString()
         */
        @Override
		public String toString()
        {
            return mCode;
        }
    }

    /**
     * Constructs a <code>runtimeException</code> instance.
     */
    public NfbRuntimeException()
    {
    }

    /**
     * Constructs an runtimeException instance with the specified code, args, debugInformation and severity.
     *
     * @param code              the exception code
     * @param args              an argument array
     * @param debugInformation  debug information
     * @param rootCause         the root cause
     * @param severity          the severity (based upon the constants defined in the util.Severity class)
     */
    public NfbRuntimeException(Code code, Object[] args, String debugInformation, Throwable rootCause)
    {
        mCode             = code;
        mDebugInformation = debugInformation;
        mRootCause        = rootCause;
        mSeverity         = Severity.FATAL;

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
    }

    /**
     * Gets the default <code>Code</code>.
     *
     * @return the default code
     */
    protected abstract Code getDefaultCode();

    /**
     * Gets the exception code name.
     *
     * @return the code name
     */
    public String getCodeName()
    {
        return mCode.getCode();
    }

    /**
     * Gets the exception code.
     *
     * @return the code as a <code>Code</code> object
     */
    public Code getCode()
    {
        return mCode;
    }

    /**
     * Gets the argument array associated with the exception code.
     *
     * @return the arguement array.
     */
    public Serializable[] getArgs()
    {
        return mArgs;
    }

    /**
     * Gets the debug information.
     *
     * @return the debug information.
     */
    public String getDebugInformation()
    {
        return mDebugInformation;
    }

    /**
     * Gets the severity.
     *
     * @return the severity.
     */
    public Throwable getRootCause()
    {
        return mRootCause;
    }

    /**
     * Gets the severity.
     *
     * @return the severity.
     */
    public int getSeverity()
    {
        return mSeverity;
    }

    /**
     * Gets the severity as a String. Returns one of the following:
     * <ul>
     * <li>FATAL
     * <li>ERROR
     * <li>WARNING
     * <li>INFO
     * <li>SUCCESS
     * <li>DEBUG
     * </ul>
     *
     * @return the severity as a String.
     */
    public String getSeverityAsString()
    {
        switch (mSeverity)
        {
            case Severity.FATAL:           return "FATAL";
            case Severity.ERROR:           return "ERROR";
            case Severity.WARNING:         return "WARNING";
            case Severity.INFORMATIONAL:   return "INFO";
            case Severity.SUCCESS:         return "SUCCESS";
            case Severity.DEBUG:           return "DEBUG";
            default:                       return "ERROR";
        }
    }

    /**
     * Returns the message based upon the error code and arguments provided to this class as part of the constructor.
     *
     * @return the message.
     */
    @Override
	public String getMessage()
    {
        String result = MessageFormat.format(mCode.getMessage().trim(), getArgs()).trim();

        return result;
    }

    /**
     * Saves the stack trace (as a String) of this exception class.  This is done so that the stack trace is serialized for any
     * and all server-side exceptions.
     *
     * <p>Note, the stack trace of the root cause is saved in the event that this exception wraps a root cause.
     */
    private String buildStackTrace()
    {
        StringBuffer results = new StringBuffer(buildStackTrace(this));

        for (Throwable rootCause = mRootCause; rootCause != null;)
        {
            results.append("Caused by: ");
            if (rootCause instanceof NfbCheckedException)
            {
                results.append(buildStackTrace(rootCause));
                rootCause = ((NfbCheckedException)rootCause).getRootCause();
            }
            else if (rootCause instanceof NfbRuntimeException)
            {
                results.append(buildStackTrace(rootCause));
                rootCause = ((NfbRuntimeException)rootCause).getRootCause();
            }
            else
            {
                results.append(buildStackTrace(rootCause));
                rootCause = null;
            }
        }

        return results.toString();
    }

    /**
     * Returns the stack trace (as a String) of the specified Throwable. Removes the results of the exception toString() method
     * from the stack trace string prior to returning.
     *
     * @param t the Throwable.
     * @return the stack trace (as a String) of the specified Throwable.
     */
    private String buildStackTrace(Throwable t)
    {
        StringWriter writer = new StringWriter();
        t.printStackTrace(new PrintWriter(writer));
        String s = writer.toString();

        if (t == this)
        {
            // Parse the exception toString results from the stack trace string.
            int i = s.indexOf("\n\tat ");
            if (i != -1)
            {
                String stackTraceString = s.substring(i);
                return stackTraceString;
            }

            return StringUtils.EMPTY;
        }

        return s;
    }

    /**
     * Gets the stack trace (as a String) of this object if the exception severity is ERROR or FATAL.  Returns the empty string
     * if the exception severity is not ERROR or FATAL.
     *
     * <p>
     * Note, the the stack trace of this object will be the stack trace of the root cause if the exception severity is ERROR or
     * FATAL and this object wraps a root cause.
     *
     * <p>
     * Note, the results of the exception toString method are removed from the stack trace string prior to it being returned to
     * the caller.
     *
     * @return the stack trace (as a String) of this object.
     */
    public String getStackTraceString()
    {
        if (mStackTraceString == null)
        {
            mStackTraceString = buildStackTrace();
        }

        return mStackTraceString;
    }

    /**
     * Returns a String representation of this object. e.g.
     * <pre>
     *     [ERROR - 2002 - ContractTypeDao.find
     *     No records found in table: CONTRACTS]
     * </pre>
     *
     * @return a String representation of this object.
     */
    @Override
	public String toString()
    {
        Object[] args = new Object[] { getSeverityAsString(), mCode, mDebugInformation, getMessage() };
        return MessageFormat.format(TO_STRING_MESSAGE_FORMAT, args);
    }

    /**
     * Gets the MediaMateRuntimeException.Code object with the corresponding
     * code name. This method returns any codes defined within its subclasses,
     * who register their codes with this class at initialization.
     *
     * @param codeName the name of the code to retrieve
     *
     * @return the code matching the name, may be null
     */
    public static Code getCode(String codeName)
    {
        synchronized (CODE_MAP)
        {
            return CODE_MAP.get(codeName);
        }
    }

    /**
     * Registers a code object in the code map. If a code with an identical
     * code name already exists, a runtime exception is thrown.
     *
     * @param code the code to register
     */
    protected static void registerCode(Code code)
    {
        synchronized (CODE_MAP)
        {
            String codeName = code.getCode();
            if (CODE_MAP.containsKey(codeName))
            {
                // throw exception?
            }
            else
            {
                CODE_MAP.put(codeName, code);
            }
        }
    }

    /**
     * Called when the exception is deserialized. This implementation performs
     * deserializaion using the standard deserialization tools, then calls log().
     *
     * @param in the stream containing the exception details
     *
     * @throws IOException thrown if an error occurs while reading the stream
     * @throws ClassNotFoundException thrown if a class in the stream isn't found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
    }
}

