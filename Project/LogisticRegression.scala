// Import & Start Spark session
import org.apache.spark.sql.SparkSession;
val spark = SparkSession.builder().getOrCreate();

// Code to minimize errors.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Load Machine Learning Libraries
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.feature.{IndexToString,StringIndexer,VectorAssembler,VectorIndexer}
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.ml.classification.LogisticRegression

// Load bank-full.csv.
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("Project/bank-full.csv")

// LabelIndexer created for using column y as index.
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label").fit(data)
val dataIndexed = labelIndexer.transform(data)

// Columns stored in a new vector.
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")

// Training and test arrays created using seed 12345
val Array(trainingdata, testdata) = dataIndexed.randomSplit(Array(0.7, 0.3), seed = 12345)

// Initialize Logistic Regression
val lr = new LogisticRegression()

// Pipeline calling the estimator.
val pipeline = new Pipeline().setStages(Array(assembler,lr))

// Training model created
val model = pipeline.fit(trainingdata)

// Data transformation in the model with the test data
val predictions = model.transform(testdata)

// we calculate the predictions of the model.
val predictionAndLabels = predictions.select($"prediction",$"label").as[(Double, Double)].rdd

// We create a metric variable to have the data of the prediction evaluator
val metrics = new MulticlassMetrics(predictionAndLabels)

// Confusion matrix
println(metrics.confusionMatrix)

// Model accuracy
println(s"Precision = ${(metrics.accuracy)}")

// Printing time and duration of the algorithm
val time = System.nanoTime
val duration = (System.nanoTime - time) / 1e9d
println("Tiempo de ejecución: " + duration)