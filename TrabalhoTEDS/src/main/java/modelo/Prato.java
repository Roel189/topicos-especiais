package modelo;

public class Prato extends Produto {

    private long codigo_prato;
    private String nome;
    private String descricao;
    private double preco;

    public long getCodigo_prato() {
        return codigo_prato;
    }

    public void setCodigo_prato(long codigo_prato) {
        this.codigo_prato = codigo_prato;
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
