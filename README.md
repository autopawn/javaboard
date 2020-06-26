# Project 2 - Javaboard
Java framework for Board Games (exercise).

#### Nombre: Ignacio Delgadillo Vera.
#### Rol: 2503020-6.

## Issue #13

:computer: Se modificó la función defaultEvaluationFunction() de la clase FoxAndHounds, la función 
toma la pieza fox y hace una búsqueda en amplitud de todos los posibles movimientos en favor de las 
rutas mas cortas para llegar al otro lado del tablero, considerando el flanqueo de los hounds y
destruyendo el arbol si un hounds esta en el camino; retorna eval en negativo para 
la evaluación.

:computer: Si  no hay un camino y los hounds bloquean toda ruta, se entrega un valor mas negativo en una unidad de los pasos hasta el hounds, ya que esto les faborece a los hounds.

