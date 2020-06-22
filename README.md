# Proyecto 2: Javaboard
Por Pedro Yáñez - 201873049-1
## Peg Solitaire
Se desarrolló el juego de mesa Peg Solitaire, con el tablero inglés.
Todos los archivos están en la carpeta pegsolitaire dentro del directorio src, se explicará lo que se hizo a continuación:
### PegSolitaire.java
Extiende a la clase abstracta Game.
Es la clase donde se encuentra la estructura el juego, las posiciones iniciales de las piezas y el constructor que las pone en su posición respectiva, en el constructor también se define el tamaño del tablero.

**Override de toString:** Se hace override de toString para poder dibujar el tablero correctamente.
-Tablero original:
![Tablero inglés de pegsolitaire](https://mathworld.wolfram.com/images/eps-gif/PegSolitaire_1000.gif) 
-Tablero en la command line

![Tablero inglés de pegsolitaire, en command line](https://i.imgur.com/y3xEOGW.png)
**Override a CloneGame**: Se debió hacer override a este método para poder hacer una copia de un estado del tipo pegsolitaire.
**Override a isInside**: Se debió hacer override a este método  pues la original solo funcionaba con tableros cuadrados.
**Override a currentWinner**: Era necesario hacer override a este método para poder establecer las reglas de victoria y derrota.
**Nuevo método greetings**: Un pequeño extra para imprimir un saludos con cómo jugar.
### PegPiece.java
Extiende a la clase abstracta Piece.
Es la clase que contiene los atributos y métodos de una pieza de pegsolitaire.
**Override a getMovements**: Fue la parte que más tiempo tomo desarrollar, obtiene los movimientos posibles para cualquier pieza en el tablero y también elimina las piezas a las que se "salta" al moverse.
**Override a clonePiece**: Se debió hacer override a este método para poder clonar una pieza específica.
**Override a asciiRepresentation()**: Se debió hacer override a este método para designar un símbolo representativo de las piezas.
### Play.java
Clase principal donde se dan las instrucciones para ejecutar el juego, imprime el saludo con greetings(). Llama al ejecutor (Executor.java) con dos jugadores  y un estado del juego pegsolitaire.
Se usan dos jugadores aunque sea un juego en solitario, pues si se pierde el juego (no quedan movimientos pero queda más de una pieza en el tablero) se debe hacer que gane el jugador 1 (que nunca juega realmente).
