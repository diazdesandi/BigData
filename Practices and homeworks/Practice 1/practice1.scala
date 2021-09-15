import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.evaluation.MulticlassMetrics
Logger.getLogger("org").setLevel(Level.ERROR)

// Here we import the sark to use it on any line
val spark = SparkSession.builder().getOrCreate();
// And import the all data with the next line
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("advertising.csv");
// And print the schema
data.printSchema();
data.head(1);
val colnames = data.columns
// We choose the lines and the strows
val firstrow = data.head(1)(0);
println("Example data row");
// We need to use a for loop the all information
for(ind <- Range(1, colnames.length)){
println(colnames(ind))
println(firstrow(ind))
// Here we jump to the next line to display more clear
println("\n")};

val timedata = data.withColumn("Hour",hour(data("Timestamp")));
val logregdata = timedata.select(data("Clicked on Ad").as("label"), $"Daily Time Spent on Site", $"Age", $"Area Income", $"Daily Internet Usage", $"Hour", $"Male");
val assembler = (new VectorAssembler() .setInputCols(Array("Daily Time Spent on Site", "Age","Area Income","Daily Internet Usage","Hour","Male").setOutputCol("features"));
val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 12345);
val lr = new LogisticRegression();
val pipeline = new Pipeline().setStages(Array(assembler, lr));
val model = pipeline.fit(training);
val results = model.transform(test);
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd;
val metrics = new MulticlassMetrics(predictionAndLabels);

// Here we print the matrix
println("Confusion matrix:");
println(metrics.confusionMatrix);

// And find the all accuracy
metrics.accuracy;