package tema4_ej6;

/**
 * @author Álvaro Zamorano Ortega
 * @author Sergio Sanz Sacristán
 * @author Miguel Ángel Losada Fernández
 * @author Gabriel López Cuenca
 */
public class TEMA4_EJ6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] VC = {35, 10, 5, 50};
        int numPartidos = 5;
        System.out.println("JaviPotter apuesta 10€ en un torneo de "
                + numPartidos + " partidos con una probabilidad para su equipo"
                + " del " + VC[0] + "%" + ", finalmente obtiene un total "
                + "de " + JaviPotter(VC, numPartidos, 10) + "€");
    }

    /**
     * Método que devuelve las ganancias de JaviPotter si ganasen los grifos en
     * función del número de partidos y probabilidad de cada equipo
     *
     * @param VC valores de la calidad de cada equipo (probabilidad como entero)
     * @param n número de partidos
     * @param dineroApostado dinero que apuesta JaviPotter
     * @return
     */
    public static float JaviPotter(int[] VC, int n, int dineroApostado) {
        //Sin sumarle 1 al número de partidos no se completan los bucles
        n++;
        //Incializamos la matriz de la probablididad (a todos los partidos le 
        //faltan n partidos por ganar)
        float[][][][] P = new float[n][n][n][n];
        //Inicializamos el array de las probabilidades de cada equipo
        float[] probEquipos = new float[4];
        for (int i = 0; i < 4; i++) {
            //Obtenemos el porcentaje de la probabilidad para cada equipo
            probEquipos[i] = (float) VC[i] / 100;
        }

        //Apuesta por el primer equipo y si su probabilidad es 0 entonces no 
        //gana nada
        if (probEquipos[0] == 0)
        {
            return 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                for (int k = 1; k < n; k++) {
                    //Si el numero de partidos que le quedan por ganar a un 
                    //equipo es 0 significa que la probabilidad de ganar es 
                    //1 (ya ha ganado)
                    P[0][i][j][k] = 1; 
                    P[i][0][j][k] = 0;
                    P[i][j][0][k] = 0;
                    P[i][j][k][0] = 0;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                for (int k = 1; k < n; k++) {
                    for (int l = 1; l < n; l++) {
                        //Multiplicamos las probabilidades de cada equipo por 
                        //la probabilidad cuando gana un partido más
                        P[i][j][k][l] = probEquipos[0] * P[i - 1][j][k][l]
                                + probEquipos[1] * P[i][j - 1][k][l]
                                + probEquipos[2] * P[i][j][k - 1][l]
                                + probEquipos[3] * P[i][j][k][l - 1];
                    }
                }
            }
        }
        //Dinero entre la probabilidad final
        System.out.println("Division final: "+dineroApostado+"/"+P[n - 1][n - 1][n - 1][n - 1]);
        return dineroApostado / P[n - 1][n - 1][n - 1][n - 1];
    }
}
