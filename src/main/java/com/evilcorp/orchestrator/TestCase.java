package com.evilcorp.orchestrator;

import java.io.InputStream;

public class TestCase {
    public static void main(String[] args) {
        final InputStream in = System.in;
        System.out.println(in.getClass().getName());
    }
}
