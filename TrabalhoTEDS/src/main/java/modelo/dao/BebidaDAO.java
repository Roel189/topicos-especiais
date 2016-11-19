package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Bebida;

public class BebidaDAO {

    private final Connection conexao;

    public BebidaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Bebida buscar(long codigo) throws SQLException {
        Bebida bebida = null;
        String selecao = "SELECT * FROM bebida WHERE codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bebida = new Bebida();
                    bebida.setCodigo(rs.getLong(1));
                    bebida.setNome(rs.getString(2));
                    bebida.setDescricao(rs.getString(3));
                    bebida.setPreco(rs.getDouble(4));
                }
            }
        }
        return bebida;
    }

    public List<Bebida> buscarTodos() throws SQLException {
        Bebida bebida;
        List<Bebida> bebidas = new ArrayList<Bebida>();
        String selecao = "SELECT * FROM bebida";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    bebida = new Bebida();
                    bebida.setCodigo(rs.getLong(1));
                    bebida.setNome(rs.getString(2));
                    bebida.setDescricao(rs.getString(3));
                    bebida.setPreco(rs.getDouble(4));
                    bebidas.add(bebida);
                }
            }
        }
        return bebidas;
    }

    public void gravar(Bebida bebida) throws SQLException {

        String insercao = "INSERT INTO bebida (codigo, nome, descricao, preco) VALUES (?, ?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao)) {
            pstmt.setInt(1, bebida.getCodigo_bebida());
            pstmt.setString(2, bebida.getNome());
            pstmt.setString(3, bebida.getDescricao());
            pstmt.setDouble(4, bebida.getPreco());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\nInserção bem sucedida.");
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }
    }
}
