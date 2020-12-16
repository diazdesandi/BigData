# Second Evaluation
Import Apache Spark session
```scala
import org.apache.spark.sql.SparkSession
```
We import this libraries to use the indexed and assembler
```scala
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler
val spark = SparkSession.builder().getOrCreate()
```
1. a) Spark Mllib import.
```scala
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
```
CVS loaded
```scala
var dataFrame = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")
```
Here we create the distinc by the previously created dataframe
```scala
val df = dataFrame.distinct();
```
2. Column names
```scala
df.columns
```
3. Schema
```scala
df.printSchema()
```
4. Print the first 5 columns.
```scala
df.head (5)
```
5. Use describe method.
```scala
df.describe().show()
```
6. Categorical data
```scala
val tagIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(df)
val index = tagIndexer.transform(df).drop("species").withColumnRenamed("indexedLabel", "label")
index.describe().show()
```
7. Classification model
First we need to import Vectorassembler to be able join or assemble the data
```scala
val assem = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features");
```
After this we have to index it
```scala
val  features = assem.transform(index);
```
And separate the data to manage more easily
```scala
val split = features.randomSplit(Array(0.6, 0.4), seed = 1234L);
val train = split(0);
val test = split(1);
```
We input some random numbers
```scala
val layers = Array[Int](4, 5, 4, 3);
```
Here we create the object to train the model
```scala
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100);
```
Now we can create the model trained
```scala
val model = trainer.fit(train);
```
Here we get the result
```scala
val result = model.transform(test);
val predictionAndLabels = result.select("prediction", "label");
```
To know the rate of prediction we invoque the next line
```scala
predictionAndLabels.show;
```

8. Print classification model results
```scala
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
```
Finnaly we print the conclution representated on numbers
```scala
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```