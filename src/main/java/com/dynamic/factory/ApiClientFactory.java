package com.dynamic.factory;

import java.util.HashMap;
import java.util.Map;

public class ApiClientFactory {
    private static final Map<String, ApiClient> clients = new HashMap<>();

    static {
        registerProvider("REST", new RestApiClient());
        registerProvider("SOAP", new SoapDynamicApiClient());
    }

    public static void registerProvider(String type, ApiClient client) {
        clients.put(type, client);
    }

    public static ApiClient createApiClient(String type) {
        ApiClient client = clients.get(type);
        if (client == null) {
            throw new IllegalArgumentException("Unknown client type: " + type);
        }
        return client;
    }
}