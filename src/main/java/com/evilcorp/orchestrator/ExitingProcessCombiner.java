package com.evilcorp.orchestrator;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Demonstration, how to translate shell script to java
 * Shell script version:
 * producer.exe | combiner.exe
 *
 * Java waits until child processes are terminated,
 * because their output is redirected to java output.
 */
public class ExitingProcessCombiner {
    public static void main(String[] args) throws IOException, InterruptedException {
        final ProcessBuilder producerBuilder = new ProcessBuilder("producer.exe", "");
        producerBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        final ProcessBuilder consumerBuilder = new ProcessBuilder("consumer.exe");
        consumerBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        consumerBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        final List<Process> processes = ProcessBuilder.startPipeline(List.of(producerBuilder, consumerBuilder));
    }
}
