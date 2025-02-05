package itbsgl.louayamor.academix.model;

public class Contact {

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