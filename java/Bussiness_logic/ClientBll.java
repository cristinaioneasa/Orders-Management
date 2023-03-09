package Bussiness_logic;

import Data_access.ClientDAO;
import Model.Client;
import exception.Failed;

import java.util.ArrayList;

/***
 * In aceasta clasa apelam metodele de insert, update si delete din Clasa ClientDAO
 * @author Ioneasa Cristina
 */
public class ClientBll {
    private ClientDAO client;

    public ClientBll(){

        client = new ClientDAO();
    }

    public Client insertClient(Client c){
        ArrayList<Client> clients = (ArrayList<Client>) client.findAll();
        Client lastClient = clients.get(clients.size()-1);
        c.setID(lastClient.getID() + 1);
        return client.insert(c);
    }

    public void updateClient(Client c, int id){
        Client cExistent = client.findById(id);
        if(cExistent == null)
            throw new Failed("Clientul cu id="+id+" nu exista in baza de date");

        c.setID(id);
        client.update(c);
    }

    public void deleteClient(int id){
        Client c = client.findById(id);
        if(c == null)
            throw new Failed("Clientul cu id="+id+" nu exista in baza de date");
        client.delete(c);
    }

    public ClientDAO getClientDAO() {
        return client;
    }

    public void setClientDAO(ClientDAO client) {

        this.client = client;
    }

}

