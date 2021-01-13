// Import & Start Spark session
import org.apache.spark.sql.SparkSession;
val spark = SparkSession.builder().getOrCreate();

// Code to minimize errors.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Load Machine Learning Libraries
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.{IndexToString,StringIndexer,VectorAssembler,VectorIndexer}

// Load bank-full.csv.
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")