package spark.ml.cookbook.chapter6
import org.apache.spark.mllib.linalg.{Vector, Vectors} 
import org.apache.spark.mllib.regression.LabeledPoint 
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.evaluation.{BinaryClassificationMetrics, MulticlassMetrics, MultilabelMetrics, binary}
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.log4j.Logger
import org.apache.log4j.Level

val data = sc.textFile("iris-data-prepared.txt")

val NaiveBayesDataSet = data.map { line => val 
columns = line.split(',')
LabeledPoint(columns(4).toDouble,
Vectors.dense(
columns(0).toDouble,
columns(1).toDouble,
columns(2).toDouble,
columns(3).toDouble))
}

println(" Total number of data vectors =", 
NaiveBayesDataSet.count())

val distinctNaiveBayesData = NaiveBayesDataSet.distinct() 
println("Distinct number of data vectors = ", 
distinctNaiveBayesData.count())

distinctNaiveBayesData.collect().take(10).foreach(println(_))

val allDistinctData =
distinctNaiveBayesData.randomSplit(Array(.80,.20),10L)
val trainingDataSet = allDistinctData(0)
val testingDataSet = allDistinctData(1)

println("number of training data =",trainingDataSet.count())
println("number of test data =",testingDataSet.count())

val myNaiveBayesModel = NaiveBayes.train(trainingDataSet)

val predictedClassification = testingDataSet.map( x => (myNaiveBayesModel.predict(x.features), x.label))

Metricas
val metrics = new MulticlassMetrics(predictedClassification)

val confusionMatrix = metrics.confusionMatrix 
println("Confusion Matrix= n",confusionMatrix)

val myModelStat=Seq(metrics.precision)
myModelStat.foreach(println(_))