package Presentation;

import Bussiness_logic.ClientBll;
import Model.Client;
import exception.Failed;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientController {
    private ClientView view;
    private ClientBll client;

    public ClientController() {
        view = new ClientView();
        client = new ClientBll();
        initializeListeners();
    }

    private void initializeListeners() {
        view.getButonInsert().addActionListener(e-> {
            try {
                Client c = new Client(view.getNume().getText(), view.getAdresa().getText(), view.getEmail().getText());
                client.insertClient(c);
                view.displayInformationMessage("Clientul a fost adaugat in baza de date.");
            }catch (Failed message){
                view.displayErrorMessage(message);
            }
        });

        view.getButonUpdate().addActionListener((e -> {
            try {
                int id = Integer.parseInt(view.getID().getText());
                Client c = new Client(view.getNume().getText(), view.getAdresa().getText(), view.getEmail().getText());
                client.updateClient(c, id);
                view.displayInformationMessage("Datele clientului au fost schimbate.");
            } catch (NumberFormatException ex) {
                view.displayInformationMessage("ID trebuie sa fie un numar!!");
            }
        }));

        view.getButonFindAll().addActionListener(e->{
            List<Client> clients = client.getClientDAO().findAll();
            JTable clientsTable = client.getClientDAO().createTable(clients);
            view.addTable(clientsTable);
        });

        //getBtnNewButton e pentru delete
        view.getBtnNewButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(view.getID().getText());
                client.deleteClient(id);
                view.displayInformationMessage("Clientul a fost sters din baza de date.");
            } catch (NumberFormatException ex) {
                view.displayInformationMessage("ID trebuie sa fie un numar!!.");
            }
        });

        view.getButonFindByID().addActionListener(e->{
            int id = Integer.parseInt(view.getID().getText());
            List<Client> clients = new ArrayList<>();
            Client c = client.getClientDAO().findById(id);
            clients.add(c);
            JTable clientsTable = client.getClientDAO().createTable(clients);
            view.addTable(clientsTable);
        });
    }


}
