package ru.spb.luxoft.vertx.webinar.bnb;

import static java.lang.System.out;

public class StarterVerticle extends io.vertx.core.AbstractVerticle {
    @Override
    public void start() throws Exception {
        out.println("<--- STARTER ---> starts");
        vertx.deployVerticle("1234");
        out.println("<--- STARTER ---> finishes");
    }
}
