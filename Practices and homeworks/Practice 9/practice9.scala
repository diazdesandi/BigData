// Import libraries
import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

// Read file
var inputData = spark.read.format("libsvm").load("/sample_multiclass_classification_data.txt")
// Create array
val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))
// Use classifier, in this case we use Logistic Regression.
val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)
// New One vs rest classifier
val ovr = new OneVsRest().setClassifier(classifier)
val ovrModel = ovr.fit(train)
// To get the predictions we add the test to predictions code line
val predictions = ovrModel.transform(test)
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
// Here we find the accuracy
val accuracy = evaluator.evaluate(predictions)
// And print the result
println(s"Test Error = ${1 - accuracy}")