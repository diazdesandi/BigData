// Here we import the all elements neccesary
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.ml.attribute.Attribute

// We most import the file to can manage
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt");
//Here we create a label
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data);
val indexed =  labelIndexer.transform(data)
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data);
// Here we create the array with the all elements
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3));
// Creating the randon classifier and set numbers
val rf = new RandomForestClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setNumTrees(10);
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels);
// We use a pipe line with the next line
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, rf, labelConverter));
// And use a model to can manage and analice future data prediction
val model = pipeline.fit(trainingData);
// Here we fint the predictions
val predictions = model.transform(testData);
// Choose the predictions
predictions.select("predictedLabel", "label", "features").show(5);
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy");
val accuracy = evaluator.evaluate(predictions);
println(s"Test Error = ${(1.0 - accuracy)}");
val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel];
// And print the all model calledrfModel
println(s"Learned classification forest model:\n ${rfModel.toDebugString}");