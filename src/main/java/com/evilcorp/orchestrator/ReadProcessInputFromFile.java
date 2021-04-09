package com.evilcorp.orchestrator;

import java.io.File;
import java.io.IOException;

/**
 * Demonstration, how to translate shell script to java
 * Shell script version:
 * consumer.exe < read-from-file.txt
 */
public class ReadProcessInputFromFile {
    public static void main(String[] args) throws IOException, InterruptedException {
        final ProcessBuilder producerBuilder = new ProcessBuilder("consumer.exe");
        producerBuilder.redirectInput(new File("read-from-file.txt"));
        producerBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        producerBuilder.start();
    }
}
