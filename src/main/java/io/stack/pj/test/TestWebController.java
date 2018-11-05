package io.stack.pj.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Controller
public class TestWebController {

    @GetMapping(value = "/stock")
    public String getStockList(){
        return "stocklist";
    }
}
