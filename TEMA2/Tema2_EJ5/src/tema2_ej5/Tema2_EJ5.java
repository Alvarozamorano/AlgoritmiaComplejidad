package tema2_ej5;

import java.util.Scanner;

/**
 * @author Álvaro Zamorano Ortega
 * @author Sergio Sanz Sacristán
 * @author Miguel Ángel Losada Fernández
 * @author Gabriel López Cuenca
 */
public class Tema2_EJ5 {

    //Atributos de la clase
    private static int N;
    private static final int MAX_ENTERO = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //int[][] matrix = generarMatriz();
        N = 6;
        int[][] matrix = new int[][]{{0, 2, 5, 1, 100, 100},
        {100, 0, 3, 100, 100, 100},
        {100, 100, 0, 100, 100, 5},
        {100, 2, 3, 0, 1, 100},
        {100, 100, 1, 100, 0, 2},
        {100, 100, 100, 100, 100, 0}
        };
        imprimirM(matrix);
        dijkstra(matrix);
    }

    public static int[][] generarMatriz() {
        //Generamos la matriz dependiendo de si el usuario elige aleatoria
        // o introducirla manualmente
        System.out.println("Generar matriz");
        System.out.print("\tNumero de nodos: ");
        Scanner s1 = new Scanner(System.in);
        N = s1.nextInt();
        int[][] matriz = new int[N][N];

        System.out.print("\tIntroducir 0(manual) o 1(aleatoria): ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        if (n == 0) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print("Introduzca posicion " + "[" + i + "]"
                            + "[" + j + "]: ");
                    Scanner s2 = new Scanner(System.in);
                    int m = s2.nextInt();
                    matriz[i][j] = m;
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int a = (int) Math.floor(Math.random() * 10);
                    matriz[i][j] = a;
                }
                int d = (int) Math.floor(Math.random() * (N * 2));
                for (int k = 0; k < d; k++) {
                    int b = (int) Math.floor(Math.random() * (N - 1));
                    int c = (int) Math.floor(Math.random() * (N - 1));
                    matriz[b][c] = 100;
                }
            }
        }
        System.out.println();
        return matriz;
    }

    public static void imprimirM(int[][] vector) {
        //Método para imprimir la matriz
        System.out.println("Imprimiendo matriz");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(vector[i][j] + " ");
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void imprimirA(int[] vector, String s) {
        //Método para imprimir los diferentes array que se generan
        System.out.println("Hasta el nodo: ");
        for (int j = 0; j < N; j++) {
            System.out.print(j + 1);
            System.out.print("\t");
        }
        System.out.println();
        System.out.println(s + ": ");

        if (s.equals("Nodo anterior")) {
            for (int i = 0; i < vector.length; i++) {
                int n = vector[i];
                n++;
                System.out.print(n + " ");
                System.out.print("\t");
            }
            System.out.println("\n");
        } else {
            for (int i = 0; i < vector.length; i++) {
                System.out.print(vector[i] + " ");
                System.out.print("\t");
            }
            System.out.println("\n");
        }
    }

    public static void dijkstra(int[][] matriz) {
        int[] distancias = new int[N];
        int[] numAristas = new int[N];
        int[] anteriores = new int[N];
        int min = 0, imin = 0;
        int[] seleccionados = new int[N];

        for (int i = 0; i < N; i++) {  //inciaializamos las distancias, los seleccionados y si hay ariasta
            distancias[i] = matriz[0][i];
            seleccionados[i] = 0;
            if (distancias[i] < MAX_ENTERO) {
                numAristas[i] = 1;
            } else {
                //Si distancia=infinito, numero aristas=0
                numAristas[i] = 0;
            }
        }
        for (int i = 0; i < N; i++) {
            min = MAX_ENTERO;
            //En cada iteraccion calculo de la minima distancia temporal
            for (int j = 1; j < N; j++) {
                if (seleccionados[j] == 0) {     //si no ha sido seleccionado
                    if (min > distancias[j]) {
                        min = distancias[j];
                        imin = j;
                    }
                }
            }
            //El nodo con la distancia minima temporal ya encontro su distancia
            //minima
            seleccionados[imin] = 1;
            //Actualiza las distancias del siguiente vector
            for (int j = 0; j < N; j++) {
                //Si distancia minima del nodo j no encontrada
                if (seleccionados[j] == 0) {
                    //si la distancia actual del nodo j mayor que la suma entre 
                    //la distancia del nodo j y la minima distancia temporal
                    if (distancias[j] > distancias[imin] + matriz[imin][j]) {
                        //Se actualiza distancia
                        distancias[j] = distancias[imin] + matriz[imin][j];
                        //Indice anterior por el que pasa el camino mas corto
                        //temporal
                        anteriores[j] = imin;
                        //Se suma en 1 el numero de saltos para llegar al nodo
                        numAristas[j] = numAristas[imin] + 1;
                    }
                }
            }
        }

        imprimirA(distancias, "Distancias");
        imprimirA(numAristas, "Numero de aristas a recorrer");
        imprimirA(anteriores, "Nodo anterior");
        caminoFinal(numAristas, anteriores);
    }

    public static void caminoFinal(int[] aristas, int[] anteriores) {
        //Numero de aristas que recorremos para llegar al nodo final
        int n_a = aristas[aristas.length - 1];
        int[] camino = new int[n_a];
        int j = 0;
        System.out.println("--------------");
        System.out.println(anteriores.length-n_a);
        System.out.println(anteriores.length);
        System.out.println("--------------");
        for (int i = (anteriores.length - n_a); i < anteriores.length; i++) {
            camino[j] = anteriores[i];
            j++;
        }

        System.out.println("Para llegar al nodo " + N + " hay que pasar por "
                + "los nodos:");

        for (int i = 0; i < camino.length; i++) {
            int n = camino[i];
            n++;
            System.out.print(n + " ");
            System.out.print("\t");
        }
        System.out.println();

    }
}
