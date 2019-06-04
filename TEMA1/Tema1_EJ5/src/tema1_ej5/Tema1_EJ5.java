package tema1_ej5;

import java.util.Scanner;

/**
 *
 * @author Miguel Ángel Losada
 * @author Sergio Sanz
 * @author Álvaro Zamorano
 * @author Gabriel López
 */
public class Tema1_EJ5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Introduzca un número: ");
        Scanner s = new Scanner (System.in);
        int n = s.nextInt();
        boolean primo = numeroPrimo(n);
        if (primo) {
            System.out.println(n + " es " + " primo\n");
        } else {
            System.out.println(n + " no es " + " primo\n");
        }
    }
    
    /**
     * Ejercicio 5
     * @param num
     * @return 
     */
    public static boolean numeroPrimo(int num) {
        boolean primo = true; 
        int i=2;
        //Mientras el numero no sea divisible por i, e i sea menor que el 
        //numero se hace el bucle
        while ((primo) && (i<num)) {
            if (num%i==0){
                //Si la división por i es exacta, el numero no es primo
                primo = false;
            }
            //La i avanza hasta llegar al número
            i++;
        }
        
        //Devolver si el número es primo o no
        return primo;
    }
}
