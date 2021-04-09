package com.evilcorp.proc;

import java.util.ArrayList;
import java.util.List;

public class LocalThreadedChainBuilder implements ProcessChainBuilder {
    private final List<LinkBuilder> chain = new ArrayList<>();

    @Override
    public ProcessChainBuilder extend(LinkBuilder link) {
        chain.add(link);
        return this;
    }

    @Override
    public ProcessChain start() {
        Link prev = null;
        Link curr;
        List<Link> links = new ArrayList<>();
        List<ConnectionLink> connectionLinks = new ArrayList<>();

        for (LinkBuilder linkBuilder : chain) {
            curr = linkBuilder.build();
            if (prev != null) {
                connectionLinks.add(new StreamConnectionLink(prev, curr));
            }
            prev = curr;
        }

        return new SimpleProcessChain(links, connectionLinks);
    }
}
