package com.dynamic.factory;

import java.util.List;
import java.util.Map;

public interface ApiClient {
    String getHola();
    List<Map<String, Object>> getPersonas();

    String executeMethod(String method);
}
