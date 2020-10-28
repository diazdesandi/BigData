// List with elements "rojo", "blanco", "negro"
var lista = collection.mutable.MutableList("rojo","blanco","negro")      

// Add "list" "green", "yellow", "blue", "orange", "pearl" to list.
lista += ("verde","amarillo", "azul", "naranja", "perla")

// Bring green, yellow and blue items
lista(3)
lista(4)
lista(5)

// Array from 1-1000, 5-5 steps
var v = range(1,1000,5)

// Find unique elements from list (1,3,3,4,6,7,3,7)
var ls = List(1,3,3,4,6,7,3,7)
ls.toSet

// Mutable map with data "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
var map = collection.mutable.Map(("Jose", 20),("Luis", 24),("Ana", 23),("Susana", "27"))

// Print map keys
map.keys

// Add new value to map
map += ("Miguel"->23)