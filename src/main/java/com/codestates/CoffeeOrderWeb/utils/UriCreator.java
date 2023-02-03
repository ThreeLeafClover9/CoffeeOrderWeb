package com.codestates.CoffeeOrderWeb.utils;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriCreator {
    public static URI createUri(String resourceDefaultPath, long resourceId) {
        return UriComponentsBuilder
                .fromOriginHeader(resourceDefaultPath)
                .path("/{resource-id}")
                .buildAndExpand(resourceId)
                .toUri();
    }
}
