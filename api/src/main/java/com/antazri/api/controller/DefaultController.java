package com.antazri.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DefaultController {

    @GetMapping
    public String getDefaultRoute() {
        return "Toshokan API successfully started";
    }
}
