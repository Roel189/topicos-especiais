package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Item;
import modelo.Pedido;

public class PedidoDAO {

    private final Connection conexao;

    public PedidoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gravar(Pedido pedido) throws SQLException {

        ItemDAO dao = new ItemDAO(conexao);
        String insercao = "INSERT INTO pedido (dataCompra, valor) VALUES (?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, pedido.getData());
            pstmt.setDouble(2, pedido.getValor());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\nInserção bem sucedida.");
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        pedido.setCodigo(rs.getLong(1));
                    }
                }
                for (Item item : pedido.getItens()) {
                    dao.gravar(item, pedido);
                }
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }

    }

    public List<Pedido> buscarTodos() throws SQLException {
        Pedido pedido;
        List<Pedido> pedidos = new ArrayList<>();
        ItemDAO dao = new ItemDAO(conexao);
        String selecao = "SELECT * FROM pedido";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    pedido = new Pedido();
                    pedido.setCodigo(rs.getLong(1));
                    pedido.setData(rs.getString(2));
                    pedido.setValor(rs.getDouble(3));
                    dao.buscarTodos(pedido);
                    pedidos.add(pedido);
                }
            }
        }
        return pedidos;
    }
}
