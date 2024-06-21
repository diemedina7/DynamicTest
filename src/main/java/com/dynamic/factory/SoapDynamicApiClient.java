package com.dynamic.factory;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SoapDynamicApiClient implements ApiClient {
    private static final String WSDL_URL = "http://localhost:8195/soap/hola?wsdl";

    @Override
    public String executeMethod(String method) {
        try {
            JaxWsDynamicClientFactory jaxws = JaxWsDynamicClientFactory.newInstance();
            Client client = jaxws.createClient(WSDL_URL, new URLClassLoader( new URL[] {
                new File("..\\libs\\jaxb-xjc-4.0.5.jar").toURI().toURL()
            }));

            Object obj[] = client.invoke(method);
            client.close();

            Gson gson = new Gson();
            String strResponse = gson.toJson(obj[0]);

            return strResponse;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getHola() {
        try {
            JaxWsDynamicClientFactory jaxws = JaxWsDynamicClientFactory.newInstance();
            Client client = jaxws.createClient(WSDL_URL, new URLClassLoader( new URL[] {
                new File("..\\libs\\jaxb-xjc-4.0.5.jar").toURI().toURL()
            }));

            Object obj[] = client.invoke("getSaludo");
            client.close();

            Gson gson = new Gson();
            String strResponse = gson.toJson(obj[0]);

            return strResponse;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> getPersonas() {
        try {
            JaxWsDynamicClientFactory pepe = JaxWsDynamicClientFactory.newInstance();
            Client client = pepe.createClient(WSDL_URL);
            Object obj[] = client.invoke("getPersonas");
            client.close();

            Gson gson = new Gson();
            String strResponse = gson.toJson(obj[0]);
            Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
            List<Map<String, Object>> personas = gson.fromJson(strResponse, listType);
            
            return personas;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
