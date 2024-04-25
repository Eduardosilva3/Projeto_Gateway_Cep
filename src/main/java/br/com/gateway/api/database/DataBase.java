package br.com.gateway.api.database;

import br.com.gateway.api.config.AppConfig;
import br.com.gateway.api.exception.GatewayException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    private static final String JDBC_URL = AppConfig.getJdbcUrl();
    private static final String USERNAME = AppConfig.getUsername();
    private static final String PASSWORD = AppConfig.getPassword();
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_CLASS);
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new GatewayException("Erro ao conectar com o banco de dados: ", e);
        }
    }


    private static boolean existTable(Connection con, String tableName) throws SQLException {
        try (var resultSet = con.getMetaData().getTables(null, null, tableName, null)) {
            return resultSet.next();
        }
    }

    public static void criarTabelaSeNaoExistir() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = conn.createStatement()) {

            if (!existTable(conn, "endereco_gateway")) {
                String sql = "CREATE TABLE endereco_gateway (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "cep VARCHAR(8) UNIQUE NOT NULL," +
                        "uf VARCHAR(255) NOT NULL," +
                        "cidade VARCHAR(255) NOT NULL," +
                        "vizinhanca VARCHAR(255) NOT NULL," +
                        "rua VARCHAR(255) NOT NULL," +
                        "service VARCHAR(255) NOT NULL" +
                        ")";
                statement.executeUpdate(sql);
                System.out.println("Tabela 'endereco_gateway' criada com sucesso.");
            } else {
                System.out.println("A tabela 'endereco_gateway' já existe no banco de dados.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}
