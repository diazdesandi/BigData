// Fibonacci Recursive

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