package io.stack.pj.util;

import java.util.Collection;

/**
 * A util class to handle validation of input data types like string, collections etc
 *
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class InputUtil {

    private InputUtil() {
    }

    public static boolean isEmpty(final String value) {
        return (value == null || value.isEmpty());
    }

    private static boolean isCollectionEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }
}