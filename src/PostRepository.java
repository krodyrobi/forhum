import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PostRepository {
    private final Connection connection;
    private final UserRepository userRepository;

    public PostRepository(Connection connection) {
        this.connection = connection;
        this.userRepository = new UserRepository(connection);

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("create table if not exists post (id integer primary key autoincrement, us references us(id), thread references thread(id), message string)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Post create(User user, Thread thread, String message) {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("insert into post(us, thread, message) values(" + user.getId() + ", " + thread.getId() + ", '" + message + "')");

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) return new Post(rs.getInt("last_insert_rowid()"), user, thread, message);
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Post> all(Thread thread) {
        List<Post> posts = new LinkedList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from post where thread=" + thread.getId());

            while (rs.next()) {
                User user = userRepository.get(rs.getInt("us"));

                posts.add(new Post(rs.getInt("id"), user, thread, rs.getString("message")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
