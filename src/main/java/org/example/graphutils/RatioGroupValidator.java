package org.example.graphutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RatioGroupValidator {

    private static final String REGEX = "(?<a>[0-9]+([,.][0-9]?)?)\\s+(?<V>[A-Za-z]+)\\s*=\\s*(?<b>[0-9]+([,.][0-9]?)?|\\?)\\s+(?<W>[A-Za-z]+)$";
    public static final String LEFT_VAL_NAME = "V";
    public static final String RIGHT_VAL_NAME = "W";
    public static final String LEFT_VAL = "a";
    public static final String RIGHT_VAL = "b";

    public static final String UNKNOWN_VALUE_SIGN = "?";


    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static boolean validInputString(String inputLine) {
        return PATTERN.matcher(inputLine).matches();
    }
    static Matcher getMatchedGroupsMatcher(String inputLine) {
        Matcher matcher = PATTERN.matcher(inputLine);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("Cannot parse line %s", inputLine));
        } else {
            return matcher;
        }
    }
}
