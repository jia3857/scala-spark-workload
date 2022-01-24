# README.md
Ref: https://xd-deng.com/render_html/step_by_step_to_package_spark_app_scala.html

```shell
# Your first build.sbt file
$ cat > build.sbt << EOF
name := "Simple Project"

version := "1.0"

scalaVersion := "2.12.15"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.0"
EOF

# Your directory layout should look like this
$ find .
.
./build.sbt
./src
./src/main
./src/main/scala
./src/main/scala/SimpleApp.scala
./src/main/scala/SparkTest.scala

$SPARK_HOME/bin/spark-submit \
  --class SparkPi \
  /Users/loaner/work/src/github.com/josh0yeh/scala-spark-workload/target/scala-2.11/test_2.11-1.0.jar

$SPARK_HOME/bin/spark-submit \
  --class SparkTest /Users/loaner/work/src/github.com/josh0yeh/scala-spark-workload/target/scala-2.12/test_2.12-1.0.jar 100 2> /dev/null
```
