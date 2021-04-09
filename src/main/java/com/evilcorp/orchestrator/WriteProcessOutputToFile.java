package com.evilcorp.orchestrator;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Demonstration, how to translate shell script to java
 * Shell script version:
 * producer.exe > write-to-file.txt
 */
public class WriteProcessOutputToFile {
    public static void main(String[] args) throws IOException, InterruptedException {
        final ProcessBuilder producerBuilder = new ProcessBuilder("producer.exe");
        producerBuilder.redirectOutput(new File("write-to-file.txt"));
        producerBuilder.start();
    }
}
