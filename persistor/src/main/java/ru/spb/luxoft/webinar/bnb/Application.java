package ru.spb.luxoft.webinar.bnb;

import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void createClusteredVertx() {
        final VertxOptions options = new VertxOptions()
                .setClustered(true)
                .setClusterHost("how the fuck I should specify the internal docker host??"); //TODO
        Vertx.clusteredVertx(options, this::clusteredVertxCreated);
    }

    private void clusteredVertxCreated(AsyncResult<Vertx> result) {
        if (result.succeeded()) {
            final Vertx clusteredVertx = result.result();
            clusteredVertx.deployVerticle(new Persistor());
        }
    }

}
