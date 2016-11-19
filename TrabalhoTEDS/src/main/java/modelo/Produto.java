package modelo;

public class Produto {
    private long codigo;
    private int prato_codigo;
    private int bebida_codigo;
     private double preco;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public int getPrato_codigo() {
        return prato_codigo;
    }

    public void setPrato_codigo(int prato_codigo) {
        this.prato_codigo = prato_codigo;
    }

    public int getBebida_codigo() {
        return bebida_codigo;
    }

    public void setBebida_codigo(int bebida_codigo) {
        this.bebida_codigo = bebida_codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
