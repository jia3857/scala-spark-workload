/* SimpleApp.scala */
import org.apache.spark.sql.SparkSession

object SimpleApp {
	def main(args: Array[String]) {
		//val sparkHome = sys.env.get("SPARK_HOME")
		val sparkHome = System.getenv("SPARK_HOME")
		println("sparkHome = " + sparkHome)
		// val logFile = sparkHome + "/README.md" // Should be some file on your system
		val logFile = "/tmp/README.md"
		val spark = SparkSession.builder
			.master("local[2]")
			.appName("Simple Application").getOrCreate()
		val logData = spark.read.textFile(logFile).cache()
		val numAs = logData.filter(line => line.contains("a")).count()
		val numBs = logData.filter(line => line.contains("b")).count()
		println(s"Lines with a: $numAs, Lines with b: $numBs")
		spark.stop()
	}
}
