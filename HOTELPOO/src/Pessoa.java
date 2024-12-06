public abstract class Pessoa {

    protected String nome;
    protected String cpf;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Pessoa [nome = " + nome + ", cpf = " + cpf + "]";
        }
    }



