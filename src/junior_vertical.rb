eb = $vertx.event_bus()

eb.consumer("greetings") { |message|
  puts "junior receives: #{message.body()}"
  message.reply("Thanks!")
}

puts "junior is running"