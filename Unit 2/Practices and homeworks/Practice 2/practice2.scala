// Here we import the all elements
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

val spark = SparkSession.builder().getOrCreate();
// We need to import the csv to can manage
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("Clean-Ecommerce.csv");
data.printSchema;
data.head(1);

val colnames = data.columns;
val firstrow = data.head(1)(0);
println("Example data row");
// We need a loop to walk all elements
for(ind <- Range(1, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
};

val df = data.select(data("Yearly Amount Spent").as("label1"), $"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Membership", $"Yearly Amount Spent");
val assembler = new VectorAssembler().setInputCols(Array("Avg Session Length", "Time on App","Time on Website","Length of Membership","Yearly Amount Spent")).setOutputCol("features");
val output = assembler.transform(df).select($"label", $"features")output.show();
// Here we display the linear regression
val lr = new LinearRegression();
// And find or create the model
val lrModel = lr.fit(output);
val trainingSummary = lrModel.summary;

trainingSummary.resuduals.show();
trainingSummary.predictions.show();
// Here we can desploay the "varianza"
trainingSummary.r2;
// Here we find the error
trainingSummary.rootMeanSquaredError;