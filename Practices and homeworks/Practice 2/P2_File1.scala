// List with elements "rojo", "blanco", "negro"
var lista = collection.mutable.MutableList("rojo","blanco","negro")      

// Add "list" "green", "yellow", "blue", "orange", "pearl" to list.
lista += ("verde","amarillo", "azul", "naranja", "perla")

// Bring green, yellow and blue items
lista(3)
lista(4)
lista(5)
