package Model;

public class Client {
    private int ID;
    private String nume;
    private String adresa;
    private String email;

    public Client(){

    }

    public Client(String nume, String adresa, String email) {
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
    }

    public Client(int ID, String nume, String adresa, String email) {
        this.ID = ID;
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + nume + '\'' +
                ", address='" + adresa + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

