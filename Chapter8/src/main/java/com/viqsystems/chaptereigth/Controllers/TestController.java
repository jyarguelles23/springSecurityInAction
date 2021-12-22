package com.viqsystems.chaptereigth.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/a")
    public String postEndpointA() {
        return "WorksPost!";
    }
    @GetMapping("/a")
    public String getEndpointA() {
        return "WorksGetA!";
    }
    @GetMapping("/a/b")
    public String getEndpointB() {
        return "WorksGetB!";
    }
    @GetMapping("/a/b/c")
    public String getEndpointC() {
        return "WorksGetC!";
    }

}
