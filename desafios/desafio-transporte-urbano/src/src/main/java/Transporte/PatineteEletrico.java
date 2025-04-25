package Transporte;

public class PatineteEletrico extends TransporteUrbano implements MeioEcologico {

    public PatineteEletrico(String nome, int capacidade) {
        super(nome, capacidade);
    }

    @Override
    public void mover() {
        System.out.println(nome + " está deslizando pela calçada.");
    }

    @Override
    public boolean ehEcologico() {
        return true;
    }

    @Override
    public double calcularCustoViagem() {
        return 2.00;
    }
}