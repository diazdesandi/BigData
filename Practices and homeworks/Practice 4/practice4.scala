// Import decision tree classifiers and evaluators from Machine Learning library,
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
// Import Spark Session
import org.apache.spark.sql.SparkSession

object DecisionTree {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("dtree")
      .getOrCreate()

// Load file as dataframe.
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")

// Include all datased lables into index.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)

// Identify and index categorical features.
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)

// Data split, training and testing data.
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

// Decision Tree model training
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

// Convert index label to original label.
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

// Merge indexer and tree using pipeline.
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

// Model training
val model = pipeline.fit(trainingData)

// Predictions
val predictions = model.transform(testData)

// Rows to display
predictions.select("predictedLabel", "label", "features").show(5)

// Select prediction and label.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")

// Compute accuracy from evaluator.
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")

// Print model.
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")

  }
}