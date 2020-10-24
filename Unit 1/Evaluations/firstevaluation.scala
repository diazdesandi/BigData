// Import & Start Spark session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// Here we asign the dataset into a variable to manage
// and load the csv netflix
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv")

// Here we access to Columns and discover names
// and to know what colimns have the csv
dataset.columns

//Here we print the schema to get the information
dataset.printSchema()

//First 5 columns
dataset.head(5)

//Learn about the dataset
dataset.describe().show()

//New data frame created, HV column added, High and Volume columns relantionship.
val NewDT = dataset.withColumn("HV Ratio", dataset("High") + df("Volume"))
NewDT.show()