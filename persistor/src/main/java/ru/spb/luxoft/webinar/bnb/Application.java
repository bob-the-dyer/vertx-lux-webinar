package ru.spb.luxoft.webinar.bnb;

import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
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
        //in embedded mode clustered eventbus is listening on 127.0.0.1 but we need real network ip
        final VertxOptions options = new VertxOptions()
                .setClusterHost(InetAddress.getLocalHost().getHostAddress());
        Vertx.clusteredVertx(options, this::clusteredVertxCreated);
    }

    private void clusteredVertxCreated(AsyncResult<Vertx> result) {
        if (result.succeeded()) {
            final Vertx clusteredVertx = result.result();
            clusteredVertx.deployVerticle(new Persistor());
        }
    }

}
