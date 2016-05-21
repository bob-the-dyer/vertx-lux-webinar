import io.vertx.core.json.JsonObject

def eb = vertx.eventBus()
i = 1;
vertx.setPeriodic(5000, { v ->
    def map = [
            "from"    : "producer",
            "message" : "The beauty and the beast is a great story",
            "counter" : i++
    ]
    def json = new JsonObject(map)
    println "producer sends: " + json
    eb.publish("story_topic", json)
})
println "producer is now running"