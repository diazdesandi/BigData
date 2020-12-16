# Practice 7 - Multilayer perceptron classifier
> Here we Import thye all neccesary imports.
```scala
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
```
> We most use a object to usea like classifier class
```scala
object MultilayerPerceptronClassifierExample {
  def main(): Unit = {
```
> Here we use the spark session
```scala
val spark = SparkSession .builder.appName("MultilayerPerceptronClassifierExample").getOrCreate();
```
> Here we load the all data located in the same files
```scala
val data = spark.read.format("libsvm").load("sample_multiclass_classification_data.txt");
val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L);
val train = splits(0);
val test = splits(1);
```
> Here we invoque the trainer
```scala
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100);
val model = trainer.fit(train);
val result = model.transform(test);
val predictionAndLabels = result.select("prediction", "label");
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy");
```
> To print the accuracy we use the next line
```scala
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}");
> And stop the procces
```scala
spark.stop();
  }
}
```