package io.stack.pj.hibernate.cfg;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.io.Serializable;
import java.util.Locale;

/**
 * Creates database table in lowercase with snake case naming pattern. The '_id' is appended on foreign keys on owner side.
 * <p>
 *     database table name: publication_houses
 *     table column name: names, author_id
 * </p>
 * <code>
 *     at ManyToOne
 *     private Author author
 * </code>
 * to 'author_id' in table.
 *
 * @author Prajin Maharjan
 * @since 1.0
 */
public class DefaultNamingStrategy extends PhysicalNamingStrategyStandardImpl implements Serializable {

    public static final DefaultNamingStrategy INSTANCE = new DefaultNamingStrategy();

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
//        System.out.println(name.getText() + "-" + addUnderscores(name.getText()));
        return new Identifier(addUnderscores(name.getText()), name.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
//        System.out.println(name.getText() + "--" + addUnderscores(name.getText()));
        return new Identifier(addUnderscores(name.getText()), name.isQuoted());
    }

    protected static String addUnderscores(String name) {
        final StringBuilder buf = new StringBuilder(name.replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; i++) {
            if (Character.isLowerCase(buf.charAt(i - 1)) &&
                    Character.isUpperCase(buf.charAt(i)) &&
                    Character.isLowerCase(buf.charAt(i + 1))
                    ) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase(Locale.ROOT);
    }
}