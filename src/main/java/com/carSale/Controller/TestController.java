package com.carSale.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@CrossOrigin
public class TestController {

    private static final Logger logger= Logger.getLogger(String.valueOf(TestController.class));

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<?> testAPI() {
        logger.info(getClass().toString() + " << Test Controller >>");
        return ResponseEntity.ok("Test API Working!");
    }
}
