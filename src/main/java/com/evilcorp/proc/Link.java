package com.evilcorp.proc;

import java.io.InputStream;
import java.io.OutputStream;

public interface Link {
    boolean online();
    OutputStream output();
    InputStream input();
    InputStream error();
}
