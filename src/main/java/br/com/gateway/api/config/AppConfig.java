package br.com.gateway.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class AppConfig {

    private static final Properties props = new Properties();
    private static final Logger logger = Logger.getLogger(AppConfig.class.getName());

    static {
        try (InputStream inputStream = AppConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            props.load(inputStream);
        } catch (IOException e) {
            logger.warning("Erro ao carregar o arquivo application.properties: " + e.getMessage());
        }
    }


    public static String getJdbcUrl() {
        return props.getProperty("jdbc.url");
    }

    public static String getUsername() {
        return props.getProperty("jdbc.username");
    }

    public static String getPassword() {
        return props.getProperty("jdbc.password");
    }
}
