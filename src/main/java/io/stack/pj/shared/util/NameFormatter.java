package io.stack.pj.shared.util;

import org.springframework.format.Formatter;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
public class NameFormatter implements Formatter<String> {

    @Override
    public String print(String input, Locale locale) {
        return formatName(input, locale);
    }

    @Override
    public String parse(String input, Locale locale) throws ParseException {
        return formatName(input, locale);
    }

    private String formatName(String input, Locale locale) {
        return StringUtils.replace(input, " ", ",");
    }
}