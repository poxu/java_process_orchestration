package com.evilcorp.proc;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class NativeProcessLink implements Link {
    final List<Process> processes;

    public NativeProcessLink(List<Process> processes) {
        this.processes = processes;
    }

    @Override
    public boolean online() {
        for (Process process : processes) {
            if (process.isAlive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public OutputStream output() {
        return processes.get(0).getOutputStream();
    }

    @Override
    public InputStream input() {
        return processes.get(processes.size() - 1).getInputStream();
    }

    @Override
    public InputStream error() {
        return processes.get(processes.size() - 1).getErrorStream();
    }
}
