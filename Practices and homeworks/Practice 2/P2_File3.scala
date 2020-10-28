// Mutable map with data "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
var map = collection.mutable.Map(("Jose", 20),("Luis", 24),("Ana", 23),("Susana", "27"))

// Print map keys
map.keys

// Add new value to map
map += ("Miguel"->23)
