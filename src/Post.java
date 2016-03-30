public class Post {
    private final int id;
    private final User user;
    private final Thread thread;
    private final String message;

    public Post(int id, User user, Thread thread, String message) {
        this.id = id;
        this.user = user;
        this.thread = thread;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Thread getThread() {
        return thread;
    }

    public String getMessage() {
        return message;
    }
}
