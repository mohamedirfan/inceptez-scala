package sparksqlworkouts

import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession

object hiveinsert
{
  def main(args:Array[String])
  {
        //val warehouseLocation = "file:${system:user.dir}/spark-warehouse"
       val conf = new SparkConf().setAppName("hive-insert").setMaster("local")
       val sc = new SparkContext(conf)
       val spark = SparkSession
      .builder()
      //added code
	  //.config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()
       import spark.implicits._
       import spark.sql
       sql("drop table src")
       sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'")
       sql("LOAD DATA LOCAL INPATH '/home/hduser/sampledata' INTO TABLE src")
       sql("SELECT * FROM src").show()
       
  }
       
}