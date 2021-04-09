package com.evilcorp.proc;

import java.util.List;

public class SimpleProcessChain implements ProcessChain {
    private final List<Link> links;
    private final List<ConnectionLink> connectionLinks;

    public SimpleProcessChain(List<Link> links, List<ConnectionLink> connectionLinks) {
        this.links = links;
        this.connectionLinks = connectionLinks;
    }

    @Override
    public void up() {
        for (ConnectionLink connectionLink : connectionLinks) {
            new Thread(() -> {
                System.out.println("connectionLink.online()`1 = " + connectionLink.online());
                while (connectionLink.online()) {
                    System.out.println("connectionLink.online() = " + connectionLink.online());
                    connectionLink.tick();
                }
                connectionLink.close();
            }).start();
        }
    }
}
