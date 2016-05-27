package ru.spb.luxoft.vertx.webinar.bnb;

import static java.lang.System.out;

public class StarterVerticle extends io.vertx.core.AbstractVerticle {
    @Override
    public void start() throws Exception {
        out.println("<--- STARTER ---> starts");
        final String starterVerticleName = System.getProperty("starter.verticle");
        vertx.deployVerticle(starterVerticleName,  h -> {
            if (h.succeeded()){
                out.println("####( STARTER )#### " + starterVerticleName + " was deployed");
            } else {
                out.println("####( STARTER )#### " + starterVerticleName + " was NOT deployed");
            }
        });
        out.println("<--- STARTER ---> finishes");
    }
}
