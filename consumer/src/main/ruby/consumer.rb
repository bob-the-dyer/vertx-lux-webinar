eb = $vertx.event_bus()

eb.consumer("story_topic") { |message|
  puts "####( CONSUMER )### receives: #{message.body()}"
  message.reply("Good story!")
}

puts "<--- CONSUMER ---> is now running"