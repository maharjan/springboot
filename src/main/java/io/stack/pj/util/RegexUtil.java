package io.stack.pj.util;

/**
 * A util class to handle all regex pattern matching/validation
 *
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class RegexUtil {

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";

    private RegexUtil() {
    }

    public static boolean isEmail(String input) {
        return input.matches(EMAIL_PATTERN);
    }

    public static boolean isDigit(String input) {
        return input.matches("^[\\d]+$");
    }

    public static boolean isDouble(String input) {
        return input.matches("(\\d+\\.\\d+)?");
    }
}
