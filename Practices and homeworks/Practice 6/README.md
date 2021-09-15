# Practice 6 - Gradient-boosted tree classifier
> Here we import the all necessary files
```scala
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{GBTClassificationModel, GBTClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
```
> We load the data
```scala
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```
> Here we use the label indexed
```scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```
> Here we create an array with including the random split
```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
val gbt = new GBTClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setMaxIter(10).setFeatureSubsetStrategy("auto")
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
```
> Here we use the pipe line
```scala
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, gbt, labelConverter))
val model = pipeline.fit(trainingData)
val predictions = model.transform(testData)
predictions.select("predictedLabel", "label", "features").show(20)
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${1.0 - accuracy}")
val gbtModel = model.stages(2).asInstanceOf[GBTClassificationModel]
```
> Here we print the classification model
```scala
println(s"Learned classification GBT model:\n ${gbtModel.toDebugString}")
```