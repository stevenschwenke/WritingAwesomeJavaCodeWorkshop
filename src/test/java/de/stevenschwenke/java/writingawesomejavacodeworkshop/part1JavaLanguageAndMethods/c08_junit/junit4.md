... will be shown in detail in part 3. Just one quick mention: Do you know about the message parameter nearly every junit method has? Consider this:
  
          assertNotNull(stream);
          assertNotNull("Stream should not be null because test file exists", stream);