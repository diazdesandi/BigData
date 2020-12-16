# Third Evaluation
1. Import Spark Session
```scala
import org.apache.spark.sql.SparkSession
```
2. Code to minimize errors.
```scala
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```
3. Spark Session instance
```scala
val spark = SparkSession.builder().getOrCreate()
```
4. Import KMeans
```scala
import org.apache.spark.ml.clustering.KMeans
```
5. Load Wholesale Customers data
```scala
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("Wholesale customers data.csv")
```
6. Select columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and name it feature_data
```scala
val  feature_data  = dataset.select("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")
```
7. Import Vector Assembler and Vector
```scala
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
```
8. New Vector Assembler created for feature columns without labels.
```scala
val assembler = new VectorAssembler().setInputCols(Array("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")).setOutputCol("features")
```