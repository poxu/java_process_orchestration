package com.evilcorp.orchestrator;

import java.io.IOException;
import java.util.List;

/**
 * Demonstration, how to translate shell script to java
 * Shell script version:
 * producer.exe | combiner.exe
 *
 * Java does not wait until child processes are terminated, because their output
 * is not redirected to java output.
 *
 * Processes will keep running after java process is terminated. To test that,
 * uncomment argument which makes producer run slow and argument, which logs
 * consumer to file.
 *
 * To make Java wait until consumer is terminated, uncomment the line, which
 * redirects consumer output to Java output.
 */
public class SimplePipelineCombiner {
    public static void main(String[] args) throws IOException, InterruptedException {
        final ProcessBuilder producerBuilder = new ProcessBuilder("producer.exe"
                // uncomment to produce data slowly
//                , "slow=100"
        );
        final ProcessBuilder consumerBuilder = new ProcessBuilder("consumer.exe"
                // uncomment to log consumer to file
//                , "logToFile"
        )
//        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        ;
        final List<Process> processes = ProcessBuilder.startPipeline(List.of(producerBuilder, consumerBuilder));
    }
}
