package tema2_ej6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Álvaro Zamorano Ortega
 * @author Sergio Sanz Sacristán
 * @author Miguel Ángel Losada Fernández
 * @author Gabriel López Cuenca
 */
public class Tema2_EJ6 {

    //Atributos de la clase
    private static int N;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Array para almacenar los valores de entrada introducidos o generados
        ArrayList<Integer> entrada = generarArray();

        System.out.println("Imprimiendo valores");
        System.out.println(entrada.toString() + "\n");
        
        //Mostrar coste total
        System.out.println("COSTE TOTAL: " + algoritmoShrek(entrada));
    }

    //Método para rellenar el array de entrada con los valores ya sea 
    //manual o aleatoriamente
    public static ArrayList<Integer> generarArray() {
        ArrayList<Integer> entrada = new ArrayList<>();

        System.out.println("Introducir valores");
        System.out.print("\tNumero de valores: ");
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        
        System.out.print("\tIntroducir 0(manual) o 1(aleatoria): ");
        Scanner s1 = new Scanner(System.in);
        int n = s1.nextInt();

        if (n == 0) {
            for (int i = 0; i < N; i++) {
                int k = i+1;
                System.out.print("Introduzca posicion " + "[" + k + "]: ");
                Scanner s2 = new Scanner(System.in);
                int m = s2.nextInt();
                entrada.add(m);
            }
        } else {
            for (int i = 0; i < N; i++) {
                //Introducimos valores aleatorios entre el 1 y el 30
                int m = (int) Math.floor(Math.random() * 29);
                entrada.add(m + 1);
            }
        }

        System.out.println();
        return entrada;
    }

    public static int posMenor(int[] array) {
        //Seleccionamos como valor minimo el primero del array
        int min = array[0];
        //Posicion del valor mínimo
        int pos = 0;
        //Recorremos el array comparando todos los elementos con el menor
        //temporal, seleccionando el minimo valor del array y su posición
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                pos = i;
            }
        }
        return pos;
    }

    public static int algoritmoShrek(ArrayList<Integer> entrada) {
        int coste = 0;
        ArrayList<Integer> salida = new ArrayList<>();

        //Ordenar array de entrada
        Collections.sort(entrada);

        do {
            //Array para almacenar sumas, se inicializa con 1000 para no tenerlo
            //en cuenta en la menor suma
            int[] sumas = {1000, 1000, 1000};
            
            //Diferentes tipos de sumas
            if (entrada.size() >= 2) {
                sumas[0] = entrada.get(0) + entrada.get(1);
            }
            if (entrada.size() >= 1 && salida.size() >= 1) {
                sumas[1] = entrada.get(0) + salida.get(0);
            }
            if (salida.size() >= 2) {
                sumas[2] = salida.get(0) + salida.get(1);
            }

            //Buscar la posición de la menor suma
            int pos = posMenor(sumas);
            //Añadimos la suma menor al coste total
            coste += sumas[pos];

            switch (pos) {
                case 0:
                    //La suma menor es con 2 valores de entrada
                    entrada.remove(0);
                    entrada.remove(0);
                    break;
                case 1:
                    //La suma menor es con 1 valor de entrada y otro de salida
                    entrada.remove(0);
                    salida.remove(0);
                    break;
                case 2:
                    //La suma menor es con 2 valores de entrada
                    salida.remove(0);
                    salida.remove(0);
                    break;
            }
            //Añadimos la suma al array de salida para tenerla en cuenta a la
            //hora de calcular sumas menores
            salida.add(sumas[pos]);
            
            //Lo hacemos mientras haya datos en entrada o el array de salida
            //tenga al menos un elemento
        } while (!entrada.isEmpty() || salida.size() > 1);

        //Devolver el coste total
        return coste;
    }
}
