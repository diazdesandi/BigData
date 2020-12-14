// Import Apache Spark session
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

// 1. a) Spark Mllib import.
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

val dataf = spark.read.option("header", "true").option("inferSchema","true")csv("/home/rene/Documents/GitHub/BigData/Evaluations/iris.csv")

var df = dataf.distinct()

// 2. Column names
df.columns

// 3. Schema
df.printSchema()

// 4. Print the first 5 columns.
df.head (5)

// 5. Use describe method.
df.describe().show()

// 6. Categorical data

// 7. Classification model

// 8. Print classification model results