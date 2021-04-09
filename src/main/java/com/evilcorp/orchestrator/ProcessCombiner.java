package com.evilcorp.orchestrator;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Demonstration, how to translate shell script to java
 * Shell script version:
 * producer.exe | combiner.exe
 */
public class ProcessCombiner {
    public static void main(String[] args) throws IOException, InterruptedException {
        final ProcessBuilder producerBuilder = new ProcessBuilder("producer.exe");
        producerBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
//        final ProcessBuilder consumerBuilder = new ProcessBuilder("error-consumer.exe");
        final ProcessBuilder consumerBuilder = new ProcessBuilder("consumer.exe");
        consumerBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        consumerBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        final Instant start = Instant.now();
        final List<Process> processes = ProcessBuilder.startPipeline(List.of(producerBuilder, consumerBuilder));
        /*
        Process currentProcess = null;
        for (Process proc : processes) {
            final CompletableFuture<Process> processCompletableFuture = proc.onExit();
            try {
                currentProcess = proc;
                final Process process = processCompletableFuture.get(10, TimeUnit.SECONDS);
                final Instant processFinished = Instant.now();
                System.out.println("process finished in " + Duration.between(start, processFinished).get(ChronoUnit.NANOS) / 1_000_000
                        +  " process.exitValue() = " + process.exitValue());
            } catch (ExecutionException e) {
//                System.out.println("Error");
                currentProcess.info().command().ifPresent(cmd -> System.out.println(cmd + " finished with exception"));
                processes.forEach(Process::destroyForcibly);
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                currentProcess.info().command().ifPresent(cmd -> System.out.println(cmd + " couldn't finish within timeout"));
                processes.forEach(Process::destroyForcibly);
                throw new RuntimeException(e);
            }
        }

         */
    }
}
