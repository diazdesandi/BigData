# Practice 8 - Linear Support Vector Machine
> Import libraries
```scala
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.sql.SparkSession
```
>. Load training data
```scala
val training = spark.read.format("libsvm").load("sample_libsvm_data.txt")
val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
```
> Fit model
```scala
val lsvcModel = lsvc.fit(training)
```
> Print coefficients and intercept for Linear Support Vector Machine
```scala
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
```
