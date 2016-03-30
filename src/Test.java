import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");

            UserRepository userRepository = new UserRepository(connection);

            User me = userRepository.create("dragos", "pass");
            userRepository.get("dragos", "pass");

            ThreadRepository threadRepository = new ThreadRepository(connection);

            Thread thread = threadRepository.create("topic");
            threadRepository.all();

            PostRepository postRepository = new PostRepository(connection);

            postRepository.create(me, thread, "hi");
            postRepository.all(thread);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
