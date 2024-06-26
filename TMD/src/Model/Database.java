package Model;

import java.sql.*;

public class Database {
    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_updown", "root", "");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    // Untuk select query dengan parameter dinamis
    public ResultSet selectQuery(String sql, Object... params) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Untuk insert, update, dan delete dengan parameter dinamis
    public int InsertUpdateDelete(String sql, Object... params) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
