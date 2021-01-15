// Import apache spark
import org.apache.spark.sql.SparkSession;

// Creating a variable to use spark on code.
val spark = SparkSession.builder().getOrCreate();

// Code to minimize errors.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Load Machine Learning Libraries
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString,StringIndexer,VectorAssembler,VectorIndexer}
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier

// Load bank-full.csv.
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("Project/bank-full.csv")

// LabelIndexer created for using column y as index.
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(data)

// Columns stored in a new vector.
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val features = assembler.transform(data)

// New vector index using max 4 categories.
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(features)

// Training and test arrays created.
val Array(traindata, testdata) = features.randomSplit(Array(0.7, 0.3))

// To create a desecion tree we use the next line
val ds = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

// New branch created for predictions
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

// Pipeline calling the estimator.
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, ds, labelConverter))

// Training model created
val model = pipeline.fit(traindata)

// Data transformation in the model with the test data
val predictions = model.transform(testdata)

// Predictions printed
predictions.select("predictedLabel", "y", "features").show(5)

// Tree model generated
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"tree model:\n ${treeModel.toDebugString}")

// Accuracy calculated and geted the result.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)

// Here we print the precision
println(s"Precision = ${(accuracy)}")