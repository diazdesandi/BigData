// ON THIS PRACTRICE WE USED AN GOOGLE PLAY STORE DATASET
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

// With this command we could see the type of fields
dataset.describe();

// To apply an for loop with the data set we use the next line
for(row <- dataset.head(10)){
    // Here we print using the row, using this loop
    println(row)
};

// Could be selected two specific colimns with the next line
dataset.select("App","Rating").show();

// We cand agroup by using groupBy
dataset.groupBy("Rating").count().show();

// Here we take the number of time we hace in parenteces
dataset.take(4);

// We collect the dataset
dataset.collect;

// To show just one specific arrow but we could show more just using come
dataset.select("Rating").show();