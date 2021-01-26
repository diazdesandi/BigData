# 1st Evaluation
Import & Start Spark session
```scala
import org.apache.spark.sql.SparkSession;
val spark = SparkSession.builder().getOrCreate();
```
Datased assigned to a variable, to manage and load the file Netlfix_2011_2016.csv
```scala
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv");
```
We access the columns and discover their names
```scala
dataset.columns;
```
We print the scheme to have more information.
```scala
dataset.printSchema();
```
We print the first 5 columns.
```scala
dataset.head(5);
```
Describe function used to know more about the dataset.
```scala
dataset.describe().show();
```
A new data frame is created as well as a new column called "HV", which represents the relationship between High and Volume columns.
```scala
val secondDataset = dataset.withColumn("Hv Ratio", dataset("High") + dataset("Volume"));
```
In this line we know the new data set
```scala
secondDataset.show();
```
In this line we give the instruction to know the highest vale of Close column.
```scala
secondDataset.select(mean("Close")).show();
```
Column Close means the average close to the dataset, we select from the second dataset and it may some take time to compare between data frames or data.

We show the minimum and maximum value of the Volume column
```scala
secondDataset.select(min("Volume"),max("Volume")).show();
```
This instruction show lower values than 600 within the Close column.
```scala
val inferior = secondDataset.filter($"Close" < 600).count();
```
In this instruction we seek to know what percentage of time the High column was greater than 500.
```scala
val time = secondDataset.filter($"High" > 500).count(); val porcentajeTime= time * .100;
```
Here we look for the Pearson Correlation between the High and Volume columns.
```scala
secondDataset.select(corr("High", "Volume").alias("Correlacion de Pearson")).show();
```
We look for the maximum value of the High column per year, from 2011 to 2016.
```scala
secondDataset.groupBy(year(secondDataset("Date")).alias("Year")).max("High").sort(asc("Year")).show();
```
We show the monthly average of the Close column using groupBy function before to get the data organized in a table
```scala
secondDataset.groupBy(month(secondDataset("Date")).alias("Month")).avg("Close").sort(asc("Month")).show();
```
