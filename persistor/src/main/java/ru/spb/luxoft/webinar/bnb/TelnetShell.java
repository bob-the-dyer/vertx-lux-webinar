package ru.spb.luxoft.webinar.bnb;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.shell.ShellService;
import io.vertx.ext.shell.ShellServiceOptions;
import io.vertx.ext.shell.term.TelnetTermOptions;

import java.net.InetAddress;

public class TelnetShell extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        ShellService service = ShellService.create(vertx,
                new ShellServiceOptions().setTelnetOptions(
                        new TelnetTermOptions().
                                setHost(InetAddress.getLocalHost().getHostAddress()).
                                setPort(4000)
                )
        );
        service.start();
    }
}

