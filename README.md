# spark-scala

This maven project is for running a word count example in Scala IDE using spark scala libraries.  
To setup this project in Scala IDE I referred to this video : https://www.youtube.com/watch?v=aB4-RD_MMf0

Please following steps to setup your project.

1. git checkout this project in some directory.
2. Maven import it from scala ide.
3. Maven update the project. It will take few minutes to download the jars.
4. Maven install the project and see if build is success.
5. Right click on project and click on configure and change the nature to scala. Soon there will be a lot of errors on the problem view.
6. To get rid of those errors go in property and click on Scala Compiler - tick Use Project Settings and then select Fixed Scala installation 2.10.5 because spark is build with 2.10.
7. Now remove the scala libraries. right click on project -> configure build path -> libraries -> remove scala libraries.
8. Now go on source tab and make sure <prj>/src/main/scala folder is added in there, so that it take source files from this folder.
9. Now run the WrodCount.scala file and the output should be generated in food.count.txt directory project root folder. To view it refresh the project folder.


Spark streaming specific settings (dont change anything in zoo.cfg(zookeeper) and server.properties(kafka))

1. D:\kafka_2.11-0.10.0.1\bin\windows>zookeeper-server-start.bat ..\..\config\zookeeper.properties
2. D:\kafka_2.11-0.10.0.1\bin\windows>kafka-server-start.bat ..\..\config\server.properties
3. D:\kafka_2.11-0.10.0.1\bin\windows>kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
4. D:\kafka_2.11-0.10.0.1\bin\windows>kafka-console-producer.bat --broker-list localhost:9092 --topic test
5. D:\kafka_2.11-0.10.0.1\bin\windows>kafka-console-consumer.bat --zookeeper localhost:2181 --topic test --from-beginning

Now change run the scala class KafkaWordCount in spark examples directory by including it in the project. It has a spark streaming wordcount example and a producer. Both take different arguments, so please give arguments like this.
wordcount : localhost:2181 my-consumer-group test 1
producer : localhost:9092 test 2 5

When this classes will run there will be lot of logging printed on screen to suppress it create a src/main/resources directory and put custom log4j.properties inside it and change the log level to WARN, additionally we can put spark-defaults.conf in the same directory.

