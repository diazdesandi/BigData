// Import & Start Spark session
import org.apache.spark.sql.SparkSession;
val spark = SparkSession.builder().getOrCreate();

// Here we asign the dataset into a variable to manage
// and load the csv netflix
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv");

// Here we access to Columns and discover names
// and to know what colimns have the csv
dataset.columns;

// Here we print the schema to get the information
dataset.printSchema();

//Print just the First 5 columns
dataset.head(5);

// Learn about the dataset & here we show the database on table with
// count,meanmin,max and more data
dataset.describe().show();

// New data frame created, HV column added, High and Volume columns relantionship
val secondDataset = dataset.withColumn("Hv Ratio", dataset("High") + dataset("Volume"));

// On this line we show the new database
secondDataset.show();

// Here we show the top column on "Close"
secondDataset.select(mean("Close")).show();

// That means the average close to the dataset, we selected from the second dataset
// and sometimes they can have time to can comparate between data frames or data

//Here we show the minimun to the column volume and the most hight on the same table
secondDataset.select(min("Volume"),max("Volume")).show();

// Here we find the inferior number on the Second Dataset
val inferior = secondDataset.filter($"Close" < 600).count();

// here we now the porcentaje on the time on hight
val time = secondDataset.filter($"High" > 500).count(); val porcentajeTime= time * .100;

// Here we find the pearson correelation on the secon dataset
secondDataset.select(corr("High", "Volume").alias("Correlacion de Pearson")).show();

// We can show the most high by year with the next line to find out
secondDataset.groupBy(year(secondDataset("Date")).alias("Year")).max("High").sort(asc("Year")).show();

// We can show the average by month of the year using and agrouping before to can get the data organizated on a table
secondDataset.groupBy(month(secondDataset("Date")).alias("Month")).avg("Close").sort(asc("Month")).show();