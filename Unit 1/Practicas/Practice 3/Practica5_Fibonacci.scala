// Fibonacci vector iterative

def functionFibonacci(number :Int):Double={
    // We start a condition to know if the number is more then 2
    if(number<2){return number
    }else{
        var vector = new  Array[Double](number+1)
        // Here we add the number to the vector
        vector(0) = 0 ; vector(1) = 1
        // After this we start the loop 
        for (k<- Range(2,number+1)){
            vector(k)=vector(k-1)+vector(k-2)
        }
        // A the end we return the value to show on console
        return vector(number)
    } 
}
// Here we call the function inicializing with a value: 20
functionFibonacci (15)