package Model;

public class Product {
    private int ID;
    private String nume;
    private int cantitate;
    private float pret;

    public Product(){

    }

    public Product(String nume, int cantitate, int pret) {
        this.nume = nume;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public Product(int ID, String nume, int cantitate, int pret) {
        this.ID = ID;
        this.nume = nume;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Product{" +
                "nume='" + nume + '\'' +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                '}';
    }
}
