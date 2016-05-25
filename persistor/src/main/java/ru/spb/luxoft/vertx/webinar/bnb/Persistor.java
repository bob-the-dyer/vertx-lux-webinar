package ru.spb.luxoft.vertx.webinar.bnb;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import static java.lang.System.out;

public class Persistor extends AbstractVerticle {

    private MongoClient mongo;

    @Override
    public void start() throws Exception {
        mongo = MongoClient.createShared(vertx,
                new JsonObject()
                        .put("host", "lux-mongo")
                        .put("db_name", "luxdatabase")
        );
        vertx.eventBus().consumer("story_topic", this::persistMessage);
        out.println("<--- PERSISTOR ---> is now running");
    }

    @Override
    public void stop() throws Exception {
        out.println("<--- PERSISTOR ---> is now stopped");
    }

    private void persistMessage(Message<JsonObject> msg) {
        JsonObject message = msg.body();
        mongo.insert("messages", message, res -> {
            if (res.succeeded()) {
                out.println("####( PERSISTOR )#### inserts, counter:" +
                        msg.body().getInteger("counter"));
            } else {
                out.println(res.cause().getMessage());
            }
        });
    }

}
