package com.mtailor.meetrics.service;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Base64SvgSplitter {

    private static final int PURE_BASE64_PART = 1;
    private static final String SVG_DELIMITER = ",";

    public String getPureBase64(final String base64Svg) {
        return Optional.ofNullable(base64Svg)
                .map(input -> input.split(SVG_DELIMITER))
                .filter(splitArray -> splitArray.length > 1)
                .map(array -> array[PURE_BASE64_PART])
                .orElseThrow(IllegalArgumentException::new);
    }
}
