package Transporte;

public class Bicicleta extends TransporteUrbano implements MeioEcologico {

    public Bicicleta(String nome, int capacidade) {
        super(nome, capacidade);
    }

    @Override
    public void mover() {
        System.out.println(nome + " está sendo pedalada pela ciclovia.");
    }

    @Override
    public boolean ehEcologico() {
        return true;
    }

    @Override
    public double calcularCustoViagem() {
        return 0.0;
    }
}