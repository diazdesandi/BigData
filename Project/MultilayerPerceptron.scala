// Import & Start Spark session
import org.apache.spark.sql.SparkSession;
val spark = SparkSession.builder().getOrCreate();

// Code to minimize errors.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Load Machine Learning Libraries
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString,StringIndexer,VectorAssembler,VectorIndexer}

// Load bank-full.csv.
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

// LabelIndexer created for using column y as index.
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label").fit(data)

// Columns stored in a new vector.
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val features = assembler.transform(data)

// Training and test arrays created using seed 1234L
val splits = data.randomSplit(Array(0.7, 0.3), seed = 1234L);
val train = splits(0);
val test = splits(1);

// Array for each algorithm level.
val capas = Array[Int](7, 5, 5, 2)

// Multilayer Perceptron classifier created
val mlp = new MultilayerPerceptronClassifier().setLayers(capas).setLabelCol("label").setFeaturesCol("features").setPredictionCol("prediction").setBlockSize(128).setSeed(1234L).setMaxIter(100)

// Pipeline calling the classifier.
val pipeline = new Pipeline().setStages(Array(labelIndexer,assembler,mlp))

// Training model created
val model = pipeline.fit(trainingData)

// Data transformation in the model with the test data
val predictions = model.transform(testdata)

// Perceptron model generated
val predictionAndLabels = predictions.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

// Accuracy
println("Test set accuracy = " + evaluator.evaluate(predictionAndLabels))