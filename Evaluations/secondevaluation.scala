// Import Apache Spark session
import org.apache.spark.sql.SparkSession
// We import this libraries to use the indexed and assembler
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler
val spark = SparkSession.builder().getOrCreate()

// 1. a) Spark Mllib import.
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

var dataFrame = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

// Here we create the district by the dataframe create previsuly
val df = dataFrame.distinct();

// 2. Column names
df.columns

// 3. Schema
df.printSchema()

// 4. Print the first 5 columns.
df.head (5)

// 5. Use describe method.
df.describe().show()

// 6. Categorical data
val tagIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(df)
val index = tagIndexer.transform(df).drop("species").withColumnRenamed("indexedLabel", "label")
index.describe().show()

// 7. Classification model
// First we need to import the vectorassembler to able join or assemble the data
val assem = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features");
// After this we have to index it
val  features = assem.transform(index);
// And separate the data to manage more easily
val split = features.randomSplit(Array(0.6, 0.4), seed = 1234L);
val train = split(0);
val test = split(1);
// We input some random numbers
val layers = Array[Int](4, 5, 4, 3);
// Here we create the object to train the model
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100);
// Now we can create the model trained
val model = trainer.fit(train);
//Here we get the result
val result = model.transform(test);
val predictionAndLabels = result.select("prediction", "label");
// To know the rate of prediction we invoque the next line
predictionAndLabels.show;

// 8. Print classification model results
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
// Finnaly we print the conclution representated on numbers
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")