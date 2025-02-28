public class Funcionario extends Pessoa {

    private String cargo;

    public Funcionario(String nome, String cpf, String cargo) {
        super(nome, cpf);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return super.toString() + ", Cargo = " + cargo;
    }
}
