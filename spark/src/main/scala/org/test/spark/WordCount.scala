package org.test.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j._
/**
 *  Refer this link to download winutils.exe http://stackoverflow.com/questions/19620642/failed-to-locate-the-winutils-binary-in-the-hadoop-binary-path
 * Eclipse->Debug/Run Configurations -> Environment (tab) -> and added
		Variable: HADOOP_HOME
		Value: <>/bin/winutils.exe - just give <>
		Important links : https://www.youtube.com/watch?v=aB4-RD_MMf0
		http://www.devinline.com/2016/01/apache-spark-setup-in-eclipse-scala-ide.html
 * 
 * author : gkhare
 */
object WordCount {
  
  def main(args: Array[String])={
    
     //Start the Spark context
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local")
    val sc = new SparkContext(conf)

    //Read some example file to a test RDD
    val test = sc.textFile("food.txt")

    test.flatMap { line => //for each line
      line.split(" ") //split the line in word by word. NB: we use flatMap because we return a list
    }
    .map { word => //for each word
      (word,1) //return a key/value tuple, with the word as key and 1 as value
    }
    .reduceByKey(_ + _) //sum all of the value with same key
    .saveAsTextFile("food23.counts.txt") //save to a text file

    //Stop the Spark context
    sc.stop
    
  }
}