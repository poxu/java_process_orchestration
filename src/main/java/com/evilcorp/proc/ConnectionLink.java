package com.evilcorp.proc;

public interface ConnectionLink {
    void tick();
    boolean online();
    void close();
}
