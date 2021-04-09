package com.evilcorp.orchestrator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration, how to translate shell script to java
 * Shell script version:
 * producer.exe
 * <p>
 * Java waits until child processes are terminated,
 * because their output is redirected to java output.
 * <p>
 * Comment redirectError and redirectOutput to see,
 * that Java does not wait if output is not redirected.
 * <p>
 * But if you read process output manually and producer writes
 * to std err, then sooner or later output reader will block
 * and you Java process will hang.
 * If it doesn't hang in you case, then use times argument,
 * to specify how many lines producer should produce.
 * It times is big enough, output reader will block.
 * <p>
 * You have to read std error simultaneously, if you want to read the whole
 * std out. Because reader blocks you have to read std err in a separate
 * thread.
 * <p>
 * Uncomment errReader.start() to see how reading std err in a separate
 * thread leads to reading std out not blocking.
 */
public class SingleProcessRunner {
    public static void main(String[] args) throws IOException, InterruptedException {
        final boolean readProcessOutputManually = false;
        final ProcessBuilder producer = new ProcessBuilder("producer.exe"
                , "slow=1"
                , "times=1000"
                , "error_stream"
        );
        if (readProcessOutputManually) {
            final Process process = producer.start();
            final Thread errReader = new Thread(() -> new BufferedReader(new InputStreamReader(new BufferedInputStream(process.getErrorStream())))
                    .lines().forEach(System.out::println));
//            errReader.start();
            new BufferedReader(new InputStreamReader(new BufferedInputStream(process.getInputStream())))
                    .lines().forEach(System.out::println);
        } else {
            /*
            producer
                    .redirectError(ProcessBuilder.Redirect.INHERIT)
                    .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                    .start();
             */
//            /*
            byte[] buffer = new byte[2048];
            final Process start = producer
                    .start();
            final int maxWaitSeconds = 1;
            long waitMillis = 0;
            final InputStream inputStream = start.getInputStream();
            final InputStream errorStream = start.getErrorStream();

            int availableBytes;
//            Thread.sleep(2000);

            List<Byte> inputBytes = new ArrayList<>();
            List<Byte> errorBytes = new ArrayList<>();

            while (inputStream.available() > 0 || (waitMillis / 1000) < maxWaitSeconds) {
                final long startTime = System.currentTimeMillis();

                while ((availableBytes = inputStream.available()) > 0) {
                    final int bytesRead = inputStream.readNBytes(buffer, 0, availableBytes);
                    if (bytesRead == -1) {
                        throw new RuntimeException("stream has ended");
                    }
                    if (bytesRead > 0) {
                        for (int i = 0; i < bytesRead; i++) {
                            inputBytes.add(buffer[i]);
                            System.out.println(buffer[i]);
                        }
                        waitMillis = 0;
                    }
                }
                while ((availableBytes = errorStream.available()) > 0) {
                    final int bytesRead = errorStream.readNBytes(buffer, 0, availableBytes);
                    if (bytesRead == -1) {
                        throw new RuntimeException("stream has ended");
                    }
                    if (bytesRead > 0) {
                        for (int i = 0; i < bytesRead; i++) {
                            errorBytes.add(buffer[i]);
                            System.out.println(buffer[i]);
                        }
                        waitMillis = 0;
                    }
                }
                final long endTime = System.currentTimeMillis();
                waitMillis = waitMillis + (endTime - startTime);
                System.out.println("start.isAlive() = " + start.isAlive());
            }

            byte[] inputRaw = new byte[inputBytes.size()];
            for (int i = 0; i < inputBytes.size(); i++) {
                inputRaw[i] = inputBytes.get(i);
            }


//            final String s = new String(inputRaw, StandardCharsets.UTF_8);
//            System.out.println(s);

             /**/



//            final Process start = producer
//                    .start();
//            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(start.getInputStream())));
//            bufferedReader.
        }
    }
}
