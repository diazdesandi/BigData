// Import & Start Spark session
import org.apache.spark.sql.SparkSession;

// Here Inicialice the variable spark to work with it
val spark = SparkSession.builder().getOrCreate();

// Load the file
val bankfullData = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

// Display the data
bankfullData.show(10)

// Show data schema
bankfullData.printSchema()

// choos the columns
val cols = bankfullData.select("balance","day","duration","campaign","pdays","previous","y").withColumn("x", when(col("y") === "yes", 1).when(col("y") === "no", 0))

// Datafrane bank
val bank = cols.select("balance","day","duration","campaign","pdays","previous","x").toDF()

// Display data
bank.show(5)
bank.printSchema()

// Array with name
val vectorCols = Array("balance","day","duration","campaign","pdays","previous","x")

// Create a  VectorAssembler with data
val Assembler = new VectorAssembler().setInputCols(vectorCols).setOutputCol("features")

// Transform the data and save into a variable
val data = Assembler.transform(bank).toDF()

// Display the data array
data.show(10)

// Take the columns by the array
val cleanDf = data.select(col("x").as("label"),col("features"))

// Print the shcema and the lines
cleanDf.show(10)
cleanDf.printSchema()

// Creating a new model
val svc = new LinearSVC().setMaxIter(10).setRegParam(0.1)

// Define the data
val separador = cleanDf.randomSplit(Array(0.7, 0.3), seed = 9876L)
val train = separador(0)
val results = separador(1)

// Training the model
val model = svc.fit(train)

// Resaults
val resultados = model.transform(results)
val evaluador = new MulticlassClassificationEvaluator().setMetricName("accuracy")

// Printing the results of the evaluator and the rest data
println(s"Coeficientes: ${model.coefficients}")
println(s"Intercepciones: ${model.intercept}")
println(s"Grado de exactitud = ${evaluador.evaluate(resultados)}")