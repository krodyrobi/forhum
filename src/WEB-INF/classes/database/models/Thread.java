package database.models;

public class Thread {
    private final int id;
    private final String name;

    public Thread(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
