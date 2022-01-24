name := "Test"

version := "1.0"

scalaVersion := "2.12.11"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % "3.2.0",
	"org.apache.spark" %% "spark-hive" % "3.2.0",
	"org.apache.spark" %% "spark-" % "3.2.0",
	"org.apache.spark" %% "spark-sql" % "3.2.0"
)
