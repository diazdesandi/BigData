// Import libraries
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.sql.SparkSession

// Load training data
val training = spark.read.format("libsvm").load("sample_libsvm_data.txt")
val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
// Fit model
val lsvcModel = lsvc.fit(training)
// Print coefficients and intercept for Linear Support Vector Machine
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")