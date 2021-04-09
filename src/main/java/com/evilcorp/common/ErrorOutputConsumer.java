package com.evilcorp.common;

public class ErrorOutputConsumer implements LineConsumer {

    @Override
    public void consume(String line, int idx) {
        System.err.println("error");
    }
}
