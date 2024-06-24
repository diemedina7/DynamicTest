package com.dynamic.factory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.google.gson.Gson;

public class SoapDynamicApiClient implements ApiClient {
    // private static final String WSDL_URL = "http://localhost:8195/soap/hola?wsdl";

    @Override
    public String executeMethod(String baseUrl, String method, String body) {
        try {
            JaxWsDynamicClientFactory jaxws = JaxWsDynamicClientFactory.newInstance();
            Client client = jaxws.createClient(baseUrl + "?wsdl", new URLClassLoader( new URL[] {
                new File("..\\libs\\jaxb-xjc-4.0.5.jar").toURI().toURL()
            }));

            Object obj[] = client.invoke(method, body);
            client.close();

            Gson gson = new Gson();
            String strResponse = gson.toJson(obj[0]);

            return strResponse;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
