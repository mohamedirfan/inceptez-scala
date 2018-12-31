package sparksqlworkouts

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext


object csvorcoperations
{
  def main(args:Array[String])
  {
       val conf = new SparkConf().setAppName("csvoperations").setMaster("local")
       val sc = new SparkContext(conf)
       sc.setLogLevel("ERROR")
       val sqlContext = new SQLContext(sc)
        import sqlContext.implicits._ 
       val df = sqlContext.read.option("header","true")
       .option("delimiter", ",")
       .csv("file:///home/hduser/sales.csv")
      
       df.createOrReplaceTempView("sales")
       sqlContext.sql("select * from sales").show()
       
       
       
  }
       
}