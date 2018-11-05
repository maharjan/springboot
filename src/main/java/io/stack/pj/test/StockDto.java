package io.stack.pj.test;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
public class StockDto {

    private final int id;
    private final String name;

    public StockDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
