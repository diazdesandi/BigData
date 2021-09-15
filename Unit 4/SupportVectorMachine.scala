// Import & Start Spark session
import org.apache.spark.sql.SparkSession;

// Load Machine Learning Libraries
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

// Creating a variable to use Spark on code.
val spark = SparkSession.builder().getOrCreate();

// Load the file
val bankfullData = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("Project/bank-full.csv")

// choos the columns
val cols = bankfullData.select("balance","day","duration","campaign","pdays","previous","y").withColumn("x", when(col("y") === "yes", 1).when(col("y") === "no", 0))

// Datafrane bank
val bank = cols.select("balance","day","duration","campaign","pdays","previous","x").toDF()

// Array with name
val vectorCols = Array("balance","day","duration","campaign","pdays","previous","x")

// Create a  VectorAssembler with data
val Assembler = new VectorAssembler().setInputCols(vectorCols).setOutputCol("features")

// Transform the data and save into a variable
val data = Assembler.transform(bank).toDF()

// Take the columns by the array
val cleanDf = data.select(col("x").as("label"),col("features"))

// Creating a new model
val svc = new LinearSVC().setMaxIter(10).setRegParam(0.1)

// Define the data
val separador = cleanDf.randomSplit(Array(0.7, 0.3), seed = 9876L)
val traindata = separador(0)
val testdata = separador(1)

// Training the model
val model = svc.fit(traindata)

// Results
val resultados = model.transform(testdata)
val evaluador = new MulticlassClassificationEvaluator().setMetricName("accuracy")

// Print accuracy
println(s"Accuracy = ${(evaluador)}")

// Printing time and duration of the algorithm
val time = System.nanoTime
val duration = (System.nanoTime - time) / 1e9d
println("Tiempo de ejecución: " + duration)