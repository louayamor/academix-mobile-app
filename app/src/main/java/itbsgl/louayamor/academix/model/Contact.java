package itbsgl.louayamor.academix.model;

public class Contact {
    private int id;
    private String username;
    private String num;
    private String timestamp; // New field

    // Constructor
    public Contact(String username, String num) {
        this.username = username;
        this.num = num;
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

    // Overloaded constructor for retrieving from database
    public Contact(int id, String username, String num, String timestamp) {
        this.id = id;
        this.username = username;
        this.num = num;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", num='" + num + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
