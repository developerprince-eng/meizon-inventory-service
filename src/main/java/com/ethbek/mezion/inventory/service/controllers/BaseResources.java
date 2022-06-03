package com.ethbek.mezion.inventory.service.controllers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class BaseResources {

    private static final String AUTH_TYPE = "Bearer ";

    public HttpHeaders customSetHeaders(HttpServletRequest requestHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, requestHeader.getHeader(HttpHeaders.AUTHORIZATION));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    public HttpHeaders customSetHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, AUTH_TYPE.concat(token));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    public String extractToken(HttpServletRequest requestHeader) {
        return requestHeader.getHeader(HttpHeaders.AUTHORIZATION).substring(AUTH_TYPE.length());
    }

    public String extractToken(String token) {
        return token.substring(AUTH_TYPE.length());
    }

}