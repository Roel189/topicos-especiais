package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Item;
import modelo.Pedido;
import modelo.Produto;

public class ClienteDAO {
    
    private final Connection conexao;

    public ClienteDAO (Connection conexao) {
        this.conexao = conexao;
    }

    
    
    public void gravar(Cliente cliente) throws SQLException {

        String insercao = "INSERT INTO cliente (cpf, nome, data_nasc, email, senha, idEndereco) VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, cliente.getCpf());
            pstmt.setString(2, cliente.getNome());
            pstmt.setString(3, cliente.getData());
            pstmt.setString(4, cliente.getEmail());
            pstmt.setString(5, cliente.getSenha());
            pstmt.setInt(6, cliente.getIdEndereco());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\n Inserção bem sucedida.");
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }

    }
    

    public List<Cliente> buscarTodos() throws SQLException {
        Cliente cliente;
        List<Cliente> clientes = new ArrayList<Cliente>();
        String selecao = "SELECT * FROM cliente";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    cliente = new Cliente();
                    cliente.setCpf(rs.getString(1));
                    cliente.setNome(rs.getString(2));
                    cliente.setData(rs.getString(3));
                    cliente.setEmail(rs.getString(4));
                    cliente.setSenha(rs.getString(5));
                    cliente.setIdEndereco(rs.getInt(6));
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }
    
    public Cliente buscar(String cpf) throws SQLException {
        Cliente cliente = null;
        String selecao = "SELECT * FROM produto WHERE cpf = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setString(1, cpf);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setCpf(rs.getString(1));
                    cliente.setNome(rs.getString(2));
                    cliente.setData(rs.getString(3));
                    cliente.setEmail(rs.getString(4));
                    cliente.setSenha(rs.getString(5));
                    cliente.setIdEndereco(rs.getInt(6));
               
                }
            }
        }
        return cliente;
    }
    
}
