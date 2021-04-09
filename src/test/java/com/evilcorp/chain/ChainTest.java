package com.evilcorp.chain;

import com.evilcorp.proc.*;
import org.junit.jupiter.api.Test;

public class ChainTest {
    @Test
    void constructor() {
        ProcessBuilder nativeProcess = null;
        ProcessBuilder otherNativeProcess = null;
        ProcessChainBuilder chainBuilder = new LocalThreadedChainBuilder();
        chainBuilder
                .extend(new NativeProcessLinkBuilder(nativeProcess))
        ;
        final ProcessChain start = chainBuilder.start();

    }

}
