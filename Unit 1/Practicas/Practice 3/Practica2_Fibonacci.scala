// Fibonacci Explicit formula

// Here we declarae each variable
// and we asign a value
var n=20
// After this we start the for loop
for(n <- Array.range(0,n+1)){
var fibonnaci =(Math.pow(((1+Math.sqrt(5))/2),n)-Math.pow(((1-Math.sqrt(5))/2),n))/Math.sqrt(5)
// Here we print each loop
println(fibonnaci)
}