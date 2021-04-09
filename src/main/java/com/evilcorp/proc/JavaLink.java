package com.evilcorp.proc;

import java.io.InputStream;
import java.io.OutputStream;

public class JavaLink implements Link {
    @Override
    public boolean online() {
        return false;
    }

    @Override
    public OutputStream output() {
        return null;
    }

    @Override
    public InputStream input() {
        return null;
    }

    @Override
    public InputStream error() {
        return null;
    }
}
