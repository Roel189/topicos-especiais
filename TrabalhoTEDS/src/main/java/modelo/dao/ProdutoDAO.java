package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Produto;

public class ProdutoDAO {
    
    private final Connection conexao;

    public ProdutoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Produto buscar(long codigo) throws SQLException {
        Produto produto = null;
        String selecao = "SELECT * FROM produto WHERE codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setCodigo(rs.getLong(1));
                    produto.setNome(rs.getString(2));
                    produto.setDescricao(rs.getString(3));
                    produto.setPreco(rs.getDouble(4));
                }
            }
        }
        return produto;
    }

    public List<Produto> buscarTodos() throws SQLException {
        Produto produto;
        List<Produto> produtos = new ArrayList<Produto>();
        String selecao = "SELECT * FROM produto";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    produto = new Produto();
                    produto.setCodigo(rs.getLong(1));
                    produto.setNome(rs.getString(2));
                    produto.setDescricao(rs.getString(3));
                    produto.setPreco(rs.getDouble(4));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }

}
