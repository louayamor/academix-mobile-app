package itbsgl.louayamor.academix.model;

public class Contact {

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

    String username, num ;

    public Contact(String username, String num) {
        this.username = username;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "nom='" + username + '\'' +
                ", numero='" + num + '\'' +
                '}';
    }
}