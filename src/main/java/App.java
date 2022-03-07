


import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("JPA-HBM").createEntityManager();

    }
}
