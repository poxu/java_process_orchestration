package com.evilcorp.common;

public class StandardOutputConsumer implements LineConsumer {

    @Override
    public void consume(String line, int idx) {
        System.out.println(line);
    }
}
