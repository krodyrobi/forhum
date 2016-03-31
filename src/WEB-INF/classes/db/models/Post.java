package db.models;

public class Post {
    private final int id;
    private final User user;
    private final Topic topic;
    private final String message;

    public Post(int id, User user, Topic topic, String message) {
        this.id = id;
        this.user = user;
        this.topic = topic;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getMessage() {
        return message;
    }
}
