package com.evilcorp.common;

public class ExceptionThrowingConsumer implements LineConsumer {
    @Override
    public void consume(String line, int idx) {
        if (idx == 500) {
            throw new RuntimeException("This is a planned exception from consumer");
        }
    }
}
