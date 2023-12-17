package com.suzzingv.genshingg.security.User.controller;

import com.suzzingv.genshingg.security.User.dto.AddUserRequest;
import com.suzzingv.genshingg.security.User.service.UserService;
import com.suzzingv.genshingg.security.exception.RedirectionException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public void signUp(@RequestBody AddUserRequest req, HttpServletResponse response) {
        userService.save(req);
        try {
            response.sendRedirect("http://localhost:8080/api/v1/login");
        } catch (IOException e) {
            throw new RedirectionException("redirection 오류");
        }
    }

    @GetMapping("/logout")
    public void logOut(HttpServletRequest req, HttpServletResponse res) {
        new SecurityContextLogoutHandler().logout(req, res, SecurityContextHolder.getContext().getAuthentication());
        try {
            res.sendRedirect("http://localhost:8080/api/v1/login");
        } catch (IOException e) {
            throw new RedirectionException("redirection 오류");
        }
    }
}
