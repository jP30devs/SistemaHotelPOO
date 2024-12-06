public class Quarto extends Hospedagem implements Reservavel {

    private boolean reservado;

    public Quarto(int numero, int capacidade) {
        super(numero, capacidade);
        this.reservado = false;
    }

    @Override
    public void reservar(String idHospede) throws CapacidadeExcedidaException {
        if (reservado) {
            throw new CapacidadeExcedidaException("Erro: O quarto " + getNumero() + " já está reservado!");
        }
        reservado = true;
        System.out.println("Quarto reservado para o hóspede " + idHospede);
    }

    public int getNumero() {
        return super.getNumero();
    }

    public int getCapacidade() {
        return super.getCapacidade();
    }

    @Override
    public String toString() {
        return super.toString() + ", reservado=" + reservado;
    }

    public void reservar(String idHospede, String data) throws CapacidadeExcedidaException {
        if (reservado) {
            throw new CapacidadeExcedidaException("Erro: O quarto " + getNumero() + " já está reservado!");
        }
        reservado = true;
        System.out.println("Quarto " + getNumero() + " reservado para o hóspede " + idHospede + " na data " + data);
    }

}
