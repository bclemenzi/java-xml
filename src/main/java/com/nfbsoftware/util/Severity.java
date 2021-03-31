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
* 
 */
public final class Severity
{
    // Severity levels
    /**
     * Severity level is "FATAL"; this is considered a failure.
     */
    public final static int FATAL = 1;
    
    /**
     * Severity level is "ERROR"; this is considered a failure.
     */
    public final static int ERROR = 2;
    /**
     * Severity level is "WARNING"; this is considered a failure.
     */
    public final static int WARNING = 3;
    /**
     * Severity level is "INFORMATIONAL"; this is considered a success.
     */
    public final static int INFORMATIONAL = 4;
    /**
     * Severity level is "SUCCESS"; this is considered a success.
     */
    public final static int SUCCESS = 5;
    /**
     * Severity level is "DEBUG"; this is considered a success, but also will provide debug information.
     */
    public final static int DEBUG = 6;

    /**
     * Returns a string representing the severity of the code.
     *
     * @param severity The severity's integer level. 
     * @return The first letter of the corresponding Severity level; if the integer does not match any of the 
     * severity levels it will return "E" for ERROR.
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
     *
     * @param severityStr A single letter representation of the Severity levels.
     * @return The corresponding int value of the letter. It will return the ERROR
     * value if there are no matches.
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
     *
     * @param severity The integer value of the Severity level
     * @return Whether or not the severity is a failure. 
     */
    public static boolean isFailure(int severity)
    {
        return severity <= WARNING;
    }

    /**
     * Returns true if the severity of the code is one of the types of
     * severities representing a "success (which are Success, Informational,
     * Debug)
     *
     * @param severity The integer value of the Severity level
     * @return Whether or not the severity is a success.
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
     * @param severity The int Severity level. 
     * @param threshold The int threshold level.
     * @return true if the severity is at or above the threshold.
     */
    public static boolean atOrAbove(int severity, int threshold)
    {
        return severity <= threshold;
    }
}

