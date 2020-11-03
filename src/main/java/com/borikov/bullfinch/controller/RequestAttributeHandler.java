package com.borikov.bullfinch.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Request attribute handler.
 */
public class RequestAttributeHandler {
    private Map<String, Object> attributes = new HashMap<>();

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    /**
     * Sets attributes.
     *
     * @param request the request
     */
    public void setAttributes(HttpServletRequest request) {
        attributes = new HashMap<>();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            attributes.put(attributeName, request.getAttribute(attributeName));
        }
    }
}
