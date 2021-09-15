# Practice 3 - Basic Statistics

> We import all libraries
```scala
package org.apache.spark.examples.ml
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.sql.SparkSession
```
> we create the spark session variable
```scala
val spark = SparkSession.builder.appName("RandomForestClassifierExample").getOrCreate()
```
```scala
val data = spark.read.format("libsvm").load("/sample_libsvm_data.txt")
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)

val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```
> Create an array to train the all data
```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
val rf = new RandomForestClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setNumTrees(10)
```
> Here we added to string
```scala
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
```
```scala
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))
val model = pipeline.fit(trainingData)
val predictions = model.transform(testData)
```

```scala
predictions.select("predictedLabel", "label", "features").show(5)
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")


val accuracy = evaluator.evaluate(predictions)
```
```scala
println(s"Test Error = ${(1.0 - accuracy)}")
val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel]
```
> Here we print the result
```scala
println(s"Learned classification forest model:\n ${rfModel.toDebugString}")
```