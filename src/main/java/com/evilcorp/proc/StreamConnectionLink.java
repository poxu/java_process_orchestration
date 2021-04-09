package com.evilcorp.proc;

import java.io.IOException;

public class StreamConnectionLink implements ConnectionLink {
    private final Link left;
    private final Link right;

    public StreamConnectionLink(Link left, Link right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void tick() {
        try {
            left.input().transferTo(right.output());
//            right.output().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean online() {
        try {
            return (left.online() || left.input().available() != 0)  && right.online();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
//        /*
        try {
            right.output().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         /**/
    }
}
