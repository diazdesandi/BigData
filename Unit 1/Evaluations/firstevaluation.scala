// Start Spark session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// Read dataset
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv")

// Columns
dataset.columns

//Scheme
dataset.Scheme

//First 5 columns
dataset.head(5)

//Learn about the dataframe
dataset.describe().show()