package Transporte;

public class Metro extends TransporteUrbano{
    public Metro(String nome, int capacidade){
        super(nome, capacidade);
    }

    @Override
    public void mover(){
        System.out.println(nome + " está se movimentando pelos trilhos.");
    }

    @Override
    public double calcularCustoViagem() {
        return 5.00;
    }
}