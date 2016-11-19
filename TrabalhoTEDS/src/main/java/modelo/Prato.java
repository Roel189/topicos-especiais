package modelo;

public class Prato extends Produto {

    private int codigo_prato;
    private String nome;
    private String descricao;
    private double preco;

    public int getCodigo_prato() {
        return codigo_prato;
    }

    public void setCodigo_prato(int codigo_prato) {
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
