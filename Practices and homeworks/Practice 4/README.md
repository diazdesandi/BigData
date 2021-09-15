# Practice 4 - Decision Tree Classifier

> Import decision tree classifiers and evaluators from Machine Learning library,
```scala
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
```
> Import Spark Session
```scala
import org.apache.spark.sql.SparkSession;
```
> Here we start the object
```scala
object DecisionTree {
```
> After this we should define the class
```scala
  def main(args: Array[String]): Unit = {
    val spark = SparkSession .builder.appName("dtree").getOrCreate();
 ```
> Load file as dataframe.
```scala
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt");
```
> Include all datased lables into index.
```scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data);
```
> Identify and index categorical features.
```scala
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data);
```
> Data split, training and testing data.
```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3));
```
> Decision Tree model training
```scala
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures");
```
> Convert index label to original label.
```scala
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels);
```
> Merge indexer and tree using pipeline.
```scala
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter));
```
> Model training
```scala
val model = pipeline.fit(trainingData);
```
> Predictions
```scala
val predictions = model.transform(testData);
```
> Rows to display
```scala
predictions.select("predictedLabel", "label", "features").show(5);
```
> Select prediction and label.
```scala
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy");
```
> Compute accuracy from evaluator.
```scala
val accuracy = evaluator.evaluate(predictions);
println(s"Test Error = ${(1.0 - accuracy)}");
```
> Print model.
```scala
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}");
  }
}
```