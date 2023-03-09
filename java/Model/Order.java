package Model;

public class Order {
    private int ID;
    private int idClient;
    private int idProdus;
    private int cantitate;

    public Order(){

    }

    public Order(int ID, int idClient, int idProdus, int cantitate) {
        this.ID = ID;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
    }

    public Order(int idClient, int idProdus, int cantitate) {
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "ID=" + ID +
                ", idClient=" + idClient +
                ", idProdus=" + idProdus +
                ", cantitate=" + cantitate +
                '}';
    }
}

