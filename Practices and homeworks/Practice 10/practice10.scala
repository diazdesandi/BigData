// Import Machine Learning libraries for Naive Bayes, Vectors and Metrics. Also, Spark session import.
package spark.ml.cookbook.chapter6
import org.apache.spark.mllib.linalg.{Vector, Vectors} 
import org.apache.spark.mllib.regression.LabeledPoint 
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.evaluation.{BinaryClassificationMetrics, MulticlassMetrics, MultilabelMetrics, binary}
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.log4j.Logger
import org.apache.log4j.Level

// Textfile loaded.
val data = sc.textFile("iris-data-prepared.txt")

/* 
Transform textfile into dataset, using species column as reference.
sepal_length, sepal_width, petal_length, petal_width are transformed into vectors
*/
val NaiveBayesDataSet = data.map { line => val 
columns = line.split(',')
LabeledPoint(columns(4).toDouble,
Vectors.dense(
columns(0).toDouble,
columns(1).toDouble,
columns(2).toDouble,
columns(3).toDouble))
}

// Delete duplicated vectors.
println(" Total number of data vectors =", 
NaiveBayesDataSet.count())

val distinctNaiveBayesData = NaiveBayesDataSet.distinct() 
println("Distinct number of data vectors = ", 
distinctNaiveBayesData.count())

// Visualize how we are saving data.
distinctNaiveBayesData.collect().take(10).foreach(println(_))

// Randomly distributed data to make a test and training dataset.
val allDistinctData =
distinctNaiveBayesData.randomSplit(Array(.80,.20),10L)
val trainingDataSet = allDistinctData(0)
val testingDataSet = allDistinctData(1)

println("number of training data =",trainingDataSet.count())
println("number of test data =",testingDataSet.count())

// Naive Bayes model created using Mllib functions.
val myNaiveBayesModel = NaiveBayes.train(trainingDataSet)

// Data being read and compared
val predictedClassification = testingDataSet.map( x => (myNaiveBayesModel.predict(x.features), x.label))

// Metrics
val metrics = new MulticlassMetrics(predictedClassification)

// Confussion Maatrix
val confusionMatrix = metrics.confusionMatrix 
println("Confusion Matrix= n",confusionMatrix)

// Precision metrics
val myModelStat=Seq(metrics.precision)
myModelStat.foreach(println(_))