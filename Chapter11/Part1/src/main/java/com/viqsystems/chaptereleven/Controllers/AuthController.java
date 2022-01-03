package com.viqsystems.chaptereleven.Controllers;

import com.viqsystems.chaptereleven.Entities.Otp;
import com.viqsystems.chaptereleven.Entities.Users;
import com.viqsystems.chaptereleven.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    private final UserService userService;

    AuthController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/user/add")
    public void addUser(@RequestBody Users user) {
        userService.addUser(user);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody Users user) {
        userService.auth(user);
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody Otp otp, HttpServletResponse response) {
        if (userService.check(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}
