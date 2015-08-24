package com.nfbsoftware.util;

/**
 *  0 = (reserved error)
 *  1 = Fatal
 *  2 = Error
 *  3 = Warning
 *  4 = Informational
 *  5 = Success
 *  6 = Debug
 *  7 = (reserved success)
 *
 * @author Brendan Clemenzi 
 * @email brendan@clemenzi.com
 */
public final class Severity
{
    // Severity levels
    public final static int FATAL = 1;
    public final static int ERROR = 2;
    public final static int WARNING = 3;
    public final static int INFORMATIONAL = 4;
    public final static int SUCCESS = 5;
    public final static int DEBUG = 6;

    /**
     * Returns a string representing the severity of the code.
     */
    public static String asString(int severity)
    {
        switch (severity)
        {
        case FATAL:           return "F";
        case ERROR:           return "E";
        case WARNING:         return "W";
        case INFORMATIONAL:   return "I";
        case SUCCESS:         return "S";
        case DEBUG:           return "D";
        }

        return "E";
    }

    /**
     * Returns a string representing the severity of the code.
     */
    public static int asInt(String severityStr)
    {
        switch (Character.toUpperCase(severityStr.charAt(0)))
        {
        case 'F': return FATAL;
        case 'E': return ERROR;
        case 'W': return WARNING;
        case 'I': return INFORMATIONAL;
        case 'S': return SUCCESS;
        case 'D': return DEBUG;
        }

        return ERROR;
    }

    /**
     * Returns true if the severity of the code is one of the types of
     * severities representing an "error" (which are Warning, Error, Fatal)
     */
    public static boolean isFailure(int severity)
    {
        return severity <= WARNING;
    }

    /*
     * Returns true if the severity of the code is one of the types of
     * severities representing a "success (which are Success, Informational,
     * Debug)
     */
    public static boolean isSuccess(int severity)
    {
        return !isFailure(severity);
    }

    /**
     * Returns true if the severity is at or above the threshold.
     * To be "above" implies the same or greater degree of error severity.<br>
     * Example:
     *  int sev = Severity.INFORMATIONAL;<br>
     *  Severity.above(sev, Severity.WARNING) is false<br>
     *  Severity.above(sev, Severity.DEBNUG) is true<br>
     * NOTE: Because of the way the severity levels are ordered, higher
     * severities are actually lower integers.  However, this should be
     * irrelevant to the application since it should not try to do its own
     * comparison of these values.
     */
    public static boolean atOrAbove(int severity, int threshold)
    {
        return severity <= threshold;
    }
}

