package io.stack.pj.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class TestController {

    @GetMapping(value = "/api/v1/stocks")
    public ResponseEntity<List<StockDto>> getStockList(){
        List<StockDto> dtos= new ArrayList<>();
        dtos.add(new StockDto(1, "Stock 1"));
        dtos.add(new StockDto(2, "Stock 2"));
        return ResponseEntity.ok(dtos);
    }
}
