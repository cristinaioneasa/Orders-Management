package Presentation;


import Data_access.ClientDAO;
import Data_access.ProductDAO;
import Model.Client;
import Model.Product;

import java.awt.*;
import java.util.List;

import javax.swing.*;

public class OrderView extends Component {
    private JFrame frame;
    private JTextField ID;
    private JTextField cantitate;
    private JTable table;
    private JComboBox comboBoxProdus;
    private JComboBox comboBoxClient;
    private JButton butonFindAll;
    private JButton butonInsert;
    private JButton butonUpdate;
    private JButton butonDelete;
    private ClientDAO clientDAO = new ClientDAO();
    private ProductDAO productDAO = new ProductDAO();


    /**
     * Create the application.
     */
    public OrderView() {
        initialize();
        this.frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel labelIdClient = new JLabel("ID Client:");
        labelIdClient.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelIdClient.setBounds(20, 60, 70, 20);
        frame.getContentPane().add(labelIdClient);

        JLabel labelIdProdus = new JLabel("ID Produs:");
        labelIdProdus.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelIdProdus.setBounds(20, 100, 70, 20);
        frame.getContentPane().add(labelIdProdus);

        JLabel labelCantitate = new JLabel("Cantitate:");
        labelCantitate.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelCantitate.setBounds(20, 140, 70, 20);
        frame.getContentPane().add(labelCantitate);

        ID = new JTextField();
        ID.setBounds(90, 20, 80, 20);
        frame.getContentPane().add(ID);
        ID.setColumns(10);

        butonInsert = new JButton("Insert");
        butonInsert.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonInsert.setBounds(300, 140, 80, 20);
        frame.getContentPane().add(butonInsert);

        butonUpdate = new JButton("Update");
        butonUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonUpdate.setBounds(400, 140, 80, 20);
        frame.getContentPane().add(butonUpdate);

        butonFindAll = new JButton("FindAll");
        butonFindAll.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonFindAll.setBounds(200, 140, 80, 20);
        frame.getContentPane().add(butonFindAll);

        butonDelete = new JButton("Delete");
        butonDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonDelete.setBounds(500, 140, 80, 20);
        frame.getContentPane().add(butonDelete);

        JLabel labelID = new JLabel("ID:");
        labelID.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelID.setBounds(20, 20, 30, 20);
        frame.getContentPane().add(labelID);

        cantitate = new JTextField();
        cantitate.setBounds(90, 140, 80, 20);
        frame.getContentPane().add(cantitate);
        cantitate.setColumns(10);

        table = new JTable();
        table.setBounds(20, 174, 950, 376);
        frame.getContentPane().add(table);

        List<Product> products = productDAO.findAll();
        comboBoxProdus = new JComboBox();
        comboBoxProdus.setBounds(90, 100, 80, 20);
        for(Product p : products){
            comboBoxProdus.addItem(p.getID());
        }
        frame.getContentPane().add(comboBoxProdus);

        List<Client> clients = clientDAO.findAll();
        comboBoxClient= new JComboBox();
        comboBoxClient.setBounds(90, 60, 80, 20);
        for(Client client : clients)
            comboBoxClient.addItem(client.getID());
        frame.getContentPane().add(comboBoxClient);
    }

    public JTextField getID() {
        return ID;
    }

    public void setID(JTextField ID) {
        this.ID = ID;
    }

    public JTextField getCantitate() {
        return cantitate;
    }

    public void setCantitate(JTextField cantitate) {
        this.cantitate = cantitate;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JComboBox getComboBoxProdus() {
        return comboBoxProdus;
    }

    public void setComboBoxProdus(JComboBox comboBox_1) {
        this.comboBoxProdus = comboBox_1;
    }

    public JComboBox getComboBoxClient() {
        return comboBoxClient;
    }

    public void setComboBox(JComboBox comboBox) {
        this.comboBoxClient = comboBox;
    }

    public JButton getButonFindAll() {
        return butonFindAll;
    }

    public void setButonFindAll(JButton butonFindAll) {
        this.butonFindAll = butonFindAll;
    }

    public JButton getButonInsert() {
        return butonInsert;
    }

    public void setButonInsert(JButton butonInsert) {
        this.butonInsert = butonInsert;
    }

    public JButton getButonUpdate() {
        return butonUpdate;
    }

    public void setButonUpdate(JButton butonUpdate) {
        this.butonUpdate = butonUpdate;
    }

    public JButton getButonDelete() {
        return butonDelete;
    }

    public void setButonDelete(JButton butonDelete) {
        this.butonDelete = butonDelete;
    }

    public void displayErrorMessage(Exception exception) {
        if (exception != null) {
            String message = exception.getMessage();
            UIManager UI = new UIManager();
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayInformationMessage(String s) {
        if (!s.isEmpty()) {
            UIManager UI = new UIManager();
            JOptionPane.showMessageDialog(this, s, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void addTable(JTable clientsTable) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(90, 200, 800, 170);

        clientsTable.setVisible(true);
        clientsTable.setEnabled(true);
        scrollPane.setViewportView(clientsTable);
        frame.add(scrollPane);
    }
}

