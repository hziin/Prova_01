import java.util.Locale;
import java.util.Scanner;

public class Prova_01_Improved {
    private static final int NUM_NOTAS = 8;
    private static final int NOTAS_POR_BIMESTRE = 2;

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("pt-BR")); // optional: ensures Portuguese locale formatting if needed
        Scanner input = new Scanner(System.in);

        System.out.println("=== Sistema Escolar ===");
        double[] notas = new double[NUM_NOTAS];

        // Entrada das notas com validação (ex.: 0.0 a 10.0) e tolerância para vírgula decimal
        for (int i = 0; i < NUM_NOTAS; i++) {
            notas[i] = readDoubleInRange(input, "Digite a nota " + (i + 1) + ": ", 0.0, 10.0);
        }

        // Calcula bimestres dinamicamente (cada bimestre = média de 2 notas)
        int numBimestres = NUM_NOTAS / NOTAS_POR_BIMESTRE;
        double[] bimestres = new double[numBimestres];
        for (int b = 0; b < numBimestres; b++) {
            int idx = b * NOTAS_POR_BIMESTRE;
            bimestres[b] = (notas[idx] + notas[idx + 1]) / 2.0;
        }

        // Calcula semestres (cada semestre = média de 2 bimestres)
        double semestre1 = (bimestres[0] + bimestres[1]) / 2.0;
        double semestre2 = (bimestres[2] + bimestres[3]) / 2.0;

        // Média final
        double mediaFinal = (semestre1 + semestre2) / 2.0;

        // Saída formatada
        System.out.println("\n=== Resultado ===");
        System.out.printf("1º Bimestre: %.1f%n", bimestres[0]);
        System.out.printf("2º Bimestre: %.1f%n", bimestres[1]);
        System.out.printf("1º Semestre: %.1f%n", semestre1);
        System.out.println("-------------------------");
        System.out.printf("3º Bimestre: %.1f%n", bimestres[2]);
        System.out.printf("4º Bimestre: %.1f%n", bimestres[3]);
        System.out.printf("2º Semestre: %.1f%n", semestre2);
        System.out.println("-------------------------");
        System.out.printf("Média Final: %.1f%n", mediaFinal);

        input.close();
    }

    // Lê um número double válido no intervalo [min, max], aceita vírgula como separador decimal
    private static double readDoubleInRange(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();

            // aceitar vírgula como separador decimal (ex.: "7,5")
            line = line.replace(',', '.');

            try {
                double value = Double.parseDouble(line);
                if (value < min || value > max) {
                    System.out.printf("Valor fora do intervalo (%.1f - %.1f). Tente novamente.%n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número (ex.: 8.5 ou 8,5). Tente novamente.");
            }
        }
    }
}