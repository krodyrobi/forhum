package database.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ThreadRepository {
    private final Connection connection;

    public ThreadRepository(Connection connection) {
        this.connection = connection;

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("create table if not exists thread (id integer primary key autoincrement, name string)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Thread create(String name) {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("insert into thread(name) values('" + name +  "')");

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) return new Thread(rs.getInt("last_insert_rowid()"), name);
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Thread get(int id) {
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from thread where id='" + id + "'");

            if (rs.next()) return new Thread(id, rs.getString("name"));
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Thread> all() {
        List<Thread> threads = new LinkedList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from thread");

            while (rs.next()) {
                threads.add(new Thread(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return threads;
    }
}
