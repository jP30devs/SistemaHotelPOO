public class Hospede extends Pessoa implements IdentificavelReserva {

    private String id;

    public Hospede(String nome, String cpf, String id) {
        super(nome, cpf);
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return super.toString() + ", id = " + id;
    }
}