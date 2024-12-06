public abstract class Hospedagem {

    protected int numero;
    protected int capacidade;

    public Hospedagem(int numero, int capacidade) {
        this.numero = numero;
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return "Hospedagem [Quarto = " + numero + ", Capacidade = " + capacidade + "]";
    }

    protected int getNumero() {
        return numero;
    }

    protected int getCapacidade() {
        return capacidade;
    }
}
