package Transporte;

public class Onibus extends TransporteUrbano{
    public Onibus(String nome, int capacidade){
        super(nome, capacidade);
    }

    @Override
    public void mover(){
        System.out.println(nome + " está se movendo pelas ruas.");
    }

    @Override
    public double calcularCustoViagem() {
        return 4.50;
    }
}