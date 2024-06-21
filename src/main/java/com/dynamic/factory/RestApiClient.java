package com.dynamic.factory;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RestApiClient implements ApiClient {
    private static final String BASE_URL = "http://localhost:8080/api";

    @Override
    public String executeMethod(String method) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                // .uri(new URI(BASE_URL + "data?param=" + parameter))
                .uri(new URI(BASE_URL + "/" + method))
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            client.close();

            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed method: " + method + ", code error: " + response.statusCode() + ", message: " + response.body());
            }
            
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
