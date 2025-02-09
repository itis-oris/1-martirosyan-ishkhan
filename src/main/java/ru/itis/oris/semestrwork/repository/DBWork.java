package ru.itis.oris.semestrwork.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import java.sql.Connection;
import java.sql.SQLException;

public class DBWork {

    final static Logger logger = LogManager.getLogger(DBWork.class);

    private static DBWork instance;

    private static HikariDataSource dataSource;

    private DBWork() {

        try {
            Class.forName("org.postgresql.Driver");

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            config.setUsername("postgres");
            config.setPassword("18092005");
            config.setConnectionTimeout(50000);
            config.setMaximumPoolSize(20);
            dataSource = new HikariDataSource(config);

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            logger.info("start migration");
            flyway.migrate();
            logger.info("migration done");
        } catch (ClassNotFoundException | FlywayException e) {
            logger.error("", e);
        }
    }

    public static DBWork getInstance() {
        if (instance == null) {
            synchronized (DBWork.class) {
                if (instance == null) {
                    instance = new DBWork();
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    /**
     * Best practice: try (Connection connection = ds.getConnection()) { ... }
     * @param connection
     * @throws SQLException
     */
    public synchronized void releaseConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public void destroy() {
        dataSource.close();
    }
}