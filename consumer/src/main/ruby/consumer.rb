eb = $vertx.event_bus()

eb.consumer("story_topic") { |message|
  puts "consumer receives: #{message.body()}"
  message.reply("Good story!")
}

puts "consumer is now running"