import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val dataf = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

var df = dataf.distinct()