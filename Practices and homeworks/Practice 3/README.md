# Practice 3
```scala
//Here we define a function to asign to the loop for
def listEvens(list:List[Int]): String ={
    // Here we start the loop using the local variable list
    for(n <- list){
        // Each loop we verify if we have a rest of the list number
        if(n%2==0){
            // If the loop is true we print into screen
            println(s"$n is even")
            // If does not works we print the next text
        }else{
            println(s"$n is odd")
        }
    }
    // Here we return a done for if we want to use it for an other function or operacion
    return "Done"
}

// We declare a list with the next line
val l = List(1,2,3,4,5,6,7,8)
// Here we declare the second list
val l2 = List(4,3,22,55,7,8)

// On this line we asign like parameter the numbers to put into this function
listEvens(l)
listEvens(l2)

//3 7 afortunado

// Here we define an other function to afortunado
def afortunado(list:List[Int]): Int={
    // Now we inicialice this variable with 0 to have a count
    var res=0
    // After this we inicialice the loop for the for each
    for(n <- list){
        // We compare if the contition is true and go ahead
        if(n==7){
            // Inside the if condition we do the oparation we need
            res = res + 14
            // in case this does not work we go directly to the else declaration
        }else{
            // here we do the operarion
            res = res + n
        }
    }
    // And we call back the rest
    return res
}
// We create value af and assign a list, after that we print it.
val af= List(1,7,7)
println(afortunado(af))

// We define another function, called balance
def balance(list:List[Int]): Boolean={
    var primera = 0
    var segunda = 0

    segunda = list.sum

    // For loop initialized
    for(i <- Range(0,list.length)){
        primera = primera + list(i)
        segunda = segunda - list(i)

        if(primera == segunda){
            return true
        }
    }
    return false 
}

// Values bl,bl2,bl3 created and with a list assigned to them.
val bl = List(3,2,1)
val bl2 = List(2,3,3,2)
val bl3 = List(10,30,90)

// We look for the balance of each value
balance(bl)
balance(bl2)
balance(bl3)

// We define palindromo function, so we can know if that word fulfills that requerimient.
def palindromo(palabra:String):Boolean ={
    return (palabra == palabra.reverse)
}

// Create values palabra,palabra,2 palabra 3 and we assign strings.
val palabra = "OSO"
val palabra2 = "ANNA"
val palabra3 = "JUAN"

// We use the funciton palindromo and we evaluate the values palabra,palabra2 and palabra3.
println(palindromo(palabra))
println(palindromo(palabra2))
println(palindromo(palabra3))

```
