package Hospital;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite os sintomas separados por vírgula:");
        String entrada = scanner.nextLine();
        String[] sintomasInformados = entrada.toLowerCase().split(",");

        Diagnostico diagnosticoGripe = new DiagnosticoGripe();
        Diagnostico diagnosticoCovid = new DiagnosticoCovid();

        System.out.println("\n--- Diagnóstico Gripe ---");
        diagnosticoGripe.exibirSintomasComuns();
        System.out.println(diagnosticoGripe.avaliarPaciente(sintomasInformados));

        System.out.println("\n--- Diagnóstico Covid-19 ---");
        diagnosticoCovid.exibirSintomasComuns();
        System.out.println(diagnosticoCovid.avaliarPaciente(sintomasInformados));

        scanner.close();
    }
}