package com.evilcorp.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class StandardInputStreamConsumer implements InputStreamConsumer {
    private Iterable<? extends LineConsumer> consumers;

    public StandardInputStreamConsumer(Iterable<? extends LineConsumer> consumers) {
        this.consumers = consumers;
    }

    @Override
    public void consume(InputStream in) {
        int i = 0;
        final Iterator<String> iterator = new BufferedReader(new InputStreamReader(new BufferedInputStream(in)))
                .lines()
                .iterator();
        for (String line = iterator.next(); iterator.hasNext(); line = iterator.next()) {
            for (LineConsumer consumer : consumers) {
                consumer.consume(line, i);
            }
            i += 1;
        }
    }
}
