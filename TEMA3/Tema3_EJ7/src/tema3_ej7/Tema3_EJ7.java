package tema3_ej7;

import java.util.Scanner;

/**
 * @author Álvaro Zamorano Ortega
 * @author Sergio Sanz Sacristán
 * @author Miguel Ángel Losada Fernández
 * @author Gabriel López Cuenca
 */
public class Tema3_EJ7 {

    /**
     * @param args the command line arguments
     */
    private static int N;

    public static void main(String[] args) {
        System.out.print("Introducir potencia de 2 como longitud del "
                + "callejero: ");
        Scanner s = new Scanner(System.in);
        N = s.nextInt();

        System.out.println();

        int[][] callejero = generarCallejero();
        System.out.println("Imprimiendo callejero original");
        imprimirM(callejero);

        filasColumnas(callejero, 0, N - 1, 0, N - 1);
        System.out.println("Imprimiendo callejero transpuesto");
        imprimirM(callejero);
    }

    public static void cambioCuadrante(int[][] matriz, int filaIniA,
            int colIniA, int filaIniB, int colIniB, int dimen) {
        //Intercambia el 3º cuadrante por el 2º independientemente del
        //tamaño de la matriz
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                //aux es el 3º cuadrante
                int aux = matriz[filaIniA + i][colIniA + j];
                //se intercambia el 3º por el 2º
                matriz[filaIniA + i][colIniA + j]
                        = matriz[filaIniB + i][colIniB + j];
                //se coloca en el 2º el valor de auxiliar
                matriz[filaIniB + i][colIniB + j] = aux;
            }
        }
    }

    public static void filasColumnas(int[][] matriz, int filaInicio,
            int filaFin, int colInicio, int colFin) {
        //Cuando la matriz es 1x1, filaInicio = filaFin, no hay que
        //intercambiar
        if (filaInicio < filaFin) {
            int filaMedio = (filaInicio + filaFin) / 2;
            int colMedio = (colInicio + colFin) / 2;

            //Dividir la matriz en cuadrantes mediante los índices, a la mitad
            //tanto por columnas como filas. Tantas veces hasta que lleguemos
            //al caso base
            filasColumnas(matriz, filaInicio, filaMedio, colInicio, colMedio);
            filasColumnas(matriz, filaInicio, filaMedio, colMedio + 1, colFin);
            filasColumnas(matriz, filaMedio + 1, filaFin, colInicio, colMedio);
            filasColumnas(matriz, filaMedio + 1, filaFin, colMedio + 1, colFin);

            //Llamada a intercambiar con los valores para cambiar el 
            //3º cuadrante por el 2º
            cambioCuadrante(matriz, filaMedio + 1, colInicio, filaInicio,
                    colMedio + 1, filaFin - filaMedio);
        }
    }

    public static void imprimirM(int[][] vector) {
        //Método para imprimir la matriz
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(vector[i][j] + " ");
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] generarCallejero() {
        int[][] callejero = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) { //Diagonal principal de 0, ya que no hay calle
                    //a la misma calle
                    callejero[i][j] = 0;
                } else {
                    //El resto de posiciones se rellena aleaoriamente
                    int m = (int) Math.floor(Math.random() * 2);
                    callejero[i][j] = m;
                }
            }
        }

        return callejero;
    }

}
