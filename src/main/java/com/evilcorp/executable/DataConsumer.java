package com.evilcorp.executable;

import com.evilcorp.args.CmdArguments;
import com.evilcorp.common.*;

import java.io.*;
import java.util.*;

public class DataConsumer {

    public static final String LOG_TO_FILE = "logToFile";
    public static final String PRODUCE_ERROR = "produceError";
    public static final String SLOW = "slow";

    public static void main(String[] args) throws IOException {
        final CmdArguments arguments = new CmdArguments(args);
        List<LineConsumer> consumers = new ArrayList<>();
        consumers.add(new StandardOutputConsumer());
        if (arguments.hasFlag(LOG_TO_FILE)) {
            consumers.add(new FileConsumer("consumer-output.txt"));
        }
        if (arguments.hasFlag(PRODUCE_ERROR)) {
            consumers.add(new ExceptionThrowingConsumer());
        }
        if (arguments.hasFlag(SLOW)) {
            consumers.add(new SlowConsumer(Long.parseLong(arguments.getFlag(SLOW))));
        }
        final InputStreamConsumer inputStreamConsumer = new StandardInputStreamConsumer(consumers);
        inputStreamConsumer.consume(System.in);
    }
}
