# Practice 2 - Linear regression

> Here we import the all elements
```scala
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```
```scala
val spark = SparkSession.builder().getOrCreate();
```
> We need to import the csv to can manage
```scala
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("Clean-Ecommerce.csv");
data.printSchema;
data.head(1);
```

> Here we set the colnames and the rows
```scala
val colnames = data.columns;
val firstrow = data.head(1)(0);
println("Example data row");
```
> We need a loop to walk all elements
```scala
for(ind <- Range(1, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
};
```
```scala
val df = data.select(data("Yearly Amount Spent").as("label1"), $"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Membership", $"Yearly Amount Spent");
val assembler = new VectorAssembler().setInputCols(Array("Avg Session Length", "Time on App","Time on Website","Length of Membership","Yearly Amount Spent")).setOutputCol("features");
val output = assembler.transform(df).select($"label", $"features")output.show();
```
> Here we display the linear regression
```scala
val lr = new LinearRegression();
```
> And find or create the model
```scala
val lrModel = lr.fit(output);
val trainingSummary = lrModel.summary;
```
> To display the results of the training we need the next line
```scala
trainingSummary.resuduals.show();
trainingSummary.predictions.show();
```
> Here we can desploay the "varianza"
```scala
trainingSummary.r2;
```
> Here we find the error
```scala
trainingSummary.rootMeanSquaredError;
```