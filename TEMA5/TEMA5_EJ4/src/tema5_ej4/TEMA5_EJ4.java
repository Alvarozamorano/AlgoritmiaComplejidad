package tema5_ej4;

/**
 * @author Álvaro Zamorano Ortega
 * @author Sergio Sanz Sacristán
 * @author Miguel Ángel Losada Fernández
 * @author Gabriel López Cuenca
 */
public class TEMA5_EJ4 {

    //Atributos de la clase
    private static final int columnas = 8;
    private static final int filas = 8;
    private static int[][] tablero = new int[filas][columnas];
    private static int[][] movimientos = new int[2][8];

    public static void main(String[] args) {
        Inicializar();

        //Posición de partida del caballo
        int x = 0, y = 0;

        boolean sol = movCab(x, y, 1);

        if (sol) {
            System.out.println("El caballo puede recorrer todas las posiciones"
                    + " del tablero desde la posición (" + (x + 1) + ","
                    + (y + 1) + ")");
        } else {
            System.out.println("El caballo no puede recorrer todas las "
                    + "posiciones del tablero desde la posición ("
                    + (x + 1) + "," + (y + 1) + ")");
        }
    }

    /**
     * Método para inicializar el tablero y los movimientos que puede realizar
     * el caballo
     *
     */
    private static void Inicializar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = 0;
            }
        }

        //Movimientos posibles que puede hacer el caballo en forma de L
        movimientos[0][0] = 1;
        movimientos[0][1] = 2;
        movimientos[0][2] = 2;
        movimientos[0][3] = 1;
        movimientos[0][4] = -1;
        movimientos[0][5] = -2;
        movimientos[0][6] = -2;
        movimientos[0][7] = -1;
        movimientos[1][0] = 2;
        movimientos[1][1] = 1;
        movimientos[1][2] = -1;
        movimientos[1][3] = -2;
        movimientos[1][4] = -2;
        movimientos[1][5] = -1;
        movimientos[1][6] = 1;
        movimientos[1][7] = 2;
    }

    /**
     * Método para generar las llamadas recursivas en las que el caballo se
     * mueve por el tablero
     *
     * @param x
     * @param y
     * @param n
     * @return
     */
    private static boolean movCab(int x, int y, int n) {
        int ax, ay;

        //Indicar en que movimiento pasa el caballo por esa casilla, es decir,
        //se recorre
        tablero[x][y] = n;

        if (n == filas * columnas) {
            //El número de movimientos es igual al número de casillas del
            //tablero, habrá terminado
            imprimirMatriz(tablero);

            return true;
        }

        for (int i = 0; i < columnas; i++) {
            //Para cada uno de los posibles movimientos que puede hacer el
            //caballo
            ax = x + movimientos[0][i];
            ay = y + movimientos[1][i];
            if (ax >= 0 && ax < filas && ay >= 0 && ay < columnas) {
                //Si el movimiento se encuentra dentro del tablero
                if (tablero[ax][ay] == 0) {
                    //En caso de que la casilla no haya sido recorrida,
                    //se recorre y se intenta hacer un nuevo movimiento
                    //(se vuelve a empezar)
                    if (movCab(ax, ay, n + 1)) {
                        return true;
                    }
                }
            }
        }

        //Desmarcar la casilla que no sirve en esta rama para que se pueda
        //pasar por esta casilla en llamadas posteriores
        tablero[x][y] = 0;

        //Una vez llegados aquí, no se podrá continuar en la recursividad
        //o el caballo no habrá recorrido todas las posiciones sin tener 
        //más movimientos disponibles
        return false;
    }

    /**
     * Método para imprimir el tablero donde se muestra en que movimiento ha
     * pasado el caballo por cada casilla
     *
     * @param matriz
     */
    private static void imprimirMatriz(int[][] matriz) {
        System.out.println("**** MATRIZ DE MOVIMIENTOS ****");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j]);
                if (j < columnas - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("\n");
        }
    }
}
