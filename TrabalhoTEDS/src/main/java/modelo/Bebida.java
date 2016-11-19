package modelo;

public class Bebida extends Produto {

    private long codigo_bebida;
    private String nome;
    private String descricao;
    private double preco;

    public long getCodigo_bebida() {
        return codigo_bebida;
    }

    public void setCodigo_bebida(long codigo_bebida) {
        this.codigo_bebida = codigo_bebida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
