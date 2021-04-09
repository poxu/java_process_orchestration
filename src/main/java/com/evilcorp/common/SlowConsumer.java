package com.evilcorp.common;

public class SlowConsumer implements LineConsumer {
    final long delay;

    public SlowConsumer(long delay) {
        this.delay = delay;
    }

    @Override
    public void consume(String line, int idx) {
        if (delay != 0) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted", e);
            }
        }
    }
}
