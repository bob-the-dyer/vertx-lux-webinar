import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import static java.lang.System.out;

public class TeamLeadVertical extends AbstractVerticle {

    MongoClient mongo;

    @Override
    public void start() throws Exception {
        mongo = MongoClient.createShared(vertx,
                new JsonObject().put("db_name", "verx-lux-webinar"));
        vertx.eventBus().consumer("greetings", this::persistGreeting);
        out.println("teamlead is running");
    }

    private void persistGreeting(Message<JsonObject> msg) {
        JsonObject greeting = msg.body();
        mongo.insert("greetings", greeting, res -> {
            if (res.succeeded()) {
                out.println("teamlead persists, counter:" +
                        msg.body().getInteger("counter"));
            } else {
                out.println(res.cause().getMessage());
            }
        });
    }
}