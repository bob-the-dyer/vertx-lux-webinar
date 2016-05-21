var Router = require("vertx-web-js/router");
var BridgeEvent = require("vertx-web-js/bridge_event");
var SockJSHandler = require("vertx-web-js/sock_js_handler");
var StaticHandler = require("vertx-web-js/static_handler");

var router = Router.router(vertx);
router.route("/eventbus/*").handler(
    SockJSHandler.create(vertx).bridge({
        "outboundPermitteds": [
            {"address": "greetings"}
        ]
    }).handle);
router.route().handler(StaticHandler.create().handle);
vertx.createHttpServer().requestHandler(router.accept).listen(8080);
console.log("web is running");