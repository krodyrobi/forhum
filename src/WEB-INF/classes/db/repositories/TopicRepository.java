package db.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import db.models.Topic;

public class TopicRepository {
    private final Connection connection;

    public TopicRepository(Connection connection) {
        this.connection = connection;

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("create table if not exists topic (id integer primary key autoincrement, name string)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Topic create(String name) {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("insert into topic(name) values('" + name +  "')");

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) return new Topic(rs.getInt("last_insert_rowid()"), name);
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Topic get(int id) {
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from topic where id='" + id + "'");

            if (rs.next()) return new Topic(id, rs.getString("name"));
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Topic> all() {
        List<Topic> topics = new LinkedList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from topic");

            while (rs.next()) {
                topics.add(new Topic(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }
}
