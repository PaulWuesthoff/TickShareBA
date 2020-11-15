package com.example.tickshareba.authentication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class TokenGenerator {
    /**
     * Class for generating Usertokens
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String LOWER = UPPER.toLowerCase(Locale.ROOT);

    public static final String DIGITS = "0123456789";

    public static final String ALPHANUM = UPPER + LOWER + DIGITS;

    private Random random = null;

    private char[] symbols = null;

    private char[] buf = null;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public TokenGenerator(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public TokenGenerator(int length, Random random) {
        this(length, random, ALPHANUM);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public TokenGenerator(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public TokenGenerator() {
        this(21);
    }

}