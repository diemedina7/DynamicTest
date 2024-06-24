package com.dynamic.test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamic.factory.ApiClient;
import com.dynamic.factory.ApiClientFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api")
public class testDynamicController {
    private static final String BASE_URL = "http://localhost:8080/api";
    private static final String BASE_URL_SOAP = "http://localhost:8195/soap/hola";

    @GetMapping(path = "/rest")
    public String getSaludoRest(@RequestParam String method) {
        ApiClient client = ApiClientFactory.createApiClient("REST");
        return client.executeMethod(BASE_URL, method, null);
    }

    @GetMapping(path = "/soap")
    public String getSaludoSoap(@RequestParam String method, @RequestParam String body) {
        ApiClient client = ApiClientFactory.createApiClient("SOAP");
        return client.executeMethod(BASE_URL_SOAP, method, null);
    }

    @PostMapping(path = "/rest2")
    public String getSaludoRest2(@RequestBody String method) {
        JsonObject jsonObject = JsonParser.parseString(method).getAsJsonObject();
        // String method = jsonObject.get("method").getAsString();

        ApiClient client = ApiClientFactory.createApiClient("REST");
        return client.executeMethod(BASE_URL, jsonObject.get("method").getAsString(), null);
    }
}
