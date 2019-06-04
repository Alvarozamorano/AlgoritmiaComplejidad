package tema5_ej6;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Álvaro Zamorano Ortega
 * @author Sergio Sanz Sacristán
 * @author Miguel Ángel Losada Fernández
 * @author Gabriel López Cuenca
 */
public class TEMA5_EJ6 {

    //Atributo para almacenar la matriz de transformaciones
    private static final char[][] matriz = {{'b', 'b', 'a', 'd'},
    {'c', 'a', 'd', 'a'},
    {'b', 'a', 'c', 'c'},
    {'d', 'c', 'd', 'b'}};

    //Atributo para almacenar las posibles letras que hay en la matriz
    private static ArrayList<String> letras;

    public static void main(String[] args) {
        String entrada = "acabada";
        String fin = "d";

        letras = letrasMatriz();

        if (reemplazar(entrada, matriz, fin)) {
            System.out.println("La palabra " + entrada.toUpperCase()
                    + " se puede sustituir por una " + fin);
        } else {
            System.out.println("La palabra " + entrada.toUpperCase()
                    + " no se puede sustituir por una " + fin);
        }
    }

    /**
     * Método para hacer las posibles transformaciones mediante llamadas
     * recursivas
     *
     * @param entrada
     * @param m
     * @param f
     * @return
     */
    private static boolean reemplazar(String entrada, char[][] m, String f) {
        String[] aux = new String[3];

        //Separar la cadena de entrada por letras
        String[] e = new String[entrada.length()];
        for (int i = 0; i < entrada.length(); i++) {
            e[i] = "" + entrada.charAt(i);
        }
        int len = e.length;

        if (len == 1) {
            //En caso de que la cadena sea de tamaño 1
            if (e[0].equals(f)) {
                //Si el único carácter que tiene es el final que queremos
                //obtener, se puede hacer la transformación
                return true;
            }
        } else {
            for (int i = 1; i < len; i++) {
                //En la primera posición del array se almacena el comienzo de
                //la palabra hasta i-1 (posición en la que se está haciendo
                //la transformación
                aux[0] = str(e, 0, i - 1);

                //En la segunda posición del array almacenamos la transformación
                if (i + 1 == len) {
                    aux[1] = pos(e[i], e[len - 1]);
                } else {
                    aux[1] = pos(e[i], e[i + 1]);
                }

                //En la tercera posición del array se almacena el resto de la 
                //palabra aún sin procesar
                aux[2] = str(e, i + 2, len);

                if (reemplazar(aux[0] + aux[1] + aux[2], m, f)) {
                    //Llamamos al método recursivamente con la nueva palabra
                    //conseguida tras la transformación
                    return true;
                }
            }
        }
        //Llegados a este punto, la transformación no se ha podido realizar
        return false;
    }

    /**
     * Método para obtener una subcadena de una cadena dada en un array que
     * contiene sus letras
     *
     * @param e
     * @param i
     * @param j
     * @return
     */
    private static String str(String[] e, int i, int j) {
        String dev = "";
        if (i < j) {
            for (int k = i; k < j; k++) {
                dev += e[k];
            }
        }
        return dev;
    }

    /**
     * Método para obtener la letra por la que se deben cambiar las 2 letras
     * correspondientes
     *
     * @param a
     * @param b
     * @return
     */
    private static String pos(String a, String b) {
        return ("" + matriz[letras.indexOf(a)][letras.indexOf(b)]);
    }

    /**
     * Método para buscar las diferentes letras que hay en la matriz de
     * transformaciones
     *
     * @return
     */
    private static ArrayList<String> letrasMatriz() {
        ArrayList<String> ls = new ArrayList<>();
        String l;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                l = "" + matriz[i][j];
                if (!ls.contains(l)) {
                    ls.add(l);
                }
            }
        }
        Collections.sort(ls);
        return ls;
    }
}
