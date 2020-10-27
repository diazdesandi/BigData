// Mutable map with data "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
var map = collection.mutable.Map(("Ivan", 20),("Brandon", 24),("Eduardo", 23),("Daniela", "27"));

// Here we print the map keys
map.keys;

// We could add the value choosing what we want
map += ("Manuel"->23);
