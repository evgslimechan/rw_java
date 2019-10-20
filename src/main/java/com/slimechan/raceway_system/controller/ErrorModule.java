package com.slimechan.raceway_system.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

@Controller
public class ErrorModule implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }}