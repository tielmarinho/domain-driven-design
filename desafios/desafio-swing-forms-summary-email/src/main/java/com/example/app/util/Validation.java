package com.example.app.util;

import java.util.regex.Pattern;

/** Validações simples e reutilizáveis. */
public final class Validation {
    private static final Pattern EMAIL =  Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private Validation(){}

    public static boolean notBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean isEmail(String s) {
        return notBlank(s) && EMAIL.matcher(s.trim()).matches();
    }
}