package tema4_ej3;

/**
 * @author Álvaro Zamorano Ortega
 * @author Sergio Sanz Sacristán
 * @author Miguel Ángel Losada Fernández
 * @author Gabriel López Cuenca
 */
public class TEMA4_EJ3 {

    //Atributos para indicar las monedas que tenemos
    //y la cantidad de cada una de ellas
    private static final int[] monedas = {1, 3, 5};
    private static final int[] cantidad = {13, 2, 1};

    public static void main(String[] args) {
        int devolver = 11;

        int mon = cambio(devolver);

        if (mon != 0) {
            System.out.println("Cantidad monedas necesarias para devolver "
                    + devolver + ": " + mon);
        } else {
            System.out.println(devolver + " no se puede devolver con las"
                    + " monedas disponibles");
        }
    }

    /**
     * Método para rellenar la matriz de cambios
     *
     * @param precio
     * @return
     */
    private static int cambio(int precio) {

        int[][] matrizCambio = new int[monedas.length + 1][precio + 1];

        //Inicializar primeras filas y columnas
        for (int i = 0; i < monedas.length; i++) {
            matrizCambio[i][0] = 0;
        }

        for (int j = 1; j <= precio; j++) {
            matrizCambio[0][j] = 99;
        }

        //Algoritmo para rellenar la matriz
        for (int i = 1; i <= monedas.length; i++) {
            for (int j = 1; j <= precio; j++) {
                //El minimo en cada paso comienza siendo la casila superior a la
                //que estamos mirando (en caso de no coger la nueva moneda)
                int minimo = matrizCambio[i - 1][j];
                if (j < monedas[i - 1]) {
                    //Si la columna es menor que el valor de la nueva moneda,
                    //nos quedamos con la superior
                    matrizCambio[i][j] = minimo;
                } else {
                    if (hayMoneda(monedas[i - 1], cantidad[i - 1], j)) {
                        //Si hay suficientes monedas del nuevo valor para
                        //devolver el cambio
                        minimo = Math.min(minimo, matrizCambio[i][j
                                - monedas[i - 1]] + 1);
                        //Minimo entre la casilla superior y la casilla de la   
                        //misma columna haciendo hueco a la nueva moneda y le
                        //sumamos 1 (la estamos usando)
                    } else {
                        //Si no hay monedas suficientes para devolver ese
                        //cierto valor
                        minimo = Math.min(minimo, matrizCambio[i - 1][j
                                - (monedas[i - 1] * cantidad[i - 1])]
                                + cantidad[i - 1]);
                        //Minimo entre la casilla superior y de la fila anterior
                        //la columna correspondiente a quitar la cantidad que 
                        //podemos devolver usando las monedas disponibles más 
                        //ese número de monedas que usamos
                    }
                    matrizCambio[i][j] = minimo;
                }
                //Reestablecer el mínimo
                minimo = 0;
            }
        }

        imprimirMatriz(monedas.length, precio, matrizCambio);

        //Si la cantidad de monedas es distinta de 99 (infinito), se puede 
        //devolver esa cantidad con las monedas que disponemos
        int cant = 0;
        if (matrizCambio[monedas.length][precio] != 99) {
            cant = matrizCambio[monedas.length][precio];
        }
        return cant;
    }

    /**
     * Método para imprimir la matriz
     *
     * @param m
     * @param c
     * @param matriz
     */
    private static void imprimirMatriz(int m, int c, int[][] matriz) {
        System.out.println("**** MATRIZ DE CAMBIOS ****");
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= c; j++) {
                System.out.print(matriz[i][j]);
                if (j < c) {
                    System.out.print(", ");
                }
            }
            System.out.println("\n");
        }
    }

    /**
     * Método para comprobar si hay monedas suficientes de una cierta moneda
     * para devolver una cierta cantidad
     *
     * @param valor
     * @param cantidad
     * @param precio
     * @return
     */
    private static boolean hayMoneda(int valor, int cantidad, int precio) {
        return ((valor * cantidad) >= precio);
    }
}
