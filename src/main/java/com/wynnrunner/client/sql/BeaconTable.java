package com.wynnrunner.client.sql;

import com.wynnrunner.client.challenges.Challenge;
import net.minecraft.util.math.BlockPos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChallengeTable {

    private static final String url = "jdbc:mysql://82.165.111.229:3306/wynnrunner";
    private static final String username = "wynnadmin";
    private static final String password = "9Yo3uK6K7f$bAdCpzD#Aec%456#dovJ";
    private static final String tableName = "challenges";
    private static String db_name = "wynnrunner";

    public static synchronized void set(Challenge challenge) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO " + tableName + " (pos, challenge) VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE challenge = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, challenge.location().toString());
            preparedStatement.setString(2, challenge.toString());
            preparedStatement.setString(3, challenge.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
