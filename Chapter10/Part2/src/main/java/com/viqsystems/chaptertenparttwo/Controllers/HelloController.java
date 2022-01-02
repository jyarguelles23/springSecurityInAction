package com.viqsystems.chaptertenparttwo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /* The /hello path remains under CSRF protection. You can’t call the endpoint without a valid CSRF token.*/
    @PostMapping("/hello")
    public String postHello() {
        return "Post Hello!";
    }

    /* The /ciao path can be called without a CSRF token.*/
    @PostMapping("/ciao")
    public String postCiao() {
        return "Post Ciao";
    }

    @GetMapping("/hello")
    public String getHello() {
        return "Get Hello!";
    }

}
