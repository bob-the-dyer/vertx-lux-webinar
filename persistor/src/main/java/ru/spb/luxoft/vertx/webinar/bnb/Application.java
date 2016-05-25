package ru.spb.luxoft.vertx.webinar.bnb;

import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.dropwizard.DropwizardMetricsOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void createClusteredVertx() throws UnknownHostException {
        final VertxOptions options = new VertxOptions()
                .setMetricsOptions(new DropwizardMetricsOptions()
                        .setEnabled(true)
                        .setJmxEnabled(true)
                        .setJmxDomain("vertx-metrics"))
                 /* in embedded mode clustered eventbus is listening on 127.0.0.1 but we need real network ip */
                .setClusterHost(InetAddress.getLocalHost().getHostAddress());
        Vertx.clusteredVertx(options, this::clusteredVertxCreated);
    }

    private void clusteredVertxCreated(AsyncResult<Vertx> result) {
        if (result.succeeded()) {
            final Vertx clusteredVertx = result.result();
            clusteredVertx.deployVerticle(new Persistor());
            clusteredVertx.deployVerticle(new TelnetShell());
        }
    }

}
