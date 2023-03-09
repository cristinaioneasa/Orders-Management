package Bussiness_logic;

import Data_access.ClientDAO;
import Data_access.OrderDAO;
import Data_access.ProductDAO;
import Model.Client;
import Model.Order;
import Model.Product;
import exception.Failed;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/***
 * In aceasta clasa apelam metodele de insert, update si delete din Clasa OrderDAO
 * si cream fisierul txt cu datele despre comanda
 * @author Ioneasa Cristina
 */
public class OrderBll {
    private OrderDAO order;
    private ClientDAO client;
    private ProductDAO product;

    public OrderBll(){
        order = new OrderDAO();
        client = new ClientDAO();
        product = new ProductDAO();
    }

    public Order insertOrder(Order o){
        Product p = (Product) product.findById(o.getIdProdus());
        Client c = (Client) client.findById(o.getIdClient());

        if(p.getCantitate() < o.getCantitate())
            throw new Failed("NU sunt suficiente produse in stoc");
            int nouaCantitate = p.getCantitate() - o.getCantitate();
            p.setCantitate(nouaCantitate);
            product.update(p);
            ArrayList<Order> orders = (ArrayList<Order>) order.findAll();
            Order lastOrder = orders.get(orders.size()-1);
            o.setID(lastOrder.getID()+1);
            factura(o, c, p);

        return (Order) order.insert(o);
    }

    /***
     * Aceasta metoda creaza un fisier txt care contine toate datele despre o comanda
     * @param o - comanda
     * @param c - clientul care a facut comanda
     * @param p - produsul comandat
     */
    private void factura(Order o, Client c, Product p) {
        FileWriter fisier;
        try{
            fisier = new FileWriter("Factura nr." + o.getID() + ".txt");
            fisier.append("Factura numarul :" + o.getID() +
                    "\n\nProdusul cumparat: " + p.getNume() + "\nPret/bucata: " + p.getPret() + "\nCantitate: " + o.getCantitate() +
                    "\n\nClientul: " + c.getNume() + "\nAdresa de facturare: " + c.getAdresa() + "\nEmail: " + c.getEmail() +
                    "\n\n\n TOTAL plata: " + p.getPret()*o.getCantitate());
            fisier.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        Order o = order.findById(id);
        if (o == null)
            throw new Failed("Comanda cu id="+id+" nu exista in baza de date.");
        Product p = product.findById(o.getIdProdus());
        int nouaCantitate = p.getCantitate() + o.getCantitate();
        p.setCantitate(nouaCantitate);
        product.update(p);
        order.delete(o);
    }

    public void updateOrder(Order o, int id){
        Order existentOrder = order.findById(id);
        if (existentOrder == null)
            throw new Failed("Comanda cu id="+id+" nu exista in baza de date.");
        o.setID(id);
        Product p = product.findById(o.getIdProdus());
        if (p.getCantitate() + existentOrder.getCantitate() < o.getCantitate()) {
            throw new Failed("Cantitatea de "+p.getNume()+" ceruta este mai mare decat stocul pe care il avem");
        }
        int nouaCantitate = p.getCantitate() + existentOrder.getCantitate() - o.getCantitate();
        p.setCantitate(nouaCantitate);
        product.update(p);
        order.update(o);
    }

    public OrderDAO getOrder() {
        return order;
    }

    public void setOrder(OrderDAO order) {
        this.order = order;
    }

    public ClientDAO getClient() {
        return client;
    }

    public void setClient(ClientDAO client) {
        this.client = client;
    }

    public ProductDAO getProduct() {
        return product;
    }

    public void setProduct(ProductDAO product) {
        this.product = product;
    }

}

