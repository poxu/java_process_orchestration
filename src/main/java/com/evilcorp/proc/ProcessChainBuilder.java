package com.evilcorp.proc;

public interface ProcessChainBuilder {
    ProcessChainBuilder extend(LinkBuilder link);

    ProcessChain start();
}
