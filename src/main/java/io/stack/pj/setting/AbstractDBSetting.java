package io.stack.pj.setting;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public abstract class AbstractDBSetting {

    private String databaseDriverName;

    private String url;

    private String username;

    private String password;

    /**
     * The maximum number of connection that a database pool will create. Default is 50.
     */
    private int maxPool = 50;

    private int maxWait = 60000;

    private int abandonTimeout = 120;

    private boolean logAbandon = true;

    private boolean removeAbandon = true;

    private boolean testOnBorrow = true;
}
