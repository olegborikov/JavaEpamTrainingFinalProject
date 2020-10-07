package com.borikov.bullfinch.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestAttributeHandler {
    private static RequestAttributeHandler instance;
    private Map<String, Object> attributes = new HashMap<>();

    private RequestAttributeHandler() {
    }

    public static RequestAttributeHandler getInstance() {
        if (instance == null) {
            instance = new RequestAttributeHandler();
        }
        return instance;
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public void setAttributes(HttpServletRequest request) {
        attributes = new HashMap<>();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            attributes.put(attributeName, request.getAttribute(attributeName));
        }
    }
}
