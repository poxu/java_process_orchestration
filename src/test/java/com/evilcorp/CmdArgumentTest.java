package com.evilcorp;

import com.evilcorp.args.CmdArguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CmdArgumentTest {
    @Test
    public void emptyArguments() {
        final CmdArguments cmdArguments = new CmdArguments(new String[]{});
        assertFalse(cmdArguments.hasFlag("any_flag"));
    }

    @Test
    public void presentArgumentsWrongArgument() {
        final CmdArguments cmdArguments = new CmdArguments(new String[]{"test_arg"});
        assertFalse(cmdArguments.hasFlag("any_flag"));
    }

    @Test
    public void presentArguments() {
        final CmdArguments cmdArguments = new CmdArguments(new String[]{"test_arg"});
        assertTrue(cmdArguments.hasFlag("test_arg"));
    }

    @Test
    public void presentArgumentsWithValues() {
        final CmdArguments cmdArguments = new CmdArguments(new String[]{"test_arg", "valuedArg=1000"});
        assertEquals("true", cmdArguments.getFlag("test_arg"));
        assertEquals("1000", cmdArguments.getFlag("valuedArg"));
        assertNull(cmdArguments.getFlag("absentArgument"));
    }
}
