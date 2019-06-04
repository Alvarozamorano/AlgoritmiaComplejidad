package tema3_ej4;

import java.util.ArrayList;

/**
 * @author Álvaro Zamorano Ortega
 * @author Sergio Sanz Sacristán
 * @author Miguel Ángel Losada Fernández
 * @author Gabriel López Cuenca
 */
public class Tema3_EJ4 {

    public static ArrayList<Integer> ordenado = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> corchos = new ArrayList<>();
        ArrayList<Integer> botellas = new ArrayList<>();

        //Añadir los mismos datos a los arrays
        corchos.add(5);
        corchos.add(4);
        corchos.add(3);
        corchos.add(1);
        botellas.add(3);
        botellas.add(4);
        botellas.add(1);
        botellas.add(5);

        corchos = ordenarCorchos(corchos, botellas, 0);
        System.out.print("Corchos: ");
        System.out.println(corchos);

        botellas = ordenarBotellas(botellas, corchos, 0);
        System.out.print("Botellas: ");
        System.out.println(botellas);

    }

    public static ArrayList<Integer> ordenarCorchos(ArrayList<Integer> corchos,ArrayList<Integer> botellas, int indice) {
        int pivote, primero;
        ArrayList<Integer> auxiliar = new ArrayList<>();

        //Si el pivote es la ultima botella (caso base)
        if (indice == botellas.size() - 1) {
            pivote = botellas.get(indice);
            auxiliar.add(pivote);
            primero = 0;
            for (int i = 0; i < botellas.size(); i++) {
                //Si el corcho es menor que la botella pivote
                if (corchos.get(i) < pivote) {
                    //Se añaden a aux desde el principio
                    auxiliar.add(primero, corchos.get(i));
                    primero++;

                    //Si el corcho es mayor que la botella pivote
                } else if (corchos.get(i) > pivote) {
                    //Se añaden a aux desde el final
                    auxiliar.add(corchos.get(i));
                }
            }
            return auxiliar;
        } else {
            pivote = botellas.get(indice);
            auxiliar.add(pivote);
            primero = 0;
            for (int i = 0; i < botellas.size(); i++) {
                //Si el corcho es menor que la botella pivote
                if (corchos.get(i) < pivote) {
                    //Se añaden a aux desde el principio
                    auxiliar.add(primero, corchos.get(i));
                    primero++;
                    //Si el corcho es mayor que la botella pivote
                } else if (corchos.get(i) > pivote) {
                    //Se añaden a aux desde el final
                    auxiliar.add(corchos.get(i));
                }
            }

            //funcion recursiva para el pivote siguiente
            return ordenarCorchos(auxiliar, botellas, indice + 1);
        }
    }

    public static ArrayList<Integer> ordenarBotellas(ArrayList<Integer> botellas, ArrayList<Integer> corchos, int indice) {
        int pivote, primero;
        ArrayList<Integer> auxiliar = new ArrayList<>();

        //Si el pivote es el ultimo corcho (caso base)
        if (indice == corchos.size() - 1) {
            pivote = corchos.get(indice);
            auxiliar.add(pivote);
            primero = 0;
            for (int i = 0; i < corchos.size(); i++) {
                //Si la botella es menor que el corcho pivote
                if (botellas.get(i) < pivote) {
                    //Se añaden a aux desde el principio
                    auxiliar.add(primero, botellas.get(i));
                    primero++;
                    //Si la botella es mayor que el corcho pivote
                } else if (botellas.get(i) > pivote) {
                    //Se añaden a aux desde el final
                    auxiliar.add(botellas.get(i));
                }
            }
            return auxiliar;

        } else {
            pivote = corchos.get(indice);
            auxiliar.add(pivote);
            primero = 0;
            for (int i = 0; i < corchos.size(); i++) {
                //Si la botella es menor que el corcho pivote
                if (botellas.get(i) < pivote) {
                    //Se añaden a aux desde el principio
                    auxiliar.add(primero, botellas.get(i));
                    primero++;
                    //Si la botella es mayor que el corcho pivote
                } else if (botellas.get(i) > pivote) {
                    //Se añaden a aux desde el final
                    auxiliar.add(botellas.get(i));
                }
            }

            //funcion recursiva para el pivote siguiente
            return ordenarBotellas(auxiliar, corchos, indice + 1);
        }
    }
}
