package itbsgl.louayamor.academix.model;

public class Contact {
    private int id;
    private String username;
    private String num;

    // Constructors
    public Contact(int id, String username, String num) {
        this.id = id;
        this.username = username;
        this.num = num;
    }

    public Contact(String username, String num) {
        this.username = username;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    // ToString method
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
