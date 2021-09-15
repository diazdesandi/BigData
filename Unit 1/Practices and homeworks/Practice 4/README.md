# Practice 4

Fibonacci Descending recursive version
```scala
// Here we declarae each variable and we asign a value

var f=0; var n=20; var t1=1; var t2=1;

// in the next cicle we do it to reach the condition
for(iteration <- Array.range(0,n+1)){
    t2=f
    f=t1+f
    t1=t2
    // here we print each cicle 
    println(t1)
    //end
}
```

Fibonacci Explicit formula
```scala
// Here we declarae each variable
// and we asign a value
var n=20
// After this we start the for loop
for(n <- Array.range(0,n+1)){
var fibonnaci =(Math.pow(((1+Math.sqrt(5))/2),n)-Math.pow(((1-Math.sqrt(5))/2),n))/Math.sqrt(5)
// Here we print each loop
println(fibonnaci)
}
```

Fibonacci Recursive
```scala
// Here we define the function to ejecute the algorithm
def functionFibonacci(number:Int):Int={
    if(number<2){
        // Here we return the number to display on console
        return number
    }else{
        // Here we return the solve to display on console
        return (functionFibonacci(number-1)+functionFibonacci(number-2))
    }
}
// Here we inicialize the function adding a 15 value to start
functionFibonacci(15)
```

Fibonacci iterative with two variables
```scala
// Here we define a function to ejecute the algorithm
def functionFibonacci(number:Int ):Int={
    var value1=0; var value2=1;
    for (iteration <- Range(0,number)){
        value2=value2+value1
        value1=value2-value1
    }
    // Here we return the value1 to show on console
    return value1
}
// Here inicialize the function adding a start value : 10
functionFibonacci(15)
```

Fibonacci vector iterative
```scala
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
```