package com.evilcorp.executable;

import com.evilcorp.args.CmdArguments;
import com.evilcorp.common.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProducer {
    public static final String SLOW = "slow";
    public static final String PRODUCE_ERROR = "produceError";
    public static final String LOG_TO_FILE = "logToFile";
    public static final String ERROR_STREAM = "error_stream";

    public static void main(String[] args) throws IOException, InterruptedException {
        final CmdArguments arguments = new CmdArguments(args);
        List<LineConsumer> consumers = new ArrayList<>();
        consumers.add(new StandardOutputConsumer());
        if (arguments.hasFlag(LOG_TO_FILE)) {
            System.out.println(LOG_TO_FILE);
            consumers.add(new FileConsumer("producer-output.txt"));
        }
        if (arguments.hasFlag(SLOW)) {
            System.out.println(SLOW);
            consumers.add(new SlowConsumer(Long.parseLong(arguments.getFlag(SLOW))));
        }
        if (arguments.hasFlag(PRODUCE_ERROR)) {
            System.out.println(PRODUCE_ERROR);
            consumers.add(new ExceptionThrowingConsumer());
        }
        if (arguments.hasFlag(ERROR_STREAM)) {
            System.out.println(ERROR_STREAM);
            consumers.add(new ErrorOutputConsumer());
        }
        final int times;
        if (arguments.hasFlag("times")) {
            System.out.println("times");
            times = Integer.parseInt(arguments.getFlag("times"));
        } else {
            times = 100;
        }
        for (int i = 0; i < times; i++) {
            for (LineConsumer consumer : consumers) {
                consumer.consume(String.valueOf(i), i);
            }
        }
    }
}
