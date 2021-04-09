package com.evilcorp.orchestrator;

import com.evilcorp.proc.LocalThreadedChainBuilder;
import com.evilcorp.proc.NativeProcessLinkBuilder;
import com.evilcorp.proc.ProcessChain;
import com.evilcorp.proc.ProcessChainBuilder;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

/**
 * Demonstration, how to translate shell script to java
 * Shell script version:
 * producer.exe | combiner.exe
 */
public class ProcessChainCombiner {
    public static void main(String[] args) throws IOException, InterruptedException {
        final ProcessBuilder producerBuilder = new ProcessBuilder("producer.exe"
//                , "slow=1"
//                , "times=1000"
        );
//        producerBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
//        producerBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//        producerBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
//        final ProcessBuilder consumerBuilder = new ProcessBuilder("error-consumer.exe");
        final ProcessBuilder consumerBuilder = new ProcessBuilder("consumer.exe", "logToFile");
        consumerBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//        final Process producer = producerBuilder.start();
//        final Process consumer = consumerBuilder.start();
//        for (int i = 0; i < 5; i++) {
//            System.out.println("producer.isAlive() = " + producer.isAlive());
//            System.out.println("producer.getInputStream().available() = " + producer.getInputStream().available());
//            System.out.println("producer.getInputStream().read() = " + producer.getInputStream().read());
//            producer.getInputStream().transferTo(consumer.getOutputStream());
//            int curr;
//            while ((curr = producer.getInputStream().read()) != -1) {
//                consumer.getOutputStream().write(curr);
//            }
//            Thread.sleep(500);
//        }
//        consumer.getOutputStream().close();
//        System.out.println("consumer.getInputStream().read() = " + consumer.getInputStream().read());
//        consumerBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
//        final Instant start = Instant.now();
//        final List<Process> processes = ProcessBuilder.startPipeline(List.of(producerBuilder, consumerBuilder));
        ProcessChainBuilder builder = new LocalThreadedChainBuilder();
        builder.extend(new NativeProcessLinkBuilder(producerBuilder))
                .extend(new NativeProcessLinkBuilder(consumerBuilder));
        final ProcessChain chain = builder.start();
        chain.up();

        Thread.sleep(5000);
    }
}
