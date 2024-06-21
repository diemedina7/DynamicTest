package com.dynamic.test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamic.factory.ApiClient;
import com.dynamic.factory.ApiClientFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api")
public class testDynamicController {
    
    @GetMapping(path = "/rest")
    public String getSaludoRest(@RequestParam String method) {
        ApiClient client = ApiClientFactory.createApiClient("REST");
        return client.executeMethod(method);
    }

    @GetMapping(path = "/soap")
    public String getSaludoSoap(@RequestParam String method) {
        ApiClient client = ApiClientFactory.createApiClient("SOAP");
        return client.executeMethod(method);
    }
}
