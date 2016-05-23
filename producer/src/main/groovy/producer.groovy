import io.vertx.core.json.JsonObject

def story = [
        "Beauty and the Beast",
        "Once upon a time there lived a wealthy merchant and his three daughters.",
        "One day, the father was to go to a far-off place and he asked his daughters what they wanted on his return.",
        "The first and the second daughter asked for lovely dresses.",
        "But the third daughter, whose name was Beauty, said, 'Father, I only need a rose plucked by your hand.'",
        "The merchant, on his way back, had to cross through the deep forest. It was dark and the merchant tried to find a place to sleep.",
        "He suddenly huge table with delicious food and he ate it all.",
        "Then the merchant went into the bedroom and slept on a soft and fluffy bed.",
        "The next day, too, the merchant did not find anyone in the castle.",
        "He saw a beautiful rose bush growing in the lawn and remembered Beauty's gift. He plucked a red rose from the bush.",
        "Suddenly, a ferocious looking beast sprang out of the bush.",
        "He was wearing fine silk clothes and roared, 'I gave you food and a bed to sleep in! And now, you are stealing my roses!'",
        "The merchant was frightened and told the Beast about Beauty's gift.",
        "The Beast decided to let him go only if he promised to send Beauty to this castle.",
        "The merchant agreed and ran back home. He cried and told his daughters about the Beast.",
        "But Beauty loved her father a lot and agreed to go stay with the Beast.",
        "The Beast treated Beauty with a lot of kindness. He was never rude to her.",
        "He let her stay in the biggest room and let her roam in the beautiful garden.",
        "Beauty would sit near the fireplace and sew while the Beast kept her company.",
        "At first, Beauty was afraid of the Beast but slowly, she began to like him.",
        "One day, the Beast asked Beauty to marry him, but she refused. She was still afraid of his fearful-looking face.",
        "The Beast still treated her kindly and with a lot of love. Beauty missed her father a lot.",
        "The Beast gave her a magic mirror and said, 'Look at the mirror and you can see your family. Now you won't feel lonely anymore.",
        "One day, Beauty looked in the mirror and saw that father was very ill and dying.",
        "She went to the Beast and pleaded and cried, 'Please let me go home! I only want to see my father before he dies!'",
        "But the Beast roared, 'No! You promised you would never leave this castle!' Saying this, he stormed out of the room.",
        "But after some time, he came to Beauty and said, 'You may go to stay with your father for seven days.",
        "But you must promise to return after that.' Beauty was very happy and agreed.",
        "Then she left and went to stay with her father. Her father, on seeing Beauty, felt very happy and soon recovered.",
        "Beauty stayed with her family for seven days and more. She forgot the Beast and his castle.",
        "But one night, she had a terrible nightmare in which she saw the Beast was very ill and about to die.",
        "He was crying, 'Beauty, please come back!'",
        "Beauty woke up and went back to the castle because she did not mean to hurt the Beast.",
        "She cried and said, 'Please don't die, Beast! I will live with you forever!'",
        "The Beast miraculously changed into a handsome prince.",
        "He said, 'I was under a curse all these years and could only be relieved when someone fell in love with me.",
        "I am now cured of the curse because you truly love me.'",
        "And then, Beauty and the Beast were married and together they lived happily ever after."
]

def eb = vertx.eventBus()
i = 0;
vertx.setPeriodic(5000, { v ->
    def map = [
            "from"   : "story teller",
            "message": story[i],
            "counter": i
    ]
    if (i < story.size() - 1) {
        i++;
    } else {
        i = 0;
    }

    def json = new JsonObject(map)
    println "producer sends: " + json
    eb.publish("story_topic", json)
})
println "producer is now running"
