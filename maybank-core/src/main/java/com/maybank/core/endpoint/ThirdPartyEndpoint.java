package com.maybank.core.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author Tewaraj
 */
@RestController
public class ThirdPartyEndpoint implements Serializable {

    protected static final long serialVersionUID = 1L;

    @GetMapping("/third_party")
    public String findAll() {
        return "From 3rd Party API!!";
    }

}
