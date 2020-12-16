# Practice 9 One vs Rest classifier
> Import libraries
```scala
import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
```
> Read file
```scala
var inputData = spark.read.format("libsvm").load("/sample_multiclass_classification_data.txt")
```
> Create array
```scala
val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))
```
> Use classifier, in this case we use Logistic Regression.
```scala
val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)
> New One vs rest classifier
```scala
val ovr = new OneVsRest().setClassifier(classifier)
val ovrModel = ovr.fit(train)
```
> To get the predictions we add the test to predictions code line
```scala
val predictions = ovrModel.transform(test)
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
```
> Here we find the accuracy
```scala
val accuracy = evaluator.evaluate(predictions)
```
> And print the result
```scala
println(s"Test Error = ${1 - accuracy}")
```