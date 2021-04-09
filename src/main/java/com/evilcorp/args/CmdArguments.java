package com.evilcorp.args;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CmdArguments {

    public static final String DELIM = "=";
    private final Map<String, String> arguments = new HashMap<>();

    public CmdArguments(String[] args) {
        final List<String> args1 = List.of(args);
        for (String s : args1) {
            final String[] keyValue = s.split(DELIM);
            if (keyValue.length == 1) {
                arguments.put(keyValue[0], "true");
            } else if (keyValue.length == 2) {
                arguments.put(keyValue[0], keyValue[1]);
            }
        }
    }

    public boolean hasFlag(String flag) {
        return arguments.containsKey(flag);
    }

    public String getFlag(String flag) {
        return arguments.get(flag);
    }
}
