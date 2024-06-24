package com.dynamic.factory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;

public class RestApiClient implements ApiClient {
    // private static final String BASE_URL = "http://localhost:8080/api";

    @Override
    public String executeMethod(String baseUrl, String method, String body) {
        try {
            // JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            // String method = jsonObject.get("method").getAsString();
            System.err.println(baseUrl);
            System.err.println(method);
            System.err.println(body);

            BodyPublisher bodyPublisher = BodyPublishers.noBody();

            if (body != null)
                bodyPublisher = BodyPublishers.ofString(body);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/" + method))
                .header("content-type", "aplication/json")
                .POST(bodyPublisher)
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
