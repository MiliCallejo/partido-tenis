import java.util.Random;
import java.util.Scanner;

public class Tenis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" - PARTIDO DE TENIS -");

        System.out.print("Nombre del Torneo: ");
        String torneo = scanner.nextLine();

        System.out.print("Nombre tenista 1: ");
        String tenista1 = scanner.nextLine();

        System.out.print("Nombre tenista 2: ");
        String tenista2 = scanner.nextLine();

        boolean revancha = true;
        do {
            System.out.print("Cuantos sets (3 o 5): ");
            int sets = scanner.nextInt();

            int puntajeTenista1 = 0;
            int puntajeTenista2 = 0;

            for (int set = 1; set <= sets; set++) {
                System.out.println("\nSet Nro " + set + ":");
                System.out.println("Arranca el saque de " + obtenerSaqueInicial(tenista1, tenista2));

                while (true) {
                    int probabilidadGanador = new Random().nextInt(100) + 1;
                    String ganadorPunto;

                    if (probabilidadGanador <= 50) {
                        ganadorPunto = tenista1;
                        puntajeTenista1++;
                    } else {
                        ganadorPunto = tenista2;
                        puntajeTenista2++;
                    }

                    System.out.println(ganadorPunto + " gano este punto");
                    System.out.println("Resultado parcial: " + tenista1 + ": " + puntajeTenista1 + " - " + puntajeTenista2 + ": " + tenista2);

                    if ((puntajeTenista1 >= 6 && puntajeTenista1 - puntajeTenista2 >= 2) || (puntajeTenista2 >= 6 && puntajeTenista2 - puntajeTenista1 >= 2)) {
                        System.out.println("El " + set + " set se termino");

                        if (puntajeTenista1 > puntajeTenista2) {
                            System.out.println(tenista1 + " gano el set: " + set);
                        } else {
                            System.out.println(tenista2 + " gano el set:" + set);
                        }

                        puntajeTenista1 = 0;
                        puntajeTenista2 = 0;
                        break;
                    }
                }
            }

            System.out.println("\n- RESULTADOS FINALES - ");
            if (puntajeTenista1 > puntajeTenista2) {
                System.out.println(tenista1 + " gano la partida " + sets + " sets a " + (sets - 1) + " en el torneo " + torneo + ".");
            } else {
                System.out.println(tenista2 + " gano la partida " + sets + " sets a " + (sets - 1) + " en el torneo " + torneo + ".");
            }

            System.out.print("\nRevancha? (si/no): ");
            scanner.nextLine();
            String respuesta = scanner.nextLine().trim().toLowerCase();

            revancha = respuesta.equals("si");

        } while (revancha);

        scanner.close();
    }

    private static String obtenerSaqueInicial(String tenista1, String tenista2) {
        return new Random().nextBoolean() ? tenista1 : tenista2;
    }
}
