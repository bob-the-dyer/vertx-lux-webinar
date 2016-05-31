package ru.spb.luxoft.vertx.webinar.bnb;

import io.vertx.core.Launcher;

public class Starter {

    public static void main(String[] args) {
        Launcher.main(new String[]{"run", System.getProperty("start.verticle"), System.getProperty("vertx.options")});
    }

}
