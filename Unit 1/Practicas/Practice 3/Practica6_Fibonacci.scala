// Fibonacci Divide and Conquer Version

// First we must to import the libraries
import scala.math.pow

// Here we define the function to ejecute the algorithm
def functionFibonacci(n :Int):Double={
    if(n<=0){
        // Here return 0  to show on console
        return 0
    }
    var indice = n-1; var aux1 =0.0; var aux2=1.0; var ab=(aux2,aux1); var cd=(aux1,aux2)
    // Ejecute just if the condition exist
    while(indice>0){
        if((indice%2)!=0){
            aux1=(cd._2*ab._2)+(cd._1*ab._1)
            aux2=(cd._2*(ab._2+ab._1)+cd._1*ab._2)
            ab=(aux1,aux2)
        }
        aux1=pow(cd._1,2)+pow(cd._2,2)
        aux2=(cd._2*(2*cd._1+cd._2))
        cd=(aux1,aux2)
        indice=indice/2
    }
    return (ab._1+ab._2)
}
// Here we launch the function
functionFibonacci(10)