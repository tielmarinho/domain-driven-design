package Transporte;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<TransporteUrbano> transportes = new ArrayList<>();
        transportes.add(new Onibus("Ônibus 2654", 50));
        transportes.add(new Metro("Metrô Linha Azul", 200));
        transportes.add(new Bicicleta("Bicicleta Verde", 1));
        transportes.add(new PatineteEletrico("Patinete Rápido", 1));

        for (TransporteUrbano t : transportes) {
            t.exibirInfo();
            t.mover();
            System.out.println("Custo da viagem: R$ " + t.calcularCustoViagem());

            if (t instanceof MeioEcologico) {
                MeioEcologico m = (MeioEcologico) t;
                if (m.ehEcologico()) {
                    System.out.println("Este transporte é ecológico!");
                }
            }
            System.out.println("-----");
        }
    }
}