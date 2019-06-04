package tema2_ej2;

import java.util.Arrays;

/**
 * @author Álvaro Zamorano Ortega
 * @author Sergio Sanz Sacristán
 * @author Miguel Ángel Losada Fernández
 * @author Gabriel López Cuenca
 */
public class Tema2_EJ2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] numeroPeticion = {0, 1, 2, 3, 4};
        int[] peticiones = {3, 2, 1, 2, 1};
        int[] longitudes = {2, 4, 3, 2, 5};
        int[] orden = ordenOptimo(longitudes, peticiones);

        System.out.println("Numero de peticion:");
        imprimirArray(numeroPeticion);

        System.out.println("Peticiones:");
        imprimirArray(peticiones);

        System.out.println("Longitudes:");
        imprimirArray(longitudes);

        System.out.println();
        System.out.print("Orden de ejecucion optimo para las peticiones: ");
        imprimirArray(orden);

        int tiempo = tiempoCarga(peticiones, longitudes, orden);
        System.out.println("Tiempo de carga siguiendo este orden: " + tiempo);
    }

    //Método para conseguir el orden de ejecución de las peticiones con menor
    //tiempo de carga total
    public static int[] ordenOptimo(int[] longitudes, int[] peticiones) {
        int n = longitudes.length;
        int posicion;
        float temporal;

        int[] orden = new int[n]; //Array para indicar el orden de las peticiones a seguir

        float[] divisiones = new float[n]; //Array de float para almacenar las divisiones
        //de peticion/longitud
        for (int i = 0; i < n; i++) {
            //Calcular divisiones y guardarlas
            divisiones[i] = (float) peticiones[i] / longitudes[i];
        }

        //Copiamos el array para no cambiar el original al ordenarlo
        //y poder comparar las posiciones finales con iniciales
        float[] divisiones_ordenado = Arrays.copyOf(divisiones, n);

        //Usamos método de ordenación por selección directa
        for (int j = 0; j < n; j++) {
            //Buscar posición del mayor objeto en la zona del array aún por
            //ordenar
            posicion = posicion_mayor(divisiones, j, n - 1);

            //Proceso para datos en las posiciones
            temporal = divisiones_ordenado[posicion];
            divisiones_ordenado[posicion] = divisiones_ordenado[j];
            divisiones_ordenado[j] = temporal;
        }

        //Comparar posiciones finales con iniciales para conocer el orden que
        //deben seguir las peticiones
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (divisiones_ordenado[i] == divisiones[k]) {
                    orden[i] = k;
                }
            }
        }

        //Devolver el orden de las peticiones
        return orden;
    }

    //Método complementario para obtener la posición del mayor dato dentro de 
    //la zona no ordenada del array, y así poder ordenarlo
    public static int posicion_mayor(float[] vector, int pas, int fin) {
        int pos = pas;
        float mayor = vector[pas];
        for (int i = pas + 1; i < fin + 1; i++) {
            if (vector[i] > mayor) {
                mayor = vector[i];
                pos = i;
            }
        }
        return pos;
    }

    //Método para calcular el tiempo de carga de las peticiones en el orden óptimo
    public static int tiempoCarga(int[] peticiones, int[] longitudes, int[] orden) {
        int sumatotal = 0;
        int anteriores = 0;

        for (int i = 0; i < peticiones.length; i++) {
            //Sumatorio desde la longitud de la primera petición hasta la petición actual
            anteriores += longitudes[orden[i]];
            //Se multiplica el sumatorio anterior por el valor de la petición actual
            sumatotal += anteriores * peticiones[orden[i]];
        }

        return sumatotal;
    }

    //Método para imprimir vectores
    public static void imprimirArray(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i] + " ");
        }
        System.out.println();
    }
}
