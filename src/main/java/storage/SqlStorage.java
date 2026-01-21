package storage;

import database.DatabaseManager;
import org.bukkit.NamespacedKey;
import player.PlayerMode;
import player.PlayerProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SqlStorage implements Storage
{
    private final DatabaseManager db;

    public SqlStorage(DatabaseManager db) {
        this.db = db;
    }

    @Override
    public PlayerProfile loadProfile(UUID uuid) {
        try (Connection c = db.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("SELECT * FROM players WHERE uuid=?")) {

            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                PlayerProfile p = new PlayerProfile(uuid);
                saveProfile(p);
                return p;
            }

            PlayerProfile p = new PlayerProfile(uuid);
            p.setPlayerMode(PlayerMode.valueOf(rs.getString("mode")));
            p.setAttempt(rs.getInt("attempt"));
            p.setAttemptTime(rs.getLong("runtime"));
            p.getCompletedAdvancements()
                    .addAll(loadAdvancements(uuid, p.getAttempt()));
            return p;

        } catch (SQLException e) {
            e.printStackTrace();
            return new PlayerProfile(uuid);
        }
    }

    @Override
    public void saveProfile(PlayerProfile p) {
        try (Connection c = db.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("""
                         INSERT OR REPLACE INTO players
                         VALUES (?, ?, ?, ?)
                     """)) {

            ps.setString(1, p.getUuid().toString());
            ps.setString(2, p.getPlayerMode().name());
            ps.setInt(3, p.getAttempt());
            ps.setLong(4, p.getAttemptTime());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<NamespacedKey> loadAdvancements(UUID uuid, int attempt) {
        Set<NamespacedKey> set = new HashSet<>();

        try (Connection c = db.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("""
                         SELECT advancement FROM advancements
                         WHERE uuid=? AND attempt=?
                     """)) {

            ps.setString(1, uuid.toString());
            ps.setInt(2, attempt);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set.add(NamespacedKey.fromString(rs.getString(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return set;
    }

    @Override
    public void addAdvancement(UUID uuid, NamespacedKey key, int attempt) {
        try (Connection c = db.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("""
                         INSERT OR IGNORE INTO advancements
                         VALUES (?, ?, ?)
                     """)) {

            ps.setString(1, uuid.toString());
            ps.setString(2, key.toString());
            ps.setInt(3, attempt);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearAdvancements(UUID uuid, int attempt) {
        try (Connection c = db.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("""
                         DELETE FROM advancements
                         WHERE uuid=? AND attempt=?
                     """)) {

            ps.setString(1, uuid.toString());
            ps.setInt(2, attempt);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
