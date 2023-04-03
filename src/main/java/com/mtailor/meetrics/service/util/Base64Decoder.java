package com.mtailor.meetrics.service.util;

import java.util.Base64;

/**
 * Only for local testing purposes.
 */
public class Base64Decoder {
    public static String decode(final String result) {
        return new String(Base64.getDecoder().decode(result));
    }
}
