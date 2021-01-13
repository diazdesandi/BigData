// Import & Start Spark session
import org.apache.spark.sql.SparkSession;
val spark = SparkSession.builder().getOrCreate();

// Code to minimize errors.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Load Machine Learning Libraries
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString,StringIndexer,VectorAssembler}
import org.apache.spark.ml.classification.{DecisionTreeClassifier,DecisionTreeClassifierModel}

// Load bank-full.csv.
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

// LabelIndexer created for using column y as index.
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(data)

// Columns stored in a new vector.
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val features = assembler.transform(data)

// New vector index using max 4 categories.
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(features)

// Training and test arrays created.
val Array(traindata, testada) = features.randomSplit(Array(0.7, 0.3))

// Decision Tree object created
val ds = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

// New branch created for predictions
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
