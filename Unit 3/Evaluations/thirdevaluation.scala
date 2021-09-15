// 1. Import Spark Session
import org.apache.spark.sql.SparkSession

// 2. Code to minimize errors.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// 3. Spark Session instance
val spark = SparkSession.builder().getOrCreate()

// 4. Import KMeans
import org.apache.spark.ml.clustering.KMeans

// 5. Load Wholesale Customers data
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("Wholesale customers data.csv")
// val dataset = spark.read.option("header","true").option("inferSchema","true").csv("Wholesale customers data.csv")

// 6. Select columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and name it feature_data
val  feature_data  = dataset.select("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")

// 7. Import Vector Assembler and Vector
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// 8. New Vector Assembler created for feature columns without labels.
val assembler = new VectorAssembler().setInputCols(Array("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")).setOutputCol("features")

//9. Using assembler object to transform
val  features = assembler.transform(feature_data)
features.show
//10. Create a model with K=3
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(features)

//11. Evaluation with the model wssse.
val WSSSE = model.computeCost(features)
println(s"Within Set Sum of Squared Errors = $WSSSE")

println("Centros Cluster: ")
model.clusterCenters.foreach(println)