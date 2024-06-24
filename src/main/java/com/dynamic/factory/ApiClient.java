package com.dynamic.factory;

public interface ApiClient {
    public String executeMethod(String baseUrl, String method, String body);
}
