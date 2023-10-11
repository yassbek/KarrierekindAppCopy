package com.karrierekind.karrierekindapp.controller;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {

    @GetMapping("/api/csrf-token")
    public String getCsrfToken(CsrfToken csrfToken) {
        return csrfToken.getToken();
    }
}