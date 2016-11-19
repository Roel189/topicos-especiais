package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Prato;


public class PratoDAO {
    
    private final Connection conexao;

    public PratoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Prato buscar(long codigo) throws SQLException {
        Prato prato = null;
        String selecao = "SELECT * FROM prato WHERE codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    prato = new Prato();
                    prato.setCodigo(rs.getLong(1));
                    prato.setNome(rs.getString(2));
                    prato.setDescricao(rs.getString(3));
                    prato.setPreco(rs.getDouble(4));
                }
            }
        }
        return prato;
    }

    public List<Prato> buscarTodos() throws SQLException {
        Prato prato;
        List<Prato> pratos = new ArrayList<Prato>();
        String selecao = "SELECT * FROM prato";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    prato = new Prato();
                    prato.setCodigo(rs.getLong(1));
                    prato.setNome(rs.getString(2));
                    prato.setDescricao(rs.getString(3));
                    prato.setPreco(rs.getDouble(4));
                    pratos.add(prato);
                }
            }
        }
        return pratos;
    }

}
