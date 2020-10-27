// Fibonacci iterative with two variables

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