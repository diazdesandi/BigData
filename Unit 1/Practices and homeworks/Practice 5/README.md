# Practice 5

On this practice we use a Google Play Dataset
```scala
// In the first step we import the dependencies neccesary
import org.apache.spark.sql.SparkSession;

// After this we place into a variable called spark
val spark = SparkSession.builder().getOrCreate();

// Here we import the dataset from the path
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("googleplaystore.csv");

// And show the dataset
dataset.printSchema();

// Here we show the database
dataset.show();

// Here we display the name of the columns
dataset.columns;

// On this line we display an specific arrow
dataset.select(avg("App")).show();

// Here we sum by app arrow
dataset.select(sum("App")).show();

// We describe the dataset with the next line 
dataset.describe().show();

// Here we could know the res if exist this 
dataset("App");

// Now we shoe 10 on head
dataset.head(10);
```
