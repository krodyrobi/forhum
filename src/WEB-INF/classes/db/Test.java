package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.repositories.*;
import db.models.*;


public class Test {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");

            UserRepository userRepository = new UserRepository(connection);

            User me = userRepository.create("dragos", "pass");
            userRepository.get("dragos", "pass");

            TopicRepository topicRepository = new TopicRepository(connection);

            Topic topic = topicRepository.create("topic");
            topicRepository.all();

            PostRepository postRepository = new PostRepository(connection);

            postRepository.create(me, topic, "hi");
            postRepository.all(topic);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
