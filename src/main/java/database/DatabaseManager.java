package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager
{
    private final HikariDataSource source;

    public DatabaseManager(JavaPlugin plugin) {
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl("jdbc:sqlite:" + plugin.getDataFolder() + "/data.db");
        cfg.setMaximumPoolSize(10);
        source = new HikariDataSource(cfg);
        init();
    }

    private void init() {
        try (Connection c = source.getConnection();
             Statement s = c.createStatement()) {

            s.executeUpdate("""
                CREATE TABLE IF NOT EXISTS players (
                    uuid TEXT PRIMARY KEY,
                    mode TEXT,
                    attempt INTEGER,
                    runtime INTEGER
                )
            """);

            s.executeUpdate("""
                CREATE TABLE IF NOT EXISTS advancements (
                    uuid TEXT,
                    advancement TEXT,
                    attempt INTEGER,
                    PRIMARY KEY (uuid, advancement, attempt)
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }

    public void shutdown() {
        source.close();
    }
}
