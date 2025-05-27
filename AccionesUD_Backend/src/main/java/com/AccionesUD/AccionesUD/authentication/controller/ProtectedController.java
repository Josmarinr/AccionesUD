package com.AccionesUD.AccionesUD.authentication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class ProtectedController {
    @PostMapping(value = "protected")
    public String welcome() {
        return "Protected endpoint accessed";
    }
}
