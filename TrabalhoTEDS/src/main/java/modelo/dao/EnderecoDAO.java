package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Endereco;
import modelo.Item;
import modelo.Pedido;
import modelo.Produto;

public class EnderecoDAO {
    
    private final Connection conexao;

    public EnderecoDAO (Connection conexao) {
        this.conexao = conexao;
    }

    
    public void gravar(Endereco endereco) throws SQLException {

        String insercao = "INSERT INTO endereco (endereco, numero, bairro, cep, estado, cidade, complemento) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, endereco.getEndereco());
            pstmt.setString(2, endereco.getNumero());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getCep());
            pstmt.setString(5, endereco.getEstado());
            pstmt.setString(6, endereco.getCidade());
            pstmt.setString(7, endereco.getComplemento());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\nInserção bem sucedida.");
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        endereco.setIdEndereco(rs.getInt(1));
                    }
                }
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }

    }
    
    public List<Endereco> buscarTodos() throws SQLException {
        Endereco endereco;
        List<Endereco> enderecos = new ArrayList<Endereco>();
        String selecao = "SELECT * FROM cliente";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    endereco = new Endereco();
                    endereco.setEndereco(rs.getString(1));
                    endereco.setNumero(rs.getString(2));
                    endereco.setBairro(rs.getString(3));
                    endereco.setCep(rs.getString(4));
                    endereco.setEstado(rs.getString(5));
                    endereco.setCidade(rs.getString(6));
                    endereco.setComplemento(rs.getString(7));
                    enderecos.add(endereco);
                }
            }
        }
        return enderecos;
    }
}
