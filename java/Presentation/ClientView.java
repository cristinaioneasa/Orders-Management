package Presentation;

import exception.Failed;

import java.awt.*;
import javax.swing.*;

public class ClientView extends Component {

    private JFrame frame;
    private JTextField nume;
    private JTextField adresa;
    private JTextField email;
    private JTextField ID;
    private JTable table;
    private JButton butonInsert;
    private JButton butonUpdate;
    private JButton butonFindAll;
    private JButton btnNewButton;
    private JButton butonFindByID;

    public ClientView() {
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

        JLabel labelNume = new JLabel("Nume:");
        labelNume.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelNume.setBounds(20, 20, 50, 20);
        frame.getContentPane().add(labelNume);

        JLabel labelAdresa = new JLabel("Adresa:");
        labelAdresa.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelAdresa.setBounds(20, 60, 50, 20);
        frame.getContentPane().add(labelAdresa);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelEmail.setBounds(20, 100, 50, 20);
        frame.getContentPane().add(labelEmail);

        nume = new JTextField();
        nume.setBounds(75, 20, 150, 20);
        frame.getContentPane().add(nume);
        nume.setColumns(10);

        adresa = new JTextField();
        adresa.setBounds(75, 60, 150, 20);
        frame.getContentPane().add(adresa);
        adresa.setColumns(10);

        email = new JTextField();
        email.setBounds(75, 100, 150, 20);
        frame.getContentPane().add(email);
        email.setColumns(10);

        butonInsert = new JButton("Insert");
        butonInsert.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonInsert.setBounds(260, 20, 80, 20);
        frame.getContentPane().add(butonInsert);

        butonUpdate = new JButton("Update");
        butonUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonUpdate.setBounds(260, 60, 80, 20);
        frame.getContentPane().add(butonUpdate);

        butonFindAll = new JButton("FindAll");
        butonFindAll.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonFindAll.setBounds(260, 100, 80, 20);
        frame.getContentPane().add(butonFindAll);

        btnNewButton = new JButton("Delete");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton.setBounds(600, 40, 80, 20);
        frame.getContentPane().add(btnNewButton);

        butonFindByID = new JButton("Find");
        butonFindByID.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonFindByID.setBounds(600, 80, 80, 20);
        frame.getContentPane().add(butonFindByID);

        JLabel labelID = new JLabel("ID:");
        labelID.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelID.setBounds(500, 60, 30, 20);
        frame.getContentPane().add(labelID);

        ID = new JTextField();
        ID.setBounds(530, 60, 50, 20);
        frame.getContentPane().add(ID);
        ID.setColumns(10);

        table = new JTable();
        table.setBounds(20, 150, 950, 400);
        frame.getContentPane().add(table);
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

    public JButton getButonFindAll() {
        return butonFindAll;
    }

    public void setButonFindAll(JButton butonFindAll) {
        this.butonFindAll = butonFindAll;
    }

    public JButton getBtnNewButton() {
        return btnNewButton;
    }

    public void setBtnNewButton(JButton btnNewButton) {
        this.btnNewButton = btnNewButton;
    }

    public JButton getButonFindByID() {
        return butonFindByID;
    }

    public void setButonFindByID(JButton butonFindByID) {
        this.butonFindByID = butonFindByID;
    }

    public JTextField getNume() {
        return nume;
    }

    public void setNume(JTextField nume) {
        this.nume = nume;
    }

    public JTextField getAdresa() {
        return adresa;
    }

    public void setAdresa(JTextField adresa) {
        this.adresa = adresa;
    }

    public JTextField getEmail() {
        return email;
    }

    public void setEmail(JTextField email) {
        this.email = email;
    }

    public JTextField getID() {
        return ID;
    }

    public void setID(JTextField ID) {
        this.ID = ID;
    }

    public void addTable(JTable clientsTable) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 190, 850, 170);
        scrollPane.setBackground(new Color(77, 121, 79, 160));

        clientsTable.setVisible(true);
        clientsTable.setEnabled(true);
        scrollPane.setViewportView(clientsTable);
        frame.add(scrollPane);
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
}

