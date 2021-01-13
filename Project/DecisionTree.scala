// Import & Start Spark session
import org.apache.spark.sql.SparkSession;
val spark = SparkSession.builder().getOrCreate();

// Code to minimize errors.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Load Machine Learning Libraries
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.Pipeline