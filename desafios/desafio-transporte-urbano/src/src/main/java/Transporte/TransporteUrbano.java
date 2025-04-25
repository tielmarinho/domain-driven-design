package Transporte;

public abstract class TransporteUrbano {
    protected String nome;
    protected int capacidade;

    public TransporteUrbano(String nome, int capacidade){
        this.nome = nome;
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void exibirInfo(){
        System.out.println("Transporte: " + nome + ", Capacidade: " + capacidade);
    }

    public abstract void mover();

    public double calcularCustoViagem() {
        return 0.0;
    }
}