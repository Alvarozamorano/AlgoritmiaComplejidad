package tema1_ej9;

import java.util.Scanner;

/**
 *
 * @author Miguel Ángel Losada
 * @author Sergio Sanz
 * @author Álvaro Zamorano
 * @author Gabriel López
 */

public class Tema1_EJ9 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        System.out.println("Introduzca un número:");
        Scanner s = new Scanner (System.in);
        int n = s.nextInt();
        int suma = sumatorio(n);
        System.out.println("La suma de 1 a " + n + " es: " + suma + "\n");
    }
    
    /**
     * Ejercicio 9
     * @param num
     * @return 
     */
    public static int sumatorio(int num){
        //Caso base: numero=1
        if(num==1){
            //Su suma es 1
            return 1;
        }else{
            //Suma del anterior + llamada recursiva del siguiente valor
            return num+sumatorio(num-1);
        }
    }
}
