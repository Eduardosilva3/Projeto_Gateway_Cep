package br.com.gateway.api.database;

import br.com.gateway.api.config.AppConfig;
import br.com.gateway.api.exception.GatewayException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new GatewayException("Erro ao fechar a conexão com o banco de dados: ", e);
        }
    }
}
