import org.apache.spark.sql.SparkSession
import org.apache.spark.internal.Logging
import org.apache.spark.sql.catalyst.dsl.expressions.DslSymbol

import scala.math.random

object SparkTest {
	def main(args: Array[String]) {

		val spark = SparkSession
			.builder
			.master("local")
			.appName("Spark SQL")
			.config("spark.sql.catalogImplementation","hive")
			.enableHiveSupport()
			.getOrCreate()

		spark.sparkContext.setLogLevel("WARN")
		println("==== Checking tables")
		spark.sql("set").show()
		spark.sql("Show databases").show()
		spark.sql("Show tables").show()
		println("==== SQL DB ====")
		val db = "db1"
		val tbl = "aapl"
		spark.sql("CREATE DATABASE IF NOT EXISTS " + db)
		spark.sql("USE " + db)
//		spark.sql("CREATE TABLE db.sample (\n    id bigint,\n    data string,\n    category string)\nUSING iceberg\nPARTITIONED BY (category)")
		// https://query1.finance.yahoo.com/v7/finance/download/AAPL?period1=345427200&period2=1642982400&interval=1d&events=history&includeAdjustedClose=true
		println("==== SQL TABLE ====")
		spark.sql("DROP TABLE IF EXISTS " + db + "." + tbl)
		spark.sql(s"CREATE TABLE IF NOT EXISTS AAPL (Date STRING, Open FLOAT, High FLOAT, Low FLOAT, Close FLOAT, AdjClose FLOAT, Volume INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TextFile LOCATION '/tmp/sql/${db}/${tbl}'")
		spark.sql(s"DESCRIBE EXTENDED ${db}.${tbl}").show()
		spark.sql(s"select * from ${db}.${tbl} LIMIT 10").show()
//		val df = spark.sql(s"select * from ${db}.${tbl} LIMIT 10").show()
//		df.show()
		spark.stop()
	}
}
