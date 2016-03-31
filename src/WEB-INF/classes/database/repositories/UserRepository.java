package database.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("create table if not exists us (id integer primary key autoincrement, name string, password string)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User create(String name, String password) {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("insert into us(name, password) values('" + name +  "', '" + password + "')");

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) return new User(rs.getInt("last_insert_rowid()"), name, password);
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User get(String name, String password) {
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from us where name='" + name + "' and password='"
                    + password + "'");

            if (rs.next()) return new User(rs.getRow(), rs.getString("name"), rs.getString("password"));
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User get(int id) {
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from us where id='" + id + "'");

            if (rs.next()) return new User(id, rs.getString("name"), rs.getString("password"));
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
