package com.evilcorp.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileConsumer implements LineConsumer {

    private final PrintWriter fileWriter;

    public FileConsumer(String fileName) {
        try {
            fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void consume(String line, int idx) {
        fileWriter.println(line);
        fileWriter.println("I ma consumer");
        fileWriter.flush();
    }
}
