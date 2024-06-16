package com.ironman.restaurantmanagement.shared.util;

import java.text.Normalizer;

public class StringHelper {

    public static String normalizeString(String str) {
        if(str == null) {
            return null;
        }

       return Normalizer.normalize(str, Normalizer.Form.NFD);
    }

    public static String removeAccents(String str) {
        if(str == null) {
            return null;
        }

        return normalizeString(str).replaceAll("\\p{M}", "");
    }

    public static String removePunctuation(String str) {
        if(str == null) {
            return null;
        }

        return normalizeString(str).replaceAll("\\p{Punct}", "");
    }

    public static String replaceWhitespace(String str, String replacement) {
        if(str == null) {
            return null;
        }

        return str.replaceAll("\\s+", replacement);
    }

    public static String buildSlugsKeywords(String str) {
        if(str == null) {
            return null;
        }

        String noAccents = removeAccents(str);
        String noPunctuation = removePunctuation(noAccents);

        return replaceWhitespace(noPunctuation.trim(), "-").toLowerCase();
    }

}
