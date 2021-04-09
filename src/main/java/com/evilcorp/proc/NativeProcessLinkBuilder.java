package com.evilcorp.proc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NativeProcessLinkBuilder implements LinkBuilder {
    final List<ProcessBuilder> processes;

    public NativeProcessLinkBuilder(ProcessBuilder... processes) {
        this.processes = new ArrayList<>(Arrays.asList(processes));
    }

    @Override
    public Link build() {
        try {
//            return new NativeProcessLink(ProcessBuilder.startPipeline(processes));
            return new NativeProcessLink(Collections.singletonList(processes.get(0).start()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
