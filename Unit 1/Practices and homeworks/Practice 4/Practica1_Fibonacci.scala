// Fibonacci Descending recursive version

/* Here we declarae each variable
 and we asign a value */

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
